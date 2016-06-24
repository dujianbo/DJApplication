package com.personal.djb.catmovie.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 作用：缓存工具类
 */
public class CacheUtils {
    /**
     * 保存软件的参数
     *
     * @param context
     * @param key
     * @param values
     */
    public static void putBoolen(Context context, String key, boolean values) {
        SharedPreferences sp = context.getSharedPreferences("dujianbo", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, values).commit();
    }

    /**
     * 得到软件保存的参数
     *
     * @param context
     * @param key
     * @return
     */
    public static Boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("dujianbo", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    /**
     * 保持String类型的数据
     *
     * @param context
     * @param key
     * @param values
     */
    public static void putString(Context context, String key, String values) {

        //如果存在sdcard就缓存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //http://lbsyun.baidu.com/static/img/imgeditor/logo.gif--MD5--lsklkslkllklkskllkslkkls
            //mnt/sdcard/beijingnews/lsklkslkllklkskllkslkkls
            try {
                String fileName = MD5Encoder.encode(key);
                ////mnt/sdcard/
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/catmovie";

                // //mnt/sdcard/beijingnews/lsklkslkllklkskllkslkkls
                File file = new File(path, fileName);

                File parentfile = file.getParentFile();
                if (!parentfile.exists()) {
                    parentfile.mkdirs();
                }

                if (!file.exists()) {
                    file.createNewFile();
                }

                FileOutputStream fos = new FileOutputStream(file);

                fos.write(values.getBytes());

                fos.close();

            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            SharedPreferences sp = context.getSharedPreferences("dujianbo", Context.MODE_PRIVATE);
            sp.edit().putString(key, values).commit();
        }


    }

    /**
     * 得到缓存的数据
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            String result = "";
            //http://lbsyun.baidu.com/static/img/imgeditor/logo.gif--MD5--lsklkslkllklkskllkslkkls
            //mnt/sdcard/beijingnews/lsklkslkllklkskllkslkkls
            try {
                String fileName = MD5Encoder.encode(key);
                ////mnt/sdcard/
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/catmovie";

                // //mnt/sdcard/beijingnews/lsklkslkllklkskllkslkkls
                File file = new File(path, fileName);

                if (file.exists()) {


                    //读取文件
                    FileInputStream fis = new FileInputStream(file);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int length = -1;
                    while ((length = fis.read(buffer)) != -1) {
                        bos.write(buffer, 0, length);
                    }


                    result = bos.toString();
                    return result;

                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            SharedPreferences sp = context.getSharedPreferences("dujianbo", Context.MODE_PRIVATE);
            return sp.getString(key, "");
        }

        return "";

    }
}
