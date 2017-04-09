package com.view.buttonripple.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Dong on 2017/4/9.
 */

public class CustomView extends RelativeLayout{

    final static String MATERIALDESIGNXML = "http://schemas.android.com/apk/res-auto";
    final static String ANDROIDXML = "http://schemas.android.com/apk/res/android";

    int beforeBackground;
    // Indicate if user touched this view the last time
    public boolean isLastTouch = false;
    public boolean animation = false;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        invalidate();
    }

    @Override
    protected void onAnimationStart() {
        super.onAnimationStart();

        animation = true;
    }

    @Override
    protected void onAnimationEnd() {
        super.onAnimationEnd();

        animation = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (animation) {
            invalidate();
        }
    }
}
