package com.dong.bootbroadcastreciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Dong on 2017/12/29 0029.
 */

public class BootBroadcastReceiver extends BroadcastReceiver {
    // 微信用户数据信息  广播action
    public static final String WX_USER_INFO_ACTION = "net.bestidear.wechat.userinfo";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WX_USER_INFO_ACTION.equals(action)) {
            try {
                formatUserInfo(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("UserInfo", "test 广播接收微信消息 =" + formatUserInfo(intent));
            // 发送 格式化后的数据
//            sendNewUserMsg(context, wxServiceMsg);
        }

    }

    private String formatUserInfo(Intent intent) {
        HashMap<String, String> map = new HashMap<String, String>();
        JSONObject jb = null;
        try {
            map.put("nickname", intent.getStringExtra("nickname"));
            map.put("headimg", intent.getStringExtra("headimg"));
            map.put("openid", intent.getStringExtra("unionid"));
            map.put("msgtype", intent.getStringExtra("msgType"));
            jb = new JSONObject(map);
        } catch (Exception e){
            e.printStackTrace();
        }

        if (null == jb) {
            return "";
        }
        return jb.toString();
    }
}
