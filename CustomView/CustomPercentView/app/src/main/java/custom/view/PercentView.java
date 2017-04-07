package custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 圆形 显示百分比
 *
 * Created by Dong on 2017/4/7 0007.
 */

public class PercentView extends View{

    private Paint mPaint;
    private RectF oval;
    private static final String TAG = "PercentView";
    private int mBackgroundColor = Color.LTGRAY;
    private int mProgressColor = Color.BLUE;
    private float mRadius;
    private int mProgress;
    private float mCenterX = 0;
    private float mCenterY = 0;
    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int CENTER = 2;
    public static final int RIGHT = 3;
    public static final int BOTTOM = 4;
    private int mGravity = CENTER;
    private RectF mRecF; //用于定义的圆弧的形状和大小的界限

    public PercentView(Context context) {
        super(context);
        init();
    }


    public PercentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams(context, attrs);
    }


    public PercentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context, attrs);
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mRecF = new RectF();
    }


    private void initParams(Context context, AttributeSet attrs) {

        init();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PercentView);
        if (typedArray != null) {
            mBackgroundColor = typedArray.getColor(R.styleable.PercentView_percent_background_color, Color.LTGRAY);
            mProgressColor = typedArray.getColor(R.styleable.PercentView_percent_progress_color, Color.BLUE);
            mRadius = typedArray.getDimension(R.styleable.PercentView_percent_circle_radius, 0);
            mProgress = typedArray.getInt(R.styleable.PercentView_percent_circle_progress, 0);
            mGravity = typedArray.getInt(R.styleable.PercentView_percent_circle_gravity, CENTER);

            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = getWidth();
        int height = getHeight();
        mCenterX = width / 2;
        mCenterY = height / 2;
        switch (mGravity) {
            case LEFT:
                mCenterX = mRadius + getPaddingLeft();
                break;
            case TOP:
                mCenterY = mRadius + getPaddingTop();
                break;
            case CENTER:
                break;
            case RIGHT:
                mCenterY = width - mRadius - getPaddingRight();
                break;
            case BOTTOM:
                mCenterY = height - mRadius - getPaddingBottom();
                break;
        }
        float left = mCenterX - mRadius;
        float top = mCenterY - mRadius;
        float right = mCenterX + mRadius;
        float bottom = mCenterY + mRadius;
        mRecF.set(left, top, right, bottom);
        Log.e(TAG, "onMeasure: left:" + left + ", top:" + top + ", right:" + right + ", bottom:" + bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(mBackgroundColor);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);

        mPaint.setColor(mProgressColor);
        double percent = mProgress  * 1.0 / 100;
        int angle = (int) (percent * 360);
        canvas.drawArc(mRecF, 270, angle, true, mPaint);// 根据进度画圆弧
    }
}
