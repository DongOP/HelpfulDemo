package com.dong.ontoucheventdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {

    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    private float x1 = 0;
    private float x2 = 0;
    private float y1 = 0;
    private float y2 = 0;
    private TextView mShowNum;
    private RelativeLayout mRelLayout;
    private int mNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mNum = 0;
        mShowNum = (TextView) findViewById(R.id.text);
        mRelLayout = (RelativeLayout) findViewById(R.id.activity_main);

        // 初始值
        mShowNum.setText("0");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //继承了Activity的onTouchEvent方法，直接监听点击事件
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
            y1 = event.getY();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            //当手指离开的时候
            x2 = event.getX();
            y2 = event.getY();
            /**if (y1 - y2 > 50) {
             ToastUtils.showToast(MainActivity.this, "向上滑");
             } else if (y2 - y1 > 50) {
             ToastUtils.showToast(MainActivity.this, "向下滑");
             } else **/
            if (x1 - x2 > 60) {
//                ToastUtils.showToast(MainActivity.this, "向左滑");
                mNum++;

                String msgLeft = mNum + "";
                mShowNum.setText(msgLeft);
                mRelLayout.setBackgroundColor(Color.parseColor(getRandColorCode()));
            } else if (x2 - x1 > 60) {
//                ToastUtils.showToast(MainActivity.this, "向右滑");
                mNum--;
                String msgRight = mNum + "";
                if (mNum > 0) {
                    mShowNum.setText(msgRight);
                    mRelLayout.setBackgroundColor(Color.parseColor(getRandColorCode()));
                } else {
                    // 小于1后颜色和文字向右不再变化，归0初始
                    mNum = 0;
                    mShowNum.setText("0");
                    mRelLayout.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 获取十六进制的颜色代码.例如  "#6E36B4"
     *
     * @return String
     */
    public static String getRandColorCode() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;

        Log.e("dong", "色值=" + r + g + b);
        return "#" + r + g + b;
    }

}
