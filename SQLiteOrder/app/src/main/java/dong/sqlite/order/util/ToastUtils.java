package dong.sqlite.order.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Dong on 2017/11/20 0020.
 */

public class ToastUtils {
    private static Toast mToast;

    public static void showToast(Context context, String resid) {
        if (mToast == null) {
            mToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        }
        mToast.setText(resid);
        mToast.show();
    }

    public static void showToast(Context context, int resid) {
        if (mToast == null) {
            mToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        }
        String msg = context.getResources().getString(resid);
        mToast.setText(msg);
        mToast.show();
    }
}
