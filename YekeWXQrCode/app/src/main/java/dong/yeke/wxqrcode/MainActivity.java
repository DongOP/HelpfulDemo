package dong.yeke.wxqrcode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
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
import dong.yeke.wxqrcode.util.PictureUtil;
import dong.yeke.wxqrcode.util.SdCardUtil;

/**
 * 获取微信二维码
 * <p>
 * 1、上传 user、psd，获取到 tokenId
 * 2、获取授权
 * 3、上传 width、sceneId，获取到二维码的数据
 * 4、将二维码数据转换成图片格式，并显示出来
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mSenceIdTV;
    private Button mUpdateCodeBTN;
    private ImageView mQrCodePic;
    private static final String TAG = "MainActivity";
    private Handler mHandler = new Handler();

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
        mQrCodePic.setImageBitmap(getLocalWXQrCode());
    }

    private Bitmap getLocalWXQrCode() {

        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_wx_qr_code:
                activeWXAlbum();
                break;

            default:
                break;
        }
    }

    // 开始注册激活微信相框。目的更新微信二维码
    private void activeWXAlbum() {
        // 发送获取tokenId的请求
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", Constants.YEKE_WXALBUM_USER);
        map.put("password", Constants.YEKE_WXALBUM_PSD);

        doGetUserTokenId(map);
    }

    private void doGetUserTokenId(Map<String, Object> params) {
        OkHttpUtils.getInstance().doPostWithParam(Constants.LOGIN_YEKE_WXALBUM_URL, OkHttpUtils.MEDIA_TYPE_JSON, params, new OkHttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.optString("httpErrorCode").equals("500")) {
                        Log.e(TAG, "可能密码账号错误, 请求结果失败= " + jsonObject.toString());
                    } else {
                        mTokenId = jsonObject.optString("tokenId");
                        // 请求微信二维码 URL, 宽度300
                        doRequestQRCode(150, getSceneId());

                        Log.d(TAG, "请求成功，服务器获取的token Id=" + mTokenId);
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
                Log.d(TAG, "onRequest tokenId fail: " + reason);
            }
        });
    }

    // SceneId --> 9位数的int
    private int getSceneId() {

        return 123456666;
    }

    /**
     * 请求微信二维码 URL
     *
     * @param width   the widths for the new WxQrcodesAPI to be created
     * @param sceneId the sceneId for the new WxQrcodesAPI to be created
     */
    private void doRequestQRCode(int width, int sceneId) {
        OkHttpUtils.getInstance().doGetQrCode(Constants.YEKE_WXQRCODE_URL, OkHttpUtils.MEDIA_TYPE_IMAGE, width, sceneId, mTokenId, new OkHttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                Log.d(TAG, "onRequestComplete...获取 result=" + result);
            }

            @Override
            public void onRequestWithResponse(byte[] response) {
                if (null != response) {
                    Bitmap bitmap = null;
                    try {
                        // 将获取到的图片数据保存到本地
                        SdCardUtil.writeToFile(response, SdCardUtil.getQrCodeImageDir(), "QrCode.txt");
                        // 将图片数据转换成bitmap
                        bitmap = PictureUtil.getPicFromBytes(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(TAG, "保存图片数据出错 Exception =" + e.getMessage());
                    }
                    if (null != bitmap) {
                        updateQrCode(bitmap);
                    } else {
                        Log.e(TAG, "二进制流转换成bitmap是空！");
                    }
                } else {
                    Log.e(TAG, "返回的response是空！");
                }
            }

            @Override
            public void onRequestFail(String reason) {
                Log.d(TAG, "onRequest WxQrcodesAPI fail: " + reason);
            }
        });
    }

    private void updateQrCode(final Bitmap bitmap) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mQrCodePic.setImageBitmap(bitmap);
            }
        });
    }


}
