package eagle.dynamicstoragereceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

    private StorageReceiver mStorageReceiver;
    private TextView mMainText;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        resgisterStorageReceiver();
    }

    private void initView() {
        mMainText = (TextView) findViewById(R.id.main_text);
    }

    class StorageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, final Intent intent) {
            String action = intent.getAction();

            if (Intent.ACTION_MEDIA_EJECT.equals(action) || Intent.ACTION_MEDIA_MOUNTED.equals(action)) {

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("MainActivity", "post UI...action:" + intent.getAction());
                        mMainText.setText("ExternalDeviceReceiver...action:" + intent.getAction());
                    }
                });
            }
//            else if (action.equals(ACTION_MEDIA_MOUNTED)) {
//                Log.e("MainActivity", "ACTION_MEDIA_MOUNTED...action:" + intent.getAction());
//            } else if (action.equals(Intent.ACTION_MEDIA_UNMOUNTED)) {
//                Log.e("MainActivity", "ACTION_MEDIA_UNMOUNTED...action:" + intent.getAction());
//            } else if (action.equals(Intent.ACTION_MEDIA_SCANNER_STARTED)) {
//                Log.e("MainActivity", "ACTION_MEDIA_SCANNER_STARTED...action:" + intent.getAction());
//            } else if (action.equals(Intent.ACTION_MEDIA_SCANNER_FINISHED)) {
//                Log.e("MainActivity", "ACTION_MEDIA_SCANNER_FINISHED...action:" + intent.getAction());
//            } else if (action.equals(Intent.ACTION_MEDIA_SHARED)) {
//                Log.e("MainActivity", "ACTION_MEDIA_SHARED...action:" + intent.getAction());
//            } else {
//                Log.e("MainActivity", "else...action:" + intent.getAction());
//            }
        }
    }


    private void resgisterStorageReceiver() {
        mStorageReceiver = new StorageReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);//表明sd对象是存在并具有读/写权限
        intentFilter.addAction(Intent.ACTION_MEDIA_EJECT);  //物理的拔出 SDCARD
        intentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_REMOVED);
        intentFilter.addAction(Intent.ACTION_MEDIA_SHARED);
        intentFilter.addAction(Intent.ACTION_MEDIA_BAD_REMOVAL);
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
        intentFilter.addAction(Intent.ACTION_MEDIA_CHECKING);
        intentFilter.addAction(Intent.ACTION_MEDIA_NOFS);
        intentFilter.addAction(Intent.ACTION_MEDIA_BUTTON);
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intentFilter.addDataScheme("file"); // 必须要有此行，否则无法收到广播
        registerReceiver(mStorageReceiver, intentFilter);
    }

    private void unresgisterStorageReceiver() {
        if (null != mStorageReceiver) {
            unregisterReceiver(mStorageReceiver);
        }
    }

    @Override
    protected void onStop() {
        unresgisterStorageReceiver();
        super.onStop();
    }
}
