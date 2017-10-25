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

public class SecondActivity extends AppCompatActivity implements View.OnClickListener, ObserverListener {

    private TextView mTvShow;
    private Button mBtnStartThird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initView();
    }

    private void initView() {
        mTvShow = (TextView) findViewById(R.id.second_tv);
        mBtnStartThird = (Button) findViewById(R.id.second_btn);

        mBtnStartThird.setOnClickListener(this);
        //注册
        ObserverManager.getInstance().add(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.second_btn) {
            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void observerUpdate(String Content) {
        mTvShow.setText(Content);
    }
}
