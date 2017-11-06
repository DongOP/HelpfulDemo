package dong.create.barcode.util;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.Hashtable;

import static android.R.attr.padding;
import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

/**
 * Created by Dong on 2017/11/3 0003.
 */

public class ZxingUtil {

    /**
     * 用于将给定的内容生成成条形码
     *
     * @param content 将要生成条形码的内容
     * @return 返回生成好的条形码bitmap
     * @throws WriterException WriterException异常
     */
    public static Bitmap CreateBarCode(String content) throws WriterException {
        // 生成一维条码,编码时指定大小
        BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.CODE_128, 330, 200);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = 0xff000000;
                }
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        // 通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    // 生成二维码
    public static Bitmap createQRCode(String str, int widthAndHeight)
            throws WriterException {
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix matrix = new MultiFormatWriter().encode(str,
                BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);

        int width = matrix.getWidth() - padding * 2;
        int height = matrix.getHeight() - padding * 2;
        int[] pixels = new int[width * height];

        for (int y = 0, j = padding; y < height; y++, j++) {
            for (int x = 0, i = padding; x < width; x++, i++) {
                if (matrix.get(i, j)) {
                    pixels[y * width + x] = BLACK;
                } else {
                    pixels[y * width + x] = WHITE;
                }
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
