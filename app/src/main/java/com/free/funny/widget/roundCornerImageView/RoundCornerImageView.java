package com.free.funny.widget.roundCornerImageView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.free.funny.R;

/**
 * Created by zhujunlin on 2017/7/11.
 */

@SuppressLint("AppCompatCustomView")
public class RoundCornerImageView extends ImageView {

    private Path mClipPath;
    private RectF mRectF;
    private static final float DEFAULT_ROUND_CORNER_RX = 20.0f;
    private static final float DEFAULT_ROUND_CORNER_RY = 20.0f;
    private float mRx = DEFAULT_ROUND_CORNER_RX;
    private float mRy = DEFAULT_ROUND_CORNER_RY;

    public RoundCornerImageView(Context context) {
        this(context, null);
        init();
    }

    public RoundCornerImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public RoundCornerImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerImageView, defStyleAttr, 0);
        mRx = a.getFloat(R.styleable.RoundCornerImageView_round_corner_rx, DEFAULT_ROUND_CORNER_RX);
        mRy = a.getFloat(R.styleable.RoundCornerImageView_round_corner_ry, DEFAULT_ROUND_CORNER_RY);
        a.recycle();

        init();
    }

    private void init() {
        mClipPath = new Path();
        mRectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int w = this.getWidth();
        int h = this.getHeight();
        mRectF.left = 0;
        mRectF.top = 0;
        mRectF.right = w;
        mRectF.bottom = h;
        mClipPath.addRoundRect(mRectF, mRx, mRy, Path.Direction.CW);
        canvas.clipPath(mClipPath);
        super.onDraw(canvas);
    }
}
