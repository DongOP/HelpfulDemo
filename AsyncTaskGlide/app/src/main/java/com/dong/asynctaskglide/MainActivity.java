package com.dong.asynctaskglide;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.dong.asynctaskglide.asynctask.DownloadAsyncTask;

/**
 * 使用AsyncTask、Glide下载、加载图片
 */
public class MainActivity extends Activity implements View.OnClickListener{

    private Button mDownloadBTN;
    private ProgressBar myProgressBar;
    private ImageView myImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mDownloadBTN = (Button) findViewById(R.id.download_btn);
        myProgressBar = (ProgressBar) findViewById(R.id.myProgressBar);
        myImageView = (ImageView) findViewById(R.id.myImageView);

        mDownloadBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.download_btn:
                new DownloadAsyncTask(MainActivity.this, myProgressBar, myImageView).execute(null, null, null);
                break;
            default:
                break;
        }
    }

}
