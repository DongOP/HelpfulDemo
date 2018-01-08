package com.dong.request;

import android.content.Context;
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
    private Button mDeleteUserBtn;
    private Switch mSwitchBtn;
    private String mTokenId;
    private static final String SYNC_BIND_MODE = "SYNC_BIND";
    private static final String DELETE_USER_MODE = "DELETE_USER";

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
        mDeleteUserBtn = (Button) findViewById(R .id.delete_wx_user);

        mPutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTokenIdAndPut(SYNC_BIND_MODE);
            }
        });

        mDeleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTokenIdAndPut(DELETE_USER_MODE);
            }
        });
    }

    /**
     * 获取微信服务器的tokenId
     * @param mode 根据不同的操作模式进行其对应的后续操作
     */
    private void getTokenIdAndPut(final String mode) {
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

                        if (SYNC_BIND_MODE.equals(mode)) {
                            // put绑定模式的状态
                            putBindStatus(mTokenId); // mBindStatusSwitch.isChecked()
                        } else if (DELETE_USER_MODE.equals(mode)){
                            // 开始删除用户
                            deleteWechatUser(mTokenId, "owUvU0t2EYOk2T29aR4smBOy_HN0"); //openId
                        }

                        Utils.logd(TAG, "请求tokenId成功。mode=" + mode + ", 服务器返回的结果 result=" + result);
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

    private void deleteWechatUser(String tokenId, String openId) {
        String wxId = readWXDeviceId();
        if (null == wxId) {
            Utils.loge(TAG, "删除微信用户失败，需先获取微信二维码。");
            return;
        }
        // 构建请求数据
        Map<String, Object> uploadData = new HashMap<String, Object>();
        uploadData.put("scopeId", "QJaWfWI9fyI"); // 固定的字符串 QJaWfWI9fyI
        uploadData.put("deviceId", wxId);
        uploadData.put("openId", openId);
        String requestUrl = Utils.WXALBUM_BASE_URL + "QJaWfWI9fyI/" + "userdevices/" + wxId + "/" + openId;
        Utils.logd(TAG, "delete WeChat User, requestUrl=" + requestUrl);

        OkhttpUtil.get().doDeleteUser(requestUrl, tokenId, uploadData, new OkhttpUtil.WXCallBack() {
            @Override
            public void onRequestComplete(String result) {
                // 当返回数据为空时，表示解绑成功
                if (null == result || "".equals(result)) {
                    Utils.logd(TAG, "delete 微信相框用户成功,result=" + result);
//                    MessageService.getInstance(mContext).deleteUserByOpenId(openid);
//                    mContext.sendBroadcast(new Intent(Constants.ACTION_UPDATE_USER));
                } else {
                    Utils.loge(TAG, "delete 微信相框用户失败,result=" + result);
                }
            }

            @Override
            public void onRequestWithResponse(byte[] response) {

            }

            @Override
            public void onRequestFail(String reason) {
                Utils.logd(TAG, "delete 微信相框用户失败，请检查网络! reason=" + reason);
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
