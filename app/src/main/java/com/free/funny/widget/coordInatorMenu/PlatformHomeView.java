package com.free.funny.widget.coordInatorMenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by zhujunlin on 2017/7/6.
 * 主界面
 */

public class PlatformHomeView extends RelativeLayout {
    private CoordInatorMenu mCoordInatorMenu;

    public PlatformHomeView(Context context) {
        this(context, null, 0);
    }

    public PlatformHomeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlatformHomeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public void setParent(CoordInatorMenu coordInatorMenu) {
        mCoordInatorMenu = coordInatorMenu;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        //拦截事件，不往下传递
        return mCoordInatorMenu.isOpened() || super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mCoordInatorMenu.isOpened()) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                mCoordInatorMenu.closeMenu();
            }
            return true;
        }
        return super.onTouchEvent(event);
    }
}
