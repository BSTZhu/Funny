package com.free.funny.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.free.funny.R;
import com.free.funny.base.flux.AppStore;
import com.free.funny.relax.base.flux.BaseStore;
import com.free.funny.relax.logic.base.RLBaseActivity;
import com.free.funny.widget.MessageDialog;
import com.free.funny.widget.loading.LoadingDialog;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by zhujunlin on 2017/7/3.
 */

public abstract class BaseActivity extends RLBaseActivity {

    public static final String ICON_FLAG = "&#xe612;";
    public static final String ICON_BACK = "&#xe614;";
    public static final String ICON_CLOSE = "&#xe60e;";
    public static final String ICON_MORE = "&#xe60d;";
    public static final String ICON_SHARE = "&#xe60f;";
    public static final String ICON_SHARE_2 = "&#xe606;";
    public static final String ICON_PLANE = "&#xe610;";
    public static final String ICON_CANLENDAR = "&#xe600;";
    public static final String ICON_OK = "&#xe607;";
    public static final String ICON_OK_2 = "&#xe602;";
    public static final String ICON_OK_3 = "&#xe611;";
    public static final String ICON_OK_4 = "&#xe60a;";
    public static final String ICON_FAIL = "&#xe608;";
    public static final String ICON_FAIL_2 = "&#xe604;";
    public static final String ICON_TOP = "&#xe609;";
    public static final String ICON_ADD = "&#xe617;";
    public static final String ICON_REMOVE = "&#xe618;";
    public static final String ICON_END = "&#xe613;";
    public static final String ICON_BELL = "&#xe60b;";
    public static final String ICON_BELL_2 = "&#xe603;";
    public static final String ICON_INFO = "&#xe605;";
    public static final String ICON_BENING = "&#xe60c;";
    public static final String ICON_SET = "&#xe619;";
    public static final String ICON_GONE = "";

    protected AppStore mAppStore;
    private boolean mIsForeground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppStore = AppStore.getInstance();
        if (getObject() != null) {
            BaseApplication.sDispatcher.register(getObject());
        }
        if (getStore() != null) {
            getStore().register();
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        init();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        init();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        init();
    }

    private void init() {
        setLeftText(ICON_BACK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected boolean needAutoRetry(Object body) {
        if (body instanceof BaseModel) {
            BaseModel model = (BaseModel) body;
            if ("50001".equals(model.returnFlag)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onResume() {
        mIsForeground = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        mIsForeground = false;
        super.onPause();
    }

    @Override
    protected boolean hasInterrupt() {
        return false;
    }

    @Override
    protected LoadingDialog getLoadingDialog() {
        return new LoadingDialog(this);
    }

    @Override
    protected Dialog getRetryDialog(final DialogInterface.OnClickListener onButtonListener,
                                    final DialogInterface.OnCancelListener onCancelListener) {
        final MessageDialog dialog = new MessageDialog(this);
        dialog.setOnButtonClickListener(new MessageDialog.OnButtonClickListener() {
            @Override
            public void onClick(int which) {
                onButtonListener.onClick(dialog, which);
            }
        });
        dialog.setOnCancelListener(onCancelListener);
        return dialog;
    }

    @Override
    protected View getErrorView() {
        return LayoutInflater.from(this).inflate(R.layout.base_view_error_page, null);
    }

    @Subscribe
    public void onParentLoginSuccess(AppStore.LoginSuccessEvent event) {
        if (mIsForeground) {
            retryAutoFailureRequest();
        }
    }

    public boolean isForeground() {
        return mIsForeground;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getObject() != null) {
            BaseApplication.sDispatcher.unregister(getObject());
        }
        if (getStore() != null) {
            getStore().unregister();
        }
    }

    /**
     * 用于事件总线的自动注册
     *
     * @return 当前的Activity
     */
    protected abstract <T> T getObject();

    protected BaseStore getStore() {
        return null;
    }
}
