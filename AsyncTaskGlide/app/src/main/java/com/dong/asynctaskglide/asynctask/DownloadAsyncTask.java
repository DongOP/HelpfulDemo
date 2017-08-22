package com.dong.asynctaskglide.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;
import com.dong.asynctaskglide.R;

import java.io.File;

/**
 * Created by Dong on 2017/8/22 0022.
 *
 * AsyncTask<Params, Progress, Result>是一个抽象类
 * Params：启动任务时输入参数的类型
 * Progress:后台任务执行中返回进度条的类型
 * Result:后台任务执行完成后返回结果的类型
 *
 *
 * doInBackground：必须重写的方法，异步执行后台线程将要完成的任务;
 * onPreExecute：后台执行耗时操作前被调用，通常完成一些初始化操作;
 * onPostExecute：当 doInBackground执行完成后，会自动调用该方法，并将doInBackground执行返回的结果传递给给方法;
 * onProgressUpdate：在doInBackground里调用publishProgress方法，更新任务的执行进度后，就会触发该方法。
 *
 */

public class DownloadAsyncTask extends AsyncTask <Void, Void, Void> {

    private final static String PICTURE_URL = "http://p1.pstatp.com/large/166200019850062839d3";
    private Context myContext;
    private ProgressBar myProgressBar;
    private ImageView myImageView;
    private static File mDownFile = null;

    public DownloadAsyncTask (Context context, ProgressBar progressBar, ImageView imageView) {
        myContext = context;
        myProgressBar = progressBar;
        myImageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        //在进行后台任务前，显示进度条
        myProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 下载网络图片
     */
    @Override
    protected Void doInBackground(Void... params) {
        FutureTarget<File> target = Glide.with(myContext)
                .load(PICTURE_URL)
                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
        try {
            mDownFile = target.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 加载图片
     */
    @Override
    protected void onPostExecute(Void bitmap) {
        //在后台任务执行完成后，把进度条隐藏，设置doInBackground返回的结果图片设置给myImageView
        myProgressBar.setVisibility(View.GONE);
        if (null == mDownFile) {
            Toast.makeText(myContext, "图片下载失败...请检查网络！", Toast.LENGTH_SHORT).show();
            return;
        }
        // 加载已经下好的本地图片
        Glide.with(myContext)
                .load(mDownFile.getPath())
//                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(myImageView);
    }
}
