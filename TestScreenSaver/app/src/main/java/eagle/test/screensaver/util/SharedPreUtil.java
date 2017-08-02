package eagle.test.screensaver.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreUtil {

	public static final String SETTING_SHAREPRE = "SHARE_NAME_DEVICE";

	public static void saveDeviceNum(Context context, String msg) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SETTING_SHAREPRE, Activity.MODE_PRIVATE);

        sharedPreferences.edit().putString("deviceNum", msg).commit();
    }

	public static String getAliDeviceNum(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(SETTING_SHAREPRE, Activity.MODE_PRIVATE);
        return sharedPreferences.getString("deviceNum", "");
    }
}
