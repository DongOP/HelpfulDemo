package dong.common.guidepage.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Dong on 2017/11/16 0016.
 */

public class CommonUtils {

    public static final boolean isDeBug = true;
    private static Toast mToast;

    public static void logd(String tag, String msg) {
        if (isDeBug) {
            Log.d(tag, msg);
        }
    }

    public static void showToast(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        }
        mToast.setText(msg);
        mToast.show();
    }

    public static void showToast(Context context, int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        }
        mToast.setText(context.getResources().getString(resId));
        mToast.show();
    }
}
