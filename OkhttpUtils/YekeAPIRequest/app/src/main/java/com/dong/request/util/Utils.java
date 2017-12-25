package com.dong.request.util;

import android.util.Log;

/**
 * Created by Dong on 2017/12/19 0019.
 */

public class Utils {

    public static final boolean isDebug = true;

    public static final String WECHAT_SCOPEID = "QJaWfWI9fyI";// QJaWfWI9fyIq
    // 微信设备码相关 start
    public static final String YEKE_WXALBUM_USER = "yeker";
    public static final String YEKE_WXALBUM_PSD = "Yeker8@h2017";

    public static final String WXALBUM_BASE_URL = "https://dev.izhiju.cn/api/v1/";
    // 获取授权
    public static final String LOGIN_YEKE_WXALBUM_URL = WXALBUM_BASE_URL + "authentication/user";
    // 微信二维码
    public static final String YEKE_WXQRCODE_URL = WXALBUM_BASE_URL + "wxqrcodes/getpic";
    // 微信设备码接口
    public final static String QRCODE_DEVICE_ID_URL = WXALBUM_BASE_URL + "deviceids/auto";

    public static void logd(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void loge(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }
}
