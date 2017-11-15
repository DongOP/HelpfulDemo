package dong.fragment.telecom.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import dong.fragment.telecom.R;
import dong.fragment.telecom.event.Event;
import dong.fragment.telecom.util.Constants;

/**
 * Created by Dong on 2017/11/15 0015.
 */

public class TitleFragment extends Fragment {

    private View mTitleFragment;
    private TextView mTv;
    private Button mInterfaceBtn;
    private Button mLocalBroadCastBtn;
    private Button mEventBusBtn;
    OnButtonClickListener mCallback;
    private LocalBroadcastManager mLocalBroadcastManager;

    public interface OnButtonClickListener {
        void OnButtonClickCallback(Object object);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mTitleFragment = inflater.inflate(R.layout.fragment_title, container, false);

        initView();
        initData();

        return mTitleFragment;
    }

    // 通过本地广播传输消息给Fragment
    private void sendContentBroadcast() {
        if (null == getActivity()) {
            return;
        }
        // 初始化
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        Intent intent = new Intent(Constants.LOCAL_BROADCAST_ACTION);
        intent.putExtra(Constants.LOCAL_BROADCAST_MSG_KEY, "本地广播传输的信息");
        mLocalBroadcastManager.sendBroadcast(intent);
        Log.d("LocalReceiver", "sendContentBroadcast 发送本地广播，消息key=" + Constants.LOCAL_BROADCAST_MSG_KEY);
    }

    private void initView() {
        mTv = (TextView) mTitleFragment.findViewById(R.id.title_tv);
        mInterfaceBtn = (Button) mTitleFragment.findViewById(R.id.interface_btn);
        mLocalBroadCastBtn = (Button) mTitleFragment.findViewById(R.id.broadcast_btn);
        mEventBusBtn = (Button) mTitleFragment.findViewById(R.id.eventbus_btn);
    }

    private void initData() {
        mInterfaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.OnButtonClickCallback("通过接口传输Fragment信息");
            }
        });

        mLocalBroadCastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendContentBroadcast();
            }
        });

        mEventBusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EventBus发送消息
                EventBus.getDefault().post(new Event.MessageEvent("post EventBus message."));
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (null != context) {
            try {
                mCallback = (OnButtonClickListener) context;
            } catch (Exception e) {
//                e.printStackTrace();
                throw new ClassCastException(context.toString()
                        + " 内容接收端的Fragment未实现OnButtonClickListener");
            }
        }

    }

}
