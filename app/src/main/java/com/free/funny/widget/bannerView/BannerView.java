package com.free.funny.widget.bannerView;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.free.funny.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhujunlin on 2017/7/6.
 */

public class BannerView<T> extends RelativeLayout {

    private LinearLayout ll_indicator_container; //indicator容器
    private ViewPager vp_banner;

    enum IndicatorAlign {
        LEFT,//做对齐
        CENTER,//居中对齐
        RIGHT //右对齐
    }

    private boolean mIsOpenBannerEffect = true;// 开启Banner效果 false 普通viewpager
    private boolean mIsCanLoop = true;// 是否轮播图片
    private int mIndicatorAlign = 1;
    private int mIndicatorPaddingLeft = 0;
    private int mIndicatorPaddingRight = 0;
    private int mBannerModePadding = 0;//在banner模式下，由于前后显示了上下一个页面的部分，因此需要计算这部分padding
    private ViewPagerScroller mViewPagerScroller;//控制ViewPager滑动速度的Scroller
    private boolean mIsAutoPlay = true;// 是否自动播放
    private int mCurrentItem = 0;//当前位置
    private Handler mHandler = new Handler();
    private BannerPagerAdapter mBannerAdapter;
    private int mDelayedTime = 3000;// Banner 切换时间间隔
    private List<T> mDatas = new ArrayList<>();
    private BannerPageClickListener mBannerPageClickListener;
    private ArrayList<ImageView> mIndicators = new ArrayList<>();
    private int[] mIndicatorRes = new int[]{R.drawable.indicator_normal, R.drawable.indicator_selected};
    private ViewPager.OnPageChangeListener mOnPageChangeListener;

    private final Runnable mLoopRunnable = new Runnable() {
        @Override
        public void run() {
            if (mIsAutoPlay) {
                mCurrentItem = vp_banner.getCurrentItem();
                mCurrentItem++;
                if (mCurrentItem == mBannerAdapter.getCount() - 1) {
                    mCurrentItem = 0;
                    vp_banner.setCurrentItem(mCurrentItem, false);
                    mHandler.postDelayed(this, mDelayedTime);
                } else {
                    vp_banner.setCurrentItem(mCurrentItem);
                    mHandler.postDelayed(this, mDelayedTime);
                }
            } else {
                mHandler.postDelayed(this, mDelayedTime);
            }
        }
    };

    public BannerView(Context context) {
        super(context);
        init();
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttrs(context, attrs);
        init();
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttrs(context, attrs);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BannerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        readAttrs(context, attrs);
        init();
    }

    private void readAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BannerView);
        mIsOpenBannerEffect = typedArray.getBoolean(R.styleable.BannerView_open_banner_mode, true);
        mIsCanLoop = typedArray.getBoolean(R.styleable.BannerView_canLoop, true);
        mIndicatorAlign = typedArray.getInt(R.styleable.BannerView_indicatorAlign, 1);
        mIndicatorPaddingLeft = typedArray.getDimensionPixelSize(R.styleable.BannerView_indicatorPaddingLeft, 0);
        mIndicatorPaddingRight = typedArray.getDimensionPixelSize(R.styleable.BannerView_indicatorPaddingRight, 0);
    }

    private void init() {
        View view = null;
        if (mIsOpenBannerEffect) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.widget_bannerview_banner_effect, this, true);
        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.widget_bannerview_banner_normal, this, true);
        }
        ll_indicator_container = (LinearLayout) view.findViewById(R.id.ll_indicator_container);
        vp_banner = (ViewPager) view.findViewById(R.id.vp_banner);
        vp_banner.setOffscreenPageLimit(4);
        mBannerModePadding = dpToPx(30);

        // 初始化Scroller
        initViewPagerScroll();

        //设置对其方式
        if (mIndicatorAlign == 0) {
            setIndicatorAlign(IndicatorAlign.LEFT);
        } else if (mIndicatorAlign == 1) {
            setIndicatorAlign(IndicatorAlign.CENTER);
        } else {
            setIndicatorAlign(IndicatorAlign.RIGHT);
        }
    }

    /**
     * 设置ViewPager的滑动速度
     */
    private void initViewPagerScroll() {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            mViewPagerScroller = new ViewPagerScroller(
                    vp_banner.getContext());
            mScroller.set(vp_banner, mViewPagerScroller);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化指示器Indicator
     */
    private void initIndicator() {
        ll_indicator_container.removeAllViews();
        mIndicators.clear();
        for (int i = 0; i < mDatas.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            if (mIndicatorAlign == IndicatorAlign.LEFT.ordinal()) {
                if (i == 0) {
                    int paddingLeft = mIsOpenBannerEffect ? mIndicatorPaddingLeft + mBannerModePadding : mIndicatorPaddingLeft;
                    imageView.setPadding(paddingLeft + 6, 0, 6, 0);
                } else {
                    imageView.setPadding(6, 0, 6, 0);
                }
            } else if (mIndicatorAlign == IndicatorAlign.RIGHT.ordinal()) {
                if (i == mDatas.size() - 1) {
                    int paddingRight = mIsOpenBannerEffect ? mBannerModePadding + mIndicatorPaddingRight : mIndicatorPaddingRight;
                    imageView.setPadding(6, 0, 6 + paddingRight, 0);
                } else {
                    imageView.setPadding(6, 0, 6, 0);
                }
            } else {
                imageView.setPadding(6, 0, 6, 0);
            }

            if (i == (mCurrentItem % mDatas.size())) {
                imageView.setImageResource(mIndicatorRes[1]);
            } else {
                imageView.setImageResource(mIndicatorRes[0]);
            }

            mIndicators.add(imageView);
            ll_indicator_container.addView(imageView);
        }
    }

    /***********************************************************************/
    /**                             对外API                               **/
    /**********************************************************************/
    /**
     * 设置Indicator 的对齐方式
     */
    public void setIndicatorAlign(IndicatorAlign indicatorAlign) {
        mIndicatorAlign = indicatorAlign.ordinal();
        LayoutParams layoutParams = (LayoutParams) ll_indicator_container.getLayoutParams();
        if (indicatorAlign == IndicatorAlign.LEFT) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        } else if (indicatorAlign == IndicatorAlign.RIGHT) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        } else {
            layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        }
        ll_indicator_container.setLayoutParams(layoutParams);
    }

    /**
     * 是否开启广告模式
     */
    public void setOpenBinnerEffect(boolean isOpenBannerEffect) {
        mIsOpenBannerEffect = isOpenBannerEffect;
        if (mIsOpenBannerEffect) {
            vp_banner.setPageTransformer(false, new CustomTransformer());
        }
    }

    /**
     * 设置ViewPager切换的速度
     */
    public void setDuration(int duration) {
        mViewPagerScroller.setDuration(duration);
    }

    /**
     * 设置是否使用ViewPager默认是的切换速度
     */
    public void setUseDefaultDuration(boolean useDefaultDuration) {
        mViewPagerScroller.setUseDefaultDuration(useDefaultDuration);
    }

    /**
     * 获取Banner页面切换动画时间
     */
    public int getDuration() {
        return mViewPagerScroller.getScrollDuration();
    }

    /**
     * 添加Page点击事件
     */
    public void setBannerPageClickListener(BannerPageClickListener bannerPageClickListener) {
        mBannerPageClickListener = bannerPageClickListener;
    }

    /**
     * 设置数据，这是最重要的一个方法。
     * 其他的配置应该在这个方法之前调用
     *
     * @param datas           Banner 展示的数据集合
     * @param bannerHolderCreator ViewHolder生成器
     */
    public void setPages(List<T> datas, BannerHolderCreator bannerHolderCreator) {
        if (datas == null || bannerHolderCreator == null) {
            return;
        }
        mDatas = datas;

        if (datas.size() < 2) {
            mIsOpenBannerEffect = false;
            MarginLayoutParams layoutParams = (MarginLayoutParams) vp_banner.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            vp_banner.setLayoutParams(layoutParams);
            setClipChildren(true);
            vp_banner.setClipChildren(true);
        } else {
            mIsOpenBannerEffect = true;
        }

        setOpenBinnerEffect(mIsOpenBannerEffect);

        mBannerAdapter = new BannerPagerAdapter(datas, bannerHolderCreator, mIsCanLoop, vp_banner);
        mBannerAdapter.setUpViewViewPager(vp_banner);
        mBannerAdapter.setPageClickListener(mBannerPageClickListener);
        vp_banner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentItem = position;
                // 切换indicator
                int realSelectPosition = mCurrentItem % mIndicators.size();
                for (int i = 0; i < mDatas.size(); i++) {
                    if (i == realSelectPosition) {
                        mIndicators.get(i).setImageResource(mIndicatorRes[1]);
                    } else {
                        mIndicators.get(i).setImageResource(mIndicatorRes[0]);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        mIsAutoPlay = false;
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        mIsAutoPlay = true;
                        break;

                }
            }
        });
        if (mOnPageChangeListener != null) {
            vp_banner.addOnPageChangeListener(mOnPageChangeListener);
        }
        //初始化Indicator
        initIndicator();
    }

    /**
     * 开始轮播
     * 确保在调用用了setPages之后调用这个方法开始轮播
     */
    public void start() {
        // 如果Adapter为null, 说明还没有设置数据，这个时候不应该轮播Banner
        if (mBannerAdapter == null) {
            return;
        }

        if (mIsCanLoop) {
            mIsAutoPlay = true;
            mHandler.postDelayed(mLoopRunnable, mDelayedTime);
        }
    }

    /**
     * 停止轮播
     */
    public void pause() {
        mIsAutoPlay = false;
        mHandler.removeCallbacks(mLoopRunnable);
    }

    public void addPageChangeLisnter(ViewPager.OnPageChangeListener onPageChangeListener) {
        mOnPageChangeListener = onPageChangeListener;
    }

    /**
     * 是否显示Indicator
     */
    public void setIndicatorVisible(boolean visible) {
        if (visible) {
            ll_indicator_container.setVisibility(VISIBLE);
        } else {
            ll_indicator_container.setVisibility(GONE);
        }
    }

    /**
     * 返回ViewPager
     */
    public ViewPager getViewPager() {
        return vp_banner;
    }

    /**
     * 设置indicator 图片资源
     */
    public void setIndicatorRes(int unSelectRes, int selectRes) {
        mIndicatorRes[0] = unSelectRes;
        mIndicatorRes[1] = selectRes;
    }

    public static int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

}
