package com.dong.readworldsharepre;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final String PREFERENCE_PACKAGE = "com.dong.saveworldsharedpre";
    private Context mContext;
    private Button mReadAppSpBtn;
    private TextView mShowReadTV;
    boolean isBind;
    public static final String PREFERENCE_NAME = "BindStatus";
    public static int MODE = Context.MODE_MULTI_PROCESS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        try {
            mContext = MainActivity.this.createPackageContext(PREFERENCE_PACKAGE, Context.CONTEXT_IGNORE_SECURITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        mReadAppSpBtn = (Button) findViewById(R.id.update_info_btn);
        mShowReadTV = (TextView) findViewById(R.id.show_tv);

        mReadAppSpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readAppSP();

                mShowReadTV.setText("读取到的信息=" + isBind);
            }
        });
    }

    private void readAppSP() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFERENCE_NAME, MODE);
        // 默认状态
        boolean defStatus = false;
        isBind = sharedPreferences.getBoolean("checkBindStatus", defStatus);
    }
}
