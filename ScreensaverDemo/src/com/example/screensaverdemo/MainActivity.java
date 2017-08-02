package com.example.screensaverdemo;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Context mContext;
	Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		handler.postDelayed(runnable, 1000 * 5);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		handler.removeCallbacks(runnable);
	}

	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			// 用户5秒没操作了

			Intent i = new Intent();
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.setClass(mContext, ScreenSaverActivity.class);
			mContext.startActivity(i);

		}
	};

	public boolean onTouchEvent(android.view.MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: { // 手指下来的时候,取消之前绑定的Runnable

			handler.removeCallbacks(runnable);
			break;
		}
		case MotionEvent.ACTION_UP: { // 手指离开屏幕，发送延迟消息 ，5秒后执行

			handler.postDelayed(runnable, 1000 * 5);

			break;
		}
		}
		return super.onTouchEvent(event);
	};

}
