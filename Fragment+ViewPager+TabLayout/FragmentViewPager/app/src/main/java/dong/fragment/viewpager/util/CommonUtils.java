package dong.fragment.viewpager.util;

import android.util.Log;

/**
 * Created by Dong on 2017/11/16 0016.
 */

public class CommonUtils {

    public static final Boolean isDebug = true;

    public static void logd (String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void loge (String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }
}
