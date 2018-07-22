package com.pfl.common.utils; /**
 * 主要功能有清除内/外缓存，清除数据库，清除sharedPreference，清除files和清除自定义目录
 */

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.math.BigDecimal;


/**
 * 本应用数据清除管理器
 */
public class DataCleanManager {
    /**
     * * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * * 16      * @param context
     */
    public static void cleanInternalCache(Context context) {
        deleteFolderFile(context.getCacheDir().getAbsolutePath(), true);
    }

    /**
     * * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * * 25      * @param context
     */
    public static void cleanDatabases(Context context) {
        deleteFolderFile("/data/data/"
                + context.getPackageName() + "/databases", true);
    }

    /**
     * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs) *
     *
     * @param context
     */
    public static void cleanSharedPreference(Context context) {
        deleteFolderFile("/data/data/" + context.getPackageName() + "/shared_prefs", true);
    }

    /**
     * * 按名字清除本应用数据库 * * 45      * @param context
     *
     * @param dbName
     */
    public static void cleanDatabaseByName(Context context, String dbName) {
        context.deleteDatabase(dbName);
    }

    /**
     * * 清除/data/data/com.xxx.xxx/files下的内容 * * 55      * @param context
     */
    public static void cleanFiles(Context context) {
        deleteFolderFile(context.getFilesDir().getAbsolutePath(), true);
    }

    /**
     * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache) 64      * @param context
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteFolderFile(context.getExternalCacheDir().getAbsolutePath(), true);
        }
    }

    /**
     * * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 * * 75      * @param filePath
     */
    public static void cleanCustomCache(String filePath) {
        deleteFolderFile(filePath, true);
    }

    /**
     * * 清除本应用所有的数据 * * 84      * @param context
     *
     * @param filepath
     */
    public static void cleanApplicationData(Context context, String... filepath) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabases(context);
        cleanSharedPreference(context);
        cleanFiles(context);
        if (filepath == null) {
            return;
        }
        for (String filePath : filepath) {
            cleanCustomCache(filePath);
        }
    }

    // 获取文件
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param filePath
     * @param deleteThisPath
     * @return
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 如果下面还有文件
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "B";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }


    public static String getCacheSize(File file) throws Exception {
        return getFormatSize(getFolderSize(file));
    }

}