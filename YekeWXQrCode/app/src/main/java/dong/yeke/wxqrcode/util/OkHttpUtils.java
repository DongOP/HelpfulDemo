package dong.yeke.wxqrcode.util;

import org.json.JSONObject;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Dong on 2017/11/6 0006.
 */

public class OkHttpUtils {

    private static final OkHttpUtils mOkHttpUtils = new OkHttpUtils();
    private final OkHttpClient mOkHttpClient;
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpUtils() {
        X509TrustManager trustManager = new TrustAllCerts();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.sslSocketFactory(createSSLSocketFactory(trustManager), trustManager);
        builder.hostnameVerifier(new TrustAllHostnameVerifier());
        mOkHttpClient = builder.build();
    }

    public static OkHttpUtils getInstance() {
        return mOkHttpUtils;
    }

    public interface CallBack {
        void onRequestComplete(String result);

        void onRequestFail(String reason);
    }

    public void doGetAsyn(final String urlStr, final OkHttpUtils.CallBack callBack) {
        Request request = new Request.Builder().url(urlStr).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                if (callBack != null) {
                    callBack.onRequestFail("ioexception");
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (callBack != null) {
                        String result = response.body().string();
                        callBack.onRequestComplete(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 带参数的 post 请求
     *
     * @param urlStr   请求的url
     * @param params   参数
     * @param callBack 请求后的回调
     */
    public void doPostToken(final String urlStr, Map<String, String> params, final OkHttpUtils.CallBack callBack) {
        if (params == null) {
            throw new NullPointerException("params is null");
        }

        JSONObject jsonParams = new JSONObject(params);
        // 设置媒体类型
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, jsonParams.toString());
        Request request = new Request.Builder()
                .url(urlStr)
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 请求失败
                if (callBack != null) {
                    callBack.onRequestFail("ioexception");
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (callBack != null) {
                        String result = response.body().string();
                        callBack.onRequestComplete(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // okhttp信任https证书
    private static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[0];
        }
    }

    private static SSLSocketFactory createSSLSocketFactory(X509TrustManager trustManager) {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{trustManager}, null);
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }
        return ssfFactory;
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}
