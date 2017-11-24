package com.dong.recyclerandlistview.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Dong on 2017/11/23 0023.
 */

public class ToastUtils {

    private static Toast mToast;

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
