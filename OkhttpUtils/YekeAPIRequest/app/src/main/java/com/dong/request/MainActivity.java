package com.dong.request;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.dong.request.util.OkhttpUtil;
import com.dong.request.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mShowTv;
    private Button mPutBtn;
    private Switch mSwitchBtn;
    private String mTokenId;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
    }

    private void initView() {
        mShowTv = (TextView) findViewById(R.id.show);
        mPutBtn = (Button) findViewById(R.id.put_btn);
        mSwitchBtn = (Switch) findViewById(R.id.switch_button);

        mPutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTokenIdAndPut();
            }
        });
    }

    private void getTokenIdAndPut() {
        // 发送获取tokenId的请求
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", Utils.YEKE_WXALBUM_USER);
        map.put("password", Utils.YEKE_WXALBUM_PSD);

        OkhttpUtil.get().doPostWithParam(Utils.LOGIN_YEKE_WXALBUM_URL, OkhttpUtil.MEDIA_TYPE_JSON, map, new OkhttpUtil.WXCallBack() {
            @Override
            public void onRequestComplete(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.optString("httpErrorCode").equals("500")) {
                        Utils.loge(TAG, "授权失败,可能密码账号错误 result=" + jsonObject.toString());
                    } else {
                        mTokenId = jsonObject.optString("tokenId");
                        // put绑定模式的状态
                        putBindStatus(mTokenId); // mBindStatusSwitch.isChecked()

                        Utils.logd(TAG, "请求tokenId成功，开始put绑定模式状态。服务器返回的结果 result=" + result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onRequestWithResponse(byte[] response) {

            }

            @Override
            public void onRequestFail(String reason) {
                Utils.logd(TAG, "onRequest tokenId fail: " + reason);
            }
        });
    }

    private void putBindStatus(String tokenId) {
        String wxId = readWXDeviceId();
        if (null == wxId) {
            Utils.loge(TAG, "微信端绑定模式同步失败，需先获取微信二维码。");
            return;
        }
        // 发送获取tokenId的请求
        Map<String, Object> uploadData = new HashMap<String, Object>();
        uploadData.put("clientId", wxId);
        uploadData.put("islock", mSwitchBtn.isChecked());

        String requestUrl = Utils.WXALBUM_BASE_URL + "deviceids/" + wxId + "?islock=" + mSwitchBtn.isChecked(); // islock = mBindStatusSwitch.isChecked()
        // https://dev.izhiju.cn/api/v1/deviceids/123456?islock=false
        Utils.logd(TAG, "put 微信相框绑定模式 doPut: requestUrl=" + requestUrl);

        OkhttpUtil.get().doPut(requestUrl, tokenId, uploadData, new OkhttpUtil.WXCallBack() {
            @Override
            public void onRequestComplete(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.optString("httpErrorCode").equals("500")) {
                        Utils.loge(TAG, "doPut 同步绑定模式失败,result=" + result);
                    } else {
                        Utils.logd(TAG, "doPut 同步绑定模式成功,result=" + result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Utils.loge(TAG, "doPut 同步出错,result=" + result);
                }
            }

            @Override
            public void onRequestWithResponse(byte[] response) {
            }

            @Override
            public void onRequestFail(String reason) {
                Utils.logd(TAG, "put 微信端绑定模式失败，请检查网络! reason=" + reason);
            }
        });
    }

    // 读取微信设备号
    public String readWXDeviceId() {
        try{
//            Utils.logd(TAG, "读取系统的微信设备号 wxId=" + Settings.System.getString(this.getContentResolver(), "set.mqtt.wx.id"));
            return "100000018";
        } catch (Exception e) {
            return null;
        }
    }
}
