package com.eagle.psdedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eagle.psdedittext.view.PasswordExitText;

public class MainActivity extends AppCompatActivity {

    private PasswordExitText mPsdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mPsdText = (PasswordExitText) findViewById(R.id.psd_ed);
    }
}
