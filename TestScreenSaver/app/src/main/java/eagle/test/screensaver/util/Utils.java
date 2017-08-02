package eagle.test.screensaver.util;

import android.util.Log;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;


/**
 * Created by Dong on 2017/7/20 0020.
 */

public class Utils {
    public static final boolean isDebug = true;
    
    private static final int BLACK      = 0xff000000;
    private static final int WHITE      = 0xffffffff;
    private static final int padding    = 35;
    public final static String USER_FOLDER                  = "FamilyAlbum/user";
    public final static String ALBUM_FOLDER                 = "FamilyAlbum/photo";
    public final static String SUFFIX                       = "jpg";
    public final static String VIDEO_SUFFIX                 = "mp4";

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

    public static void launchActivity(Context context, Class<?> activity,
            Bundle bundle) {
        Intent intent = new Intent(context, activity);
        intent.setPackage("com.allwinner.digitalphotoframe.settings");
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
