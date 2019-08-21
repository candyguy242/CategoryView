package com.sky.categorydemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class RoundCornerImageView extends AppCompatImageView {

    private final int DEFAULT_RADIUS = 6;

    private final RectF mRoundRect = new RectF();
    private float mRadiusX = DEFAULT_RADIUS;
    private float mRadiusY = DEFAULT_RADIUS;
    private final Paint mMaskPaint = new Paint();
    private final Paint mZonePaint = new Paint();

    /**
     * 高宽比(已知宽，求高)
     */
    public float mHWRate = 0;
    /**
     * 宽高比(已知高，求宽)
     */
    public float mWHRate = 0;

    public RoundCornerImageView(Context context) {
        super(context);
        init();
    }

    public RoundCornerImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerImageView);
        mHWRate = array.getFloat(R.styleable.RoundCornerImageView_hwRate, 0);
        mWHRate = array.getFloat(R.styleable.RoundCornerImageView_whRate, 0);
        mRadiusX = array.getDimensionPixelSize(R.styleable.RoundCornerImageView_radiusX, DEFAULT_RADIUS);
        mRadiusY = array.getDimensionPixelSize(R.styleable.RoundCornerImageView_radiusY, DEFAULT_RADIUS);
        array.recycle();
    }

    public RoundCornerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerImageView);
        mHWRate = array.getFloat(R.styleable.RoundCornerImageView_hwRate, 0);
        mWHRate = array.getFloat(R.styleable.RoundCornerImageView_whRate, 0);
        mRadiusX = array.getDimensionPixelSize(R.styleable.RoundCornerImageView_radiusX, DEFAULT_RADIUS);
        mRadiusY = array.getDimensionPixelSize(R.styleable.RoundCornerImageView_radiusY, DEFAULT_RADIUS);
        array.recycle();
    }

    private void init() {
        mMaskPaint.setAntiAlias(true);
        mMaskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        mZonePaint.setAntiAlias(true);
        mZonePaint.setColor(Color.WHITE);
        mRadiusX = DEFAULT_RADIUS;
        mRadiusY = DEFAULT_RADIUS;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mHWRate > 0) {
            int width = getMeasuredWidth();
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int paddingRight = getPaddingRight();
            int paddingBottom = getPaddingBottom();
            int height = (int) (mHWRate * (width - paddingLeft - paddingRight) + paddingTop + paddingBottom);
            setMeasuredDimension(width, height);
        }else if(mWHRate > 0){
            int height = getMeasuredWidth();
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int paddingRight = getPaddingRight();
            int paddingBottom = getPaddingBottom();
            int width = (int) (mWHRate * (height - paddingTop - paddingBottom) + paddingLeft + paddingRight);
            setMeasuredDimension(width, height);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int w = getWidth();
        int h = getHeight();
        mRoundRect.set(0, 0, w, h);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.saveLayer(mRoundRect, mZonePaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawRoundRect(mRoundRect, mRadiusX, mRadiusY, mZonePaint);
        //
        canvas.saveLayer(mRoundRect, mMaskPaint, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        canvas.restore();
    }

}
