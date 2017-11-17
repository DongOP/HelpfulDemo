package dong.common.guidepage.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dong on 2017/11/17 0017.
 */

public class SharePFUtils {
    public static final String SHARE_PRE_NAME = "Guide";
    public static final String IS_FIRST_LAUNCHER = "isFirst";

    public static boolean getIsFirstLaunch(Context context) {
        if (context == null) {
            return true;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                SHARE_PRE_NAME, Activity.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_FIRST_LAUNCHER, true);
    }

    public static void setNotFirstLaunch(Context context) {
        if (context == null) {
            return;
        }
        SharedPreferences mySharedPreferences = context.getSharedPreferences(
                SHARE_PRE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putBoolean(IS_FIRST_LAUNCHER, false);
        editor.apply();
    }

    public static void setIsFirstLaunch(Context context) {
        if (context == null) {
            return;
        }
        SharedPreferences mySharedPreferences = context.getSharedPreferences(
                SHARE_PRE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putBoolean(IS_FIRST_LAUNCHER, true);
        editor.apply();
    }

}
