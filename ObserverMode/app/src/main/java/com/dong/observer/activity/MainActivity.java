package com.dong.observer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dong.observer.R;
import com.dong.observer.listener.ObserverListener;
import com.dong.observer.util.ObserverManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ObserverListener{

    private TextView mTvShow;
    private Button mBtnStartSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mTvShow = (TextView) findViewById(R.id.main_tv);
        mBtnStartSecond = (Button) findViewById(R.id.main_btn);

        mBtnStartSecond.setOnClickListener(this);
        //注册
        ObserverManager.getInstance().add(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.main_btn) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void observerUpdate(String Content) {
        mTvShow.setText(Content);
    }
}
