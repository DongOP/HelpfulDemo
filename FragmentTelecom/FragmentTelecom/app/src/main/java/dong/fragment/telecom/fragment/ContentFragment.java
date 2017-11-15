package dong.fragment.telecom.fragment;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dong.fragment.telecom.R;
import dong.fragment.telecom.util.Constants;

import static dong.fragment.telecom.R.id.show_content;

/**
 * Created by Dong on 2017/11/15 0015.
 */

public class ContentFragment extends Fragment {

    private View mContentFragment;
    private TextView mContentTV;
    private LocalBroadcastManager mLocalBroadcastManager;
    private LocalReceiver mLocalReceiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentFragment = inflater.inflate(R.layout.fragment_content, container, false);
        mContentTV = (TextView) mContentFragment.findViewById(show_content);

        initLocalBroadcastManager();
        return mContentFragment;
    }

    // 初始化 LocalBroadcastManager 和 LocalReceiver
    private void initLocalBroadcastManager() {
        mLocalReceiver = new LocalReceiver();
        if (null == getActivity()) {
            return;
        }
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
    }

    public void updateContent(String content) {
        mContentTV.setText(content);
    }

    private class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String content = intent.getStringExtra(Constants.LOCAL_BROADCAST_MSG_KEY);
            if (Constants.LOCAL_BROADCAST_ACTION.equals(intent.getAction())) {
                mContentTV.setText(content);
            }
            Log.d("LocalReceiver", "onReceive 接收的数据=" + content);
        }
    }

    // 注册本地广播接收器
    private void registerLocalBroadCast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.LOCAL_BROADCAST_ACTION);
        mLocalBroadcastManager.registerReceiver(mLocalReceiver, filter);
    }

    // 注销本地广播接收器
    private void unregisterLocalBroadCast() {
        mLocalBroadcastManager.unregisterReceiver(mLocalReceiver);
    }

    @Override
    public void onStart() {
        super.onStart();

        registerLocalBroadCast();
    }

    @Override
    public void onStop() {
        super.onStop();

        unregisterLocalBroadCast();
    }


}
