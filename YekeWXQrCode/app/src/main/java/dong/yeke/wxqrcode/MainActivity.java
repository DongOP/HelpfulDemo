package dong.yeke.wxqrcode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dong.yeke.wxqrcode.util.Constants;
import dong.yeke.wxqrcode.util.OkHttpUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // 必须是9位int整数
    private int mSenceIdNum;
    private TextView mSenceIdTV;
    private Button mUpdateCodeBTN;
    private ImageView mQrCodePic;
    private static final String TAG = "MainActivity";

    private String mTokenId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mSenceIdTV = (TextView) findViewById(R.id.scene_id);
        mUpdateCodeBTN = (Button) findViewById(R.id.update_wx_qr_code);
        mQrCodePic = (ImageView) findViewById(R.id.wx_qr_code);

        mUpdateCodeBTN.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_wx_qr_code:
                getUserTokenId();
                break;

            default:
                break;
        }
    }

    private void getUserTokenId() {
        // 发送获取tokenId的请求
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", Constants.YEKE_WXALBUM_USER);
        map.put("password", Constants.YEKE_WXALBUM_PSD);

        doGetUserTokenId(map);
    }

    private void doGetUserTokenId(Map<String, String> params) {
        OkHttpUtils.getInstance().doPostToken(Constants.LOGIN_YEKE_WXALBUM_URL, params, new OkHttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.optString("httpErrorCode").equals("500")) {
                        Log.e(TAG, "可能密码账号错误, get tokenId fail: " + jsonObject.toString());
                    } else {
                        mTokenId = jsonObject.optString("tokenId");
                        Log.d(TAG, "从服务器获取token Id=" + mTokenId);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onRequestFail(String reason) {
                Log.d(TAG, "onRequestFail tokenId fail: " + reason);
            }
        });
    }

    //获取ToKen
//    public void sendToKen(final OkHttpUtils.CallBack listener) {
//        new Thread() {
//            public void run() {
//                AuthenticationApi api = new AuthenticationApi();
//                UsernamePasswordCredentials body = new UsernamePasswordCredentials();
//                body.setUsername(Constants.YEKE_WXALBUM_USER);
//                body.setPassword(Constants.YEKE_WXALBUM_PSD);
//                AccessToken response = null;
//                try {
//                    response = api.loginUsernamePassword(body);
//                    Log.i("AccessToken", response.getTokenId());
//                    try {
//                        mTokenId = response.getTokenId();
//                        if (listener != null) {
//                            listener.onRequestComplete(response.getTokenId());
//                        }
//                    } catch (Exception e) {
//                        if (listener != null) {
//                            listener.onRequestFail(e.toString());
//                        }
//                    }
//                } catch (ApiException e) {
//                    e.printStackTrace();
//                    if (listener != null) {
//                        listener.onRequestFail(e.toString());
//                    }
//                }
//            }
//        }.start();
//    }


}
