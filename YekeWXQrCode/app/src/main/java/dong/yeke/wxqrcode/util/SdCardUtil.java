package dong.yeke.wxqrcode.util;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Dong on 2017/11/8 0008.
 */

public class SdCardUtil {


    public static String getQrCodeImageDir() {
        if (!SdCardUtil.isSDcardMounted()) {
            return "";
        }
        return String.format("%s/%s/", SdCardUtil.getSDCardPath(), Constants.QRCODE_IMAGE_FOLDER);
    }

    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    public static boolean isSDcardMounted() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    // 将byte[]写入到文本文件中
    public static void writeToFile(byte[] byteContent, String filePath, String fileName) {
        makesureFileDirExists(filePath);

        String strFilePath = filePath + fileName;
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fout = new FileOutputStream(file);
            fout.write(byteContent);
            fout.close();
            Log.d("dong", "获取并写入成功，图片byte[]文件保存在=" + strFilePath);
        } catch (Exception e) {
            Log.e("dong", "Error on write File:" + e);
        }
    }

    public static boolean makesureFileDirExists(String dirName) {
        File file = new File(dirName);
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }
}
