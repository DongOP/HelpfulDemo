package com.dong.externalstorage.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Array;
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

    // 获取 设备的SD卡、U盘路径
    public static String getStoragePath(Context context, boolean isUsb) {
        String path = "";

        if (Build.VERSION.SDK_INT < 23) {
            String pathKey = "";
            if (isUsb) {
                pathKey = "usb";
                // 小于 Android 6.0 版本的U盘路径
                path = getStoragePathOld(context, pathKey);
            } else {
                pathKey = "sd";
                // 小于 Android 6.0 版本的sd路径
                path = getSDFilePath(context);
            }
            Log.e(TAG, "6.0以下...getStoragePath.....path=" + path);

            return path;
        } else {
            StorageManager mStorageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
            Class<?> volumeInfoClazz = null;
            Class<?> diskInfoClaszz = null;

            try {
                volumeInfoClazz = Class.forName("android.os.storage.VolumeInfo");
                diskInfoClaszz = Class.forName("android.os.storage.DiskInfo");

                Method StorageManager_getVolumes = Class.forName("android.os.storage.StorageManager").getMethod("getVolumes");
                Method VolumeInfo_GetDisk = volumeInfoClazz.getMethod("getDisk");
                Method VolumeInfo_GetPath = volumeInfoClazz.getMethod("getPath");
                Method DiskInfo_IsUsb = diskInfoClaszz.getMethod("isUsb");
                Method DiskInfo_IsSd = diskInfoClaszz.getMethod("isSd");

                List<Object> List_VolumeInfo = (List<Object>) StorageManager_getVolumes.invoke(mStorageManager);
                for (int i = 0; i < List_VolumeInfo.size(); i++) {
                    Object volumeInfo = List_VolumeInfo.get(i);
                    Object diskInfo = VolumeInfo_GetDisk.invoke(volumeInfo);


                    if (diskInfo == null) continue;

                    boolean sd = (boolean) DiskInfo_IsSd.invoke(diskInfo);
                    boolean usb = (boolean) DiskInfo_IsUsb.invoke(diskInfo);

                    File file = (File) VolumeInfo_GetPath.invoke(volumeInfo);
                    // Logger.d("diskinfo="+file.getAbsolutePath()+"; is_usb="+usb+";  is_sd="+sd);

                    if (isUsb == usb) {//usb
                        if (null != file) {
                            path = file.getAbsolutePath();
                        }
                        break;
                    } else if (!isUsb == sd) {//sd
                        if (null != file) {
                            path = file.getAbsolutePath();
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
            Log.e(TAG, "6.0...getStoragePath.....path=" + path);

            return path;
        }
    }

    /**
     * 获取6.0以下机器的外置U盘路径
     *
     * @param keyPath extsd 外置SD卡，usb 外置U盘
     * @return 返回外置SD卡或者U盘的路径
     */
    public static String getStoragePathOld(Context context, String keyPath) {
        Method mMethodGetPaths = null;
        String[] paths = null;
        String state = null;
        List<String> lists = new ArrayList<String>();
        StorageManager mStorageManager = (StorageManager) context.getSystemService(context.STORAGE_SERVICE);
        try {
            mMethodGetPaths = mStorageManager.getClass().getMethod(
                    "getVolumePaths");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            if (mMethodGetPaths != null) {
                paths = (String[]) mMethodGetPaths.invoke(mStorageManager);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        mMethodGetPaths = null;
        for(int i = 0; i < paths.length; i++) {
            if(paths[i].contains("extsd") || paths[i].contains("usb")){
                try {
                    mMethodGetPaths = mStorageManager.getClass().getMethod(
                            "getVolumeState", String.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                try {
                    if (mMethodGetPaths != null) {
                        state = (String) mMethodGetPaths.invoke(mStorageManager, paths[i]);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (Environment.MEDIA_MOUNTED.equals(state)) {
                    lists.add(paths[i]);
                }
            }
        }

//        Utils.logd("dong", "获取4.4机器的外置路径 lists=" + lists.toString());
        for (String path : lists) {
            if (path.contains("usb")) {
//                Utils.loge("dong", "4.4机器的外置U盘路径 path=" + path);
                return path;
            }
        }

        return "";
    }

    /**
     * 获取 Android 4.4 外置SD卡路径
     */
    public static String getSDFilePath(Context context) {
        StorageManager mStorageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (removable) {
                    return path;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
