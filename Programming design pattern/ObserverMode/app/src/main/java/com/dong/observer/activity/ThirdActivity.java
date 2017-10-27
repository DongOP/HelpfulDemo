package com.dong.observer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dong.observer.R;
import com.dong.observer.listener.ObserverListener;
import com.dong.observer.util.ObserverManager;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener, ObserverListener {

    private TextView mTvShow;
    private Button mBtnThird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        initView();
    }

    private void initView() {
        mTvShow = (TextView) findViewById(R.id.third_tv);
        mBtnThird = (Button) findViewById(R.id.third_btn);

        mBtnThird.setOnClickListener(this);
        //注册
        ObserverManager.getInstance().add(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.third_btn) {
            ObserverManager.getInstance().notifyObserver("观察者请刷新信息");

        }
    }

    @Override
    public void observerUpdate(String Content) {
        mTvShow.setText(Content);
    }
}
