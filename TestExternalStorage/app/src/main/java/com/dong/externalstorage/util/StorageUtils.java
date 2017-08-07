package com.dong.externalstorage.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dong on 2017/8/4 0004.
 */

public class StorageUtils {

    private static final String TAG = "StorageUtils";

    // 根据文件算出容量大小
    public static List caclFileSize(File file) {
        List<String> mSizeList = new ArrayList<String>();
        mSizeList.clear();
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File aPath = file;
                StatFs sf = new StatFs(aPath.getPath());
                long size = sf.getBlockSizeLong();
                long total = sf.getBlockCountLong();
                long available = sf.getAvailableBlocksLong();
                DecimalFormat df = new DecimalFormat();
                df.setGroupingSize(3);

                String totalSize = formatFileSize(size * total);
                String availableSize = formatFileSize(size * available);
                String useSize = formatFileSize(size * (total - available));

                mSizeList.add(totalSize);  // 0、总大小 mSizeList.get(0)
                mSizeList.add(availableSize);  // 1、可用大小
                mSizeList.add(useSize);   // 2、已使用大小
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mSizeList;
    }

    // 存储格式单位转换
    public static String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    // 获取6.0以上设备的SD卡路径
    public static String getStoragePath(Context context, boolean isUsb){

        String path="";

        if(Build.VERSION.SDK_INT<23)return path;

         StorageManager mStorageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
         Class<?> volumeInfoClazz=null;
         Class<?> diskInfoClaszz=null;

         try {
             volumeInfoClazz = Class.forName("android.os.storage.VolumeInfo");
             diskInfoClaszz = Class.forName("android.os.storage.DiskInfo");

             Method StorageManager_getVolumes=Class.forName("android.os.storage.StorageManager").getMethod("getVolumes");
             Method VolumeInfo_GetDisk = volumeInfoClazz.getMethod("getDisk");
             Method VolumeInfo_GetPath = volumeInfoClazz.getMethod("getPath");
             Method DiskInfo_IsUsb = diskInfoClaszz.getMethod("isUsb");
             Method DiskInfo_IsSd = diskInfoClaszz.getMethod("isSd");

             List<Object> List_VolumeInfo = (List<Object>) StorageManager_getVolumes.invoke(mStorageManager);
             for(int i=0; i<List_VolumeInfo.size(); i++){
                 Object volumeInfo = List_VolumeInfo.get(i);
                 Object diskInfo = VolumeInfo_GetDisk.invoke(volumeInfo);


                 if(diskInfo==null)continue;

                 boolean sd= (boolean) DiskInfo_IsSd.invoke(diskInfo);
                 boolean usb= (boolean) DiskInfo_IsUsb.invoke(diskInfo);

                 File file= (File) VolumeInfo_GetPath.invoke(volumeInfo);
                 // Logger.d("diskinfo="+file.getAbsolutePath()+"; is_usb="+usb+";  is_sd="+sd);

                 if(isUsb == usb){//usb
                     if (null != file) {
                         path=file.getAbsolutePath();
                     }
                     break;
                 }else if(!isUsb == sd){//sd
                     if (null != file) {
                         path=file.getAbsolutePath();
                     }
                     break;
                 }

             }

         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (NoSuchMethodException e) {
             e.printStackTrace();
         } catch (InvocationTargetException e) {
             e.printStackTrace();
         } catch (IllegalAccessException e) {
             e.printStackTrace();
         }
        return path;
    }
}
