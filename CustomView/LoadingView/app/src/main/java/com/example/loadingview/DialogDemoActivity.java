package com.example.loadingview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.loadingview.view.ShapeLoadingDialog;


public class DialogDemoActivity extends Activity {

    private ShapeLoadingDialog shapeLoadingDialog;

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo);

        initLoadingView();

        initClick();
    }

    private void initClick() {

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shapeLoadingDialog.show();
                mHandler.postDelayed(new LoadingThread(), 1000);
            }
        });

        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shapeLoadingDialog.show();
                mHandler.postDelayed(new LoadingThread(), 6000);
            }
        });
    }

    private void initLoadingView() {
        shapeLoadingDialog = new ShapeLoadingDialog(this);
        shapeLoadingDialog.setLoadingText("加载中...");
    }

    class LoadingThread implements Runnable {

        @Override
        public void run() {
            shapeLoadingDialog.dismiss();
        }
    }

}
