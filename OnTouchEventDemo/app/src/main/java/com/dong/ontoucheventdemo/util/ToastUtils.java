package com.dong.ontoucheventdemo.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Dong on 2017/8/23 0023.
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
}
