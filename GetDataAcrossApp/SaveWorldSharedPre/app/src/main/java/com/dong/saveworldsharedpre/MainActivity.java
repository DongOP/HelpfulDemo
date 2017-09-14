package com.dong.saveworldsharedpre;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    public static final String PREFER_NAME = "BindStatus";
    public static int MODE = Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE;
    private Button mSaveInfoBtn1;
    private Button mSaveInfoBtn2;
    private TextView mShowInfoTv;
    private boolean mInfoOne;
    private boolean mInfoTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mSaveInfoBtn1 = (Button) findViewById(R.id.btn_save_1);
        mSaveInfoBtn2 = (Button) findViewById(R.id.btn_save_2);
        mShowInfoTv = (TextView) findViewById(R.id.tv_show);

        mSaveInfoBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSharedPreferences1();

                updateInfoOne();
            }
        });

        mSaveInfoBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSharedPreferences2();

                updateInfoTwo();
            }
        });
    }

    private void updateInfoOne() {
        mShowInfoTv.setText("1、保存的信息=" + mInfoOne);
    }

    private void updateInfoTwo() {
        mShowInfoTv.setText("2、保存的信息=" + mInfoTwo);
    }

    private void saveSharedPreferences1() {
        // TODO Auto-generated method stub
        SharedPreferences share = getSharedPreferences(PREFER_NAME, MODE);
        SharedPreferences.Editor editor = share.edit();

        mInfoOne = true;
        editor.putBoolean("checkBindStatus", mInfoOne);
        editor.commit();
    }

    private void saveSharedPreferences2() {
        // TODO Auto-generated method stub
        SharedPreferences share = getSharedPreferences(PREFER_NAME, MODE);
        SharedPreferences.Editor editor = share.edit();

        mInfoTwo = false;
        editor.putBoolean("checkBindStatus", mInfoTwo);
        editor.commit();
    }
}
