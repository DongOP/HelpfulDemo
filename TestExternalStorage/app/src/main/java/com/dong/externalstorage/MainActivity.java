package com.dong.externalstorage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dong.externalstorage.util.StorageUtils;

import java.io.File;

public class MainActivity extends Activity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private Context mContext;

    // SD存储
    private TextView mSDTotalSpace;
    private TextView mSDFreeSpace;
    private String mSDPath;
    private String mSDTotalSize;
    // USB存储
    private TextView mUSBTotalSpace;
    private TextView mUSBFreeSpace;
    private String mUSBPath;
    private String mUSBTotalSize;

    private Button mUpdateBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        initView();
        initData();
    }

    private void initView() {
        mSDTotalSpace = (TextView) findViewById(R.id.sd_total_tv);
        mSDFreeSpace = (TextView) findViewById(R.id.sd_free_tv);

        mUSBTotalSpace = (TextView) findViewById(R.id.usb_total_tv);
        mUSBFreeSpace = (TextView) findViewById(R.id.usbfree_tv);
        mUpdateBTN = (Button) findViewById(R.id.btn_update);

        mUpdateBTN.setOnClickListener(this);
    }

    private void initData() {

        mSDPath = StorageUtils.getStoragePath(mContext, false);
        mUSBPath = StorageUtils.getStoragePath(mContext, true);
        if (null != mSDPath) {
            Log.e(TAG, "mSDPath=" + mSDPath);
        } else {
            Log.e(TAG, "SD卡路径为空！");
        }

        if (null != mUSBPath) {
            Log.e(TAG, "mUSBPath=" + mUSBPath);
        } else {
            Log.e(TAG, "USB卡路径为空！");
        }

        // 显示SD卡总容量
        if (!"".equals(mSDPath) && null != mSDPath) {
            File file = new File(mSDPath);
            if (StorageUtils.caclFileSize(file).size() > 0) {
                mSDTotalSize = StorageUtils.caclFileSize(file).get(0).toString().trim();
            }
            mSDTotalSpace.setText(mSDTotalSize);
        } else {
            mSDTotalSpace.setText("SD路径为空!");
        }

        // 显示USB总容量
        if (!"".equals(mUSBPath) && null != mUSBPath) {
            File usbFile = new File(mUSBPath);
            if (StorageUtils.caclFileSize(usbFile).size() > 0) {
                mUSBTotalSize = StorageUtils.caclFileSize(usbFile).get(0).toString().trim();
            }
            mUSBTotalSpace.setText(mUSBTotalSize);
        } else {
            mUSBTotalSpace.setText("USB路径为空...");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                initData();
                break;
            default:
                break;
        }
    }
}
