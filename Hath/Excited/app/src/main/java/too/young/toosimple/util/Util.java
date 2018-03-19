package too.young.toosimple.util;

import android.util.Log;

/**
 * Created by Dong on 2018/3/5 0005.
 */

public class Util {
    public static final int WAKEUP = 1;
    public static final int SLEEP = 2;

    public final static String PREF_NAME 					= "TPFamilyAlbum";

    // 保存Alarm信息
    public final static String WAKEUP_ID = "wakeup_id";
    public final static String WAKEUP_HOUR = "wakeup_hour";
    public final static String WAKEUP_MINUTES = "wakeup_minutes";
    public final static String WAKEUP_ENABLED = "wakeup_enabled";
    public final static String WAKEUP_DAYSOFWEEK = "wakeup_daysOfWeek";

    public final static String SLEEP_ID = "sleep_id";
    public final static String SLEEP_HOUR = "sleep_hour";
    public final static String SLEEP_MINUTES = "sleep_minutes";
    public final static String SLEEP_ENABLED = "sleep_enabled";
    public final static String SLEEP_DAYSOFWEEK = "sleep_daysOfWeek";

    private static final Boolean DEBUG = true;
    public static void log(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void loge(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }
}
