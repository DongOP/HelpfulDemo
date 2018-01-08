package com.dong.mockwxuserinfo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // 微信用户数据信息  广播action
    public static final String WX_USER_INFO_ACTION = "net.bestidear.wechat.userinfo";
    private Button mSendUserInfo;
    private String mNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mSendUserInfo = (Button) findViewById(R.id.send_msg);
        mSendUserInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String time = new SimpleDateFormat("HH:mm:ss:SSSS", Locale.CHINA).format(new Date());
        switch (v.getId()) {
            case R.id.send_msg:
                mNickname = "nickname_" + time;
                mockWXUserInfo();
                break;
            default:
                break;
        }
    }

    private void mockWXUserInfo() {
        Intent intent = new Intent(WX_USER_INFO_ACTION);
        Bundle bundle = new Bundle();
        bundle.putString("nickname", mNickname);
        bundle.putString("headimg", "headimg_url");
        bundle.putString("unionid", "unionid");
        bundle.putString("msgType", "bind");
        //
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("nickname", mNickname);
        map.put("headimg", "headimg_url");
        map.put("unionid", "unionid");
        map.put("msgType", "bind");
        JSONObject jb = new JSONObject(map);
        //
        intent.putExtras(bundle);
        sendBroadcast(intent);

        try {
            Log.e("Broadcast", "发送消息=" + jb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
