package dong.fragment.telecom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import dong.fragment.telecom.event.Event;
import dong.fragment.telecom.fragment.ContentFragment;
import dong.fragment.telecom.fragment.TitleFragment;

public class MainActivity extends AppCompatActivity implements TitleFragment.OnButtonClickListener{

    private ContentFragment mContentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInterfaceView();
    }

    private void initInterfaceView() {
        mContentFragment = (ContentFragment)getFragmentManager().findFragmentById(R.id.fragment_content);
        if (null == mContentFragment) {
            mContentFragment = new ContentFragment();
        }
    }

    @Override
    public void OnButtonClickCallback(Object object) {
        mContentFragment.updateContent(object.toString());
    }

    // 通过EventBus的事件订阅，接收消息更新Fragment
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Event.MessageEvent event) {
        Log.d("EventBus", "onMessageEvent 接收的数据=" + event.getMsg());
        mContentFragment.updateContent(event.getMsg());
    }

    @Override
    protected void onStart() {
        super.onStart();

        // 注册 EventBus
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        // 注销 EventBus
        EventBus.getDefault().unregister(this);
    }
}
