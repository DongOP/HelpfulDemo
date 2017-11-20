package dong.sqlite.order.util;

import android.os.Environment;

/**
 * Created by Dong on 2017/11/20 0020.
 */

public class SDUtils {

    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }
}
