package dong.yeke.wxqrcode.util;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Dong on 2017/11/7 0007.
 */

public class PictureUtil {

    /**
     * 将字节数组转换为Bitmap对象
     *
     * @param bytes 图片byte[]数据
     */
    public static Bitmap getPicFromBytes(byte[] bytes) {
        if (bytes != null) {
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        return null;
    }

}
