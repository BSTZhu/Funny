package com.free.funny.relax.logic.base;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.free.funny.relax.R;
import com.free.funny.relax.base.network.RequestStateListener;
import com.free.funny.relax.base.network.SimpleCall;
import com.free.funny.relax.base.network.SimpleCallBack;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhujunlin on 2017/5/26.
 * <p>
 * 中断概念：中断源发出中断请求，若此时有startActivity或finish等操作，则拒绝，并记录
 * 中断恢复：尝试从记录中恢复中断
 * 中断源：决定当前是否有中断的判断，并决定何时恢复中断
 * 中断连续：不同中断源，串行触发
 */

public abstract class RLBaseActivity extends FragmentActivity {

    /**
     * 请求失败，不显示异常页面
     */
    public static final int NO_EMPTY_VIEW = -1;

    /**
     * 请求失败，显示一个位于titlebar下，撑满屏幕的异常页面
     */
    public static final int FULL_EMPTY_VIEW = 0;
    private static final long LOADING_DISMISS_DELAY = 200;
    private static final int WHAT_DISMISS_LOADINGDIALOG = 0x1001;

    protected static String sNextIgnorePendingIntent;
    /**
     * 在startActivity及startActivityForResult中记录企图打开的Intent，当遇到中断后，用于中断解除后继续
     */
    private Intent mPendingIntent;
    private int mPendingRequestCode = -1000;
    /**
     * 在finish中记录企图退出当前Activity的状态，当中断解除后finish
     */
    private boolean mPendingFinish;

    private Dialog mLoadingDialog, mRetryDialog;
    protected List<SimpleCall> mCancelableCallList = new ArrayList<>();
    protected List<SimpleCall> mUncancelableCallList = new ArrayList<>();
    protected Map<SimpleCall, RetryCallInfo> mRetryCallMap = new HashMap<>();
    protected Map<SimpleCall, RetryCallInfo> mAutoRetryCallMap = new HashMap<>();

    private int mCustomRootLayout = -1;
    private View mRoot;
    private FrameLayout fl_container;
    private RelativeLayout rl_title_bar;
    private TextView tv_title, tv_left1, tv_right1, tv_left2, tv_right2;
    private float mIconSize, mTextSize;
    private RLBaseHandler mParentHandler;

    private static class RLBaseHandler extends Handler {

        private WeakReference<RLBaseActivity> mOuter;

        public RLBaseHandler(RLBaseActivity activity) {
            mOuter = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            RLBaseActivity outer = mOuter.get();
            if (outer != null) {
                if (msg.what == WHAT_DISMISS_LOADINGDIALOG) {// 避免请求框高频闪动
                    try {
                        boolean needShow = outer.mCancelableCallList.size() > 0 || outer.mUncancelableCallList.size() > 0;
                        if (outer.mLoadingDialog.isShowing() && !needShow) {
                            outer.mLoadingDialog.dismiss();
                            if (!outer.mRetryDialog.isShowing() && outer.mRetryCallMap.size() > 0) {
                                outer.mRetryDialog.show();
                            }
                        }
                    } catch (Exception ignored) {
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInternal();
    }

    @Override
    public void setContentView(int layoutResID) {
        mRoot = LayoutInflater.from(this).
                inflate(mCustomRootLayout == -1 ? R.layout.base_activity_layout : mCustomRootLayout, null);
        super.setContentView(mRoot);
        initView();
        addViewToContent(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        mRoot = LayoutInflater.from(this).
                inflate(mCustomRootLayout == -1 ? R.layout.base_activity_layout : mCustomRootLayout, null);
        super.setContentView(mRoot);
        initView();
        addViewToContent(view);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        mRoot = LayoutInflater.from(this).
                inflate(mCustomRootLayout == -1 ? R.layout.base_activity_layout : mCustomRootLayout, null);
        super.setContentView(mRoot);
        initView();
        addViewToContent(view, params);
    }

    private void initInternal() {
        mParentHandler = new RLBaseHandler(this);
        //初始化加载框
        mLoadingDialog = getLoadingDialog();
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                //拦截在加载框显示过程中的物理返回键，用于下面做取消的动作
                if (mLoadingDialog.isShowing() && keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    return true;
                } else if (mLoadingDialog.isShowing() && keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == KeyEvent.ACTION_UP) {
                    cancelRequest();
                    return true;
                } else {
                    return false;
                }
            }
        });
        //初始化重试框
        mRetryDialog = getRetryDialog(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showErrorView();
                } else {
                    retryCall(mRetryCallMap);
                }
            }
        }, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

            }
        });
    }

    private void initView() {
        rl_title_bar = (RelativeLayout) mRoot.findViewWithTag("rl_title_bar");
        fl_container = (FrameLayout) mRoot.findViewWithTag("fl_container");
        tv_title = (TextView) mRoot.findViewWithTag("tv_title");
        tv_left1 = (TextView) mRoot.findViewWithTag("tv_left1");
        tv_right1 = (TextView) mRoot.findViewWithTag("tv_right1");
        tv_left2 = (TextView) mRoot.findViewWithTag("tv_left2");
        tv_right2 = (TextView) mRoot.findViewWithTag("tv_right2");

        mIconSize = tv_left2.getTextSize();
        mTextSize = tv_left1.getTextSize();
    }

    private void addViewToContent(int layoutResID) {
        addViewToContent(LayoutInflater.from(this).inflate(layoutResID, null));
    }

    private void addViewToContent(View view) {
        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        addViewToContent(view, params);
    }

    private void addViewToContent(View view, LayoutParams params) {
        fl_container.addView(view, params);
    }

    protected TextView setIconTypeface(TextView tv) {
        Typeface tf = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        tv.setTypeface(tf);
        return tv;
    }

    protected void setTitleString(String title) {
        tv_title.setText(title);
    }

    protected void setTitleId(int id) {
        setTitleString(getString(id));
    }

    private Spanned getHtmlText(String witch) {
        return Html.fromHtml(witch);
    }

    private boolean isIcon(String s) {
        return s.length() == 8 && s.startsWith("&#") && s.endsWith(";");
    }

    protected void setAllTextColor(int color) {
        tv_title.setTextColor(color);
        tv_left1.setTextColor(color);
        tv_left2.setTextColor(color);
        tv_right1.setTextColor(color);
        tv_right2.setTextColor(color);
    }

    private void refreshTextSize(TextView tv, String s) {
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, isIcon(s) ? mIconSize : mTextSize);
    }

    protected TextView getLeftText() {
        return tv_left1;
    }

    protected TextView[] getLeftTextGroup() {
        return new TextView[]{tv_left1, tv_left2};
    }

    /**
     * 左边只有一个
     *
     * @param text 为""时隐藏
     * @return 显示的TextView
     */
    protected TextView setLeftText(String text) {
        if (isIcon(text)) {
            setIconTypeface(tv_left1);
            tv_left1.setText(getHtmlText(text));
        } else {
            tv_left1.setTypeface(Typeface.DEFAULT);
            tv_left1.setText(text);
        }
        tv_left1.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        tv_left2.setVisibility(View.GONE);
        refreshTextSize(tv_left1, text);
        return tv_left1;
    }

    protected TextView setLeftText(int id) {
        return setLeftText(getString(id));
    }

    protected void setLeftTextClickListener(View.OnClickListener l) {
        tv_left1.setOnClickListener(l);
    }

    protected void setLeftVisibility(boolean isVisibility) {
        if (isVisibility) {
            tv_left1.setVisibility(View.VISIBLE);
        } else {
            tv_left1.setVisibility(View.GONE);
        }
    }

    /**
     * 左边有2个
     *
     * @param text1 从左往右数,第一个
     * @param text2 从左往右数,第二个
     * @return
     */
    protected TextView[] setLeftText(String text1, String text2) {
        if (TextUtils.isEmpty(text1)) {
            setLeftText(text2);
        } else if (TextUtils.isEmpty(text2)) {
            setLeftText(text1);
        }
        if (isIcon(text1)) {
            setIconTypeface(tv_left1);
            tv_left1.setText(getHtmlText(text1));
        } else {
            tv_left1.setTypeface(Typeface.DEFAULT);
            tv_left1.setText(text1);
        }
        tv_left1.setVisibility(TextUtils.isEmpty(text1) ? View.GONE : View.VISIBLE);
        refreshTextSize(tv_left1, text1);
        if (isIcon(text2)) {
            setIconTypeface(tv_left2);
            tv_left2.setText(getHtmlText(text2));
        } else {
            tv_left2.setTypeface(Typeface.DEFAULT);
            tv_left2.setText(text1);
        }
        tv_left2.setVisibility(TextUtils.isEmpty(text2) ? View.GONE : View.VISIBLE);
        refreshTextSize(tv_left2, text2);
        return new TextView[]{tv_left1, tv_left2};
    }

    protected TextView[] setLeftText(int id1, int id2) {
        return setLeftText(getString(id1), getString(id2));
    }

    protected void setLeftTextClickListener(View.OnClickListener l1, View.OnClickListener l2) {
        tv_left1.setOnClickListener(l1);
        tv_left2.setOnClickListener(l2);
    }

    /**
     * 右边只有一个
     *
     * @param text 为""或null时隐藏
     * @return 显示的TextView
     */
    protected TextView setRightText(String text) {
        if (isIcon(text)) {
            setIconTypeface(tv_right1);
            tv_right1.setText(getHtmlText(text));
        } else {
            tv_right1.setTypeface(Typeface.DEFAULT);
            tv_right1.setText(text);
        }
        tv_right1.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        tv_right2.setVisibility(View.GONE);
        refreshTextSize(tv_right1, text);
        return tv_right1;
    }

    protected TextView setRightText(int id) {
        return setRightText(getString(id));
    }

    protected void setRightTextClickListener(View.OnClickListener l) {
        tv_right1.setOnClickListener(l);
    }

    protected TextView getRightText() {
        return tv_right1;
    }

    protected void setRightVisibility(boolean isVisibility) {
        if (isVisibility) {
            tv_right1.setVisibility(View.VISIBLE);
        } else {
            tv_right1.setVisibility(View.GONE);
        }
    }

    /**
     * 右边有2个
     *
     * @param text1 从右往左数,第一个
     * @param text2 从右往左数,第二个
     * @return
     */
    protected TextView[] setRightText(String text1, String text2) {
        if (TextUtils.isEmpty(text1)) {
            setRightText(text2);
        } else if (TextUtils.isEmpty(text2)) {
            setRightText(text1);
        }
        if (isIcon(text1)) {
            setIconTypeface(tv_right1);
            tv_right1.setText(getHtmlText(text1));
        } else {
            tv_right1.setTypeface(Typeface.DEFAULT);
            tv_right1.setText(text1);
        }
        tv_right1.setVisibility(TextUtils.isEmpty(text1) ? View.GONE : View.VISIBLE);
        refreshTextSize(tv_right1, text1);
        if (isIcon(text2)) {
            setIconTypeface(tv_right2);
            tv_right2.setText(getHtmlText(text2));
        } else {
            tv_right2.setTypeface(Typeface.DEFAULT);
            tv_right2.setText(text1);
        }
        tv_right2.setVisibility(TextUtils.isEmpty(text2) ? View.GONE : View.VISIBLE);
        refreshTextSize(tv_right2, text2);
        return new TextView[]{tv_right1, tv_right2};
    }

    protected TextView[] setRightText(int id1, int id2) {
        return setRightText(getString(id1), getString(id2));
    }

    protected TextView[] getRightTextGroup() {
        return new TextView[]{tv_right1, tv_right2};
    }

    protected void setRightTextClickListener(View.OnClickListener l1, View.OnClickListener l2) {
        tv_right1.setOnClickListener(l1);
        tv_right2.setOnClickListener(l2);
    }

    protected void showTitleBar() {
        rl_title_bar.setVisibility(View.VISIBLE);
    }

    protected void hideTitleBar() {
        rl_title_bar.setVisibility(View.GONE);
    }

    @Override
    public void startActivity(Intent intent) {
        ComponentName cn = intent.getComponent();
        if (cn != null && cn.getClassName().equals(sNextIgnorePendingIntent)) {
            super.startActivity(intent);
            sNextIgnorePendingIntent = null;
            return;
        }
        mPendingIntent = intent;
        mPendingRequestCode = -1000;
        if (hasInterrupt()) {
            return;
        }
        super.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        ComponentName cn = intent.getComponent();
        if (cn != null && cn.getClassName().equals(sNextIgnorePendingIntent)) {
            super.startActivityForResult(intent, requestCode);
            sNextIgnorePendingIntent = null;
            return;
        }
        mPendingIntent = intent;
        mPendingRequestCode = requestCode;
        if (hasInterrupt()) {
            return;
        }
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void finish() {
        if (hasInterrupt()) {
            mPendingFinish = true;
            return;
        }
        if (mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
        if (mRetryDialog.isShowing()) {
            mRetryDialog.dismiss();
        }
        super.finish();
    }

    protected void retryAutoFailureRequest() {
        retryCall(mAutoRetryCallMap);
    }

    /**
     * 尝试从中断恢复
     */
    protected void recoveryFromInterrupt() {
        if (mPendingIntent != null) {
            if (mPendingRequestCode == -1000) {
                startActivity(mPendingIntent);
            } else {
                startActivityForResult(mPendingIntent, mPendingRequestCode);
            }
        }
        if (mPendingFinish) {
            mPendingFinish = false;
            finish();
        }
    }

    /**
     * 取消前台、可以被取消的请求
     */
    public void cancelRequest() {
        for (SimpleCall call : mCancelableCallList) {
            call.cancel();
        }
    }

    /**
     * 强制取消前台所有请求
     */
    public void forceCancelRequest() {
        cancelRequest();
        for (SimpleCall call : mUncancelableCallList) {
            call.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCancelableCallList.clear();
        mUncancelableCallList.clear();
        mRetryCallMap.clear();
    }

    private void showErrorView() {
        List<Integer> containerList = new ArrayList<>();
        for (RetryCallInfo info : mRetryCallMap.values()) {
            // 避免重复，不添加不需要显示异常页的请求
            if (!containerList.contains(info.errorViewContainer) && info.errorViewContainer != NO_EMPTY_VIEW) {
                containerList.add(info.errorViewContainer);
            }
        }
        for (int id : containerList) {
            View container = findViewById(id);
            if (id != FULL_EMPTY_VIEW && container != null && container instanceof ViewGroup) {
                LayoutParams params = new LayoutParams(container.getWidth(), container.getHeight());
                ((ViewGroup) container).removeAllViews();
                ((ViewGroup) container).addView(getErrorView(), params);
            } else {
                LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                //布局root
            }
        }
    }

    private void retryCall(Map<SimpleCall, RetryCallInfo> callMap) {
        Set<Map.Entry<SimpleCall, RetryCallInfo>> set = new HashSet<>();
        set.addAll(callMap.entrySet());
        for (Map.Entry<SimpleCall, RetryCallInfo> entry : set) {
            SimpleCall call = entry.getKey();
            RetryCallInfo info = entry.getValue();
            callMap.remove(call);
            addRequest(call.clone(), info.callBack, true, info.cancelable, true, info.errorViewContainer);
        }
    }

    /**
     * 创建一个前台、不可取消、需要重试、无异常页面的请求
     *
     * @param call
     * @param callBack
     */
    public void addRequest(SimpleCall call, SimpleCallBack callBack) {
        addRequest(call, callBack, true);
    }

    /**
     * 创建一个不可取消、需要重试、无异常页面的请求
     *
     * @param call
     * @param callBack
     * @param isForeground 是否为前台请求
     */
    public void addRequest(SimpleCall call, SimpleCallBack callBack, boolean isForeground) {
        addRequest(call, callBack, isForeground, false);
    }

    /**
     * 创建一个需要重试，无异常页面的请求
     *
     * @param call
     * @param callBack
     * @param isForeground 是否为前台请求
     * @param cancelable   是否可以被取消
     */
    public void addRequest(SimpleCall call, SimpleCallBack callBack, boolean isForeground, boolean cancelable) {
        addRequest(call, callBack, isForeground, cancelable, true);
    }

    /**
     * 创建一个请求，若需要重试，则无异常页面
     *
     * @param call
     * @param callBack
     * @param isForeground 是否为前台请求
     * @param cancelable   是否可以被取消
     * @param needRetry    是否需要重试
     */
    public void addRequest(SimpleCall call, SimpleCallBack callBack, boolean isForeground,
                           boolean cancelable, boolean needRetry) {
        addRequest(call, callBack, isForeground, cancelable, needRetry, NO_EMPTY_VIEW);
    }

    /**
     * 创建一个后台请求。该请求可以被取消，不需要重试
     *
     * @param call
     * @param callBack
     */
    public void addBackgroundRequest(SimpleCall call, SimpleCallBack callBack) {
        addRequest(call, callBack, false, true, false, NO_EMPTY_VIEW);
    }

    /**
     * 创建一个请求
     *
     * @param call
     * @param callBack
     * @param isForeground       是否为前台请求
     * @param cancelable         是否可以被取消
     * @param needRetry          是否需要重试
     * @param errorViewContainer 将要装载重试页面的容器ID，此ID需要为当前页面的元素。若ID未找到或非ViewGroup，则默认FULL_EMPTY_VIEW
     */
    public void addRequest(final SimpleCall call, final SimpleCallBack callBack, final boolean isForeground,
                           final boolean cancelable, final boolean needRetry, final int errorViewContainer) {
        RLBaseApplication.sRequestManager.addRequest(call, callBack, new RequestStateListener() {
            @Override
            public void onStart() {
                if (isForeground) {
                    if (cancelable) {
                        mCancelableCallList.add(call);
                    } else {
                        mUncancelableCallList.add(call);
                    }
                    refreshDialogState();
                }
            }

            @Override
            public void onFinish() {
                if (isForeground) {
                    if (mCancelableCallList.contains(call)) {
                        mCancelableCallList.remove(call);
                    } else if (mUncancelableCallList.contains(call)) {
                        mUncancelableCallList.remove(call);
                    }
                    refreshDialogState();
                }
            }

            @Override
            public void onSuccess(Object body) {
                if (mRetryCallMap.containsKey(call)) {
                    mRetryCallMap.remove(call);
                }
                if (needAutoRetry(body) && !mRetryCallMap.containsKey(call)) {
                    RetryCallInfo info = new RetryCallInfo();
                    info.callBack = callBack;
                    info.cancelable = cancelable;
                    info.errorViewContainer = errorViewContainer;
                    mAutoRetryCallMap.put(call, info);
                }
            }

            @Override
            public void onFailure() {
                if (isForeground && needRetry && !mRetryCallMap.containsKey(call)) {
                    RetryCallInfo info = new RetryCallInfo();
                    info.callBack = callBack;
                    info.cancelable = cancelable;
                    info.errorViewContainer = errorViewContainer;
                    mRetryCallMap.put(call, info);
                }
            }
        });
    }

    private void refreshDialogState() {
        boolean needShow = mCancelableCallList.size() > 0 || mUncancelableCallList.size() > 0;
        mParentHandler.removeMessages(WHAT_DISMISS_LOADINGDIALOG);
        if (!mLoadingDialog.isShowing() && needShow) {
            mLoadingDialog.show();
        } else {
            mParentHandler.sendEmptyMessageDelayed(WHAT_DISMISS_LOADINGDIALOG, LOADING_DISMISS_DELAY);
        }
    }

    private class RetryCallInfo {
        SimpleCallBack callBack;
        boolean cancelable;
        int errorViewContainer;
    }

    /**
     * @return 是否有中断，若true，则会终止
     */
    protected abstract boolean hasInterrupt();

    protected abstract boolean needAutoRetry(Object body);

    protected abstract Dialog getLoadingDialog();

    protected abstract Dialog getRetryDialog(DialogInterface.OnClickListener onButtonListener,
                                             DialogInterface.OnCancelListener onCancelListener);

    protected abstract View getErrorView();
}
