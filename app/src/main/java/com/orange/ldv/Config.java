package com.orange.ldv;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Config {

    /**
     * 1.当打包app时，请先设置APK=true
     * 2.使用 npm run build 打包vue文件
     * 3.使用android-studio 打包成apk文件
     */
//    public static Boolean APK = false;
    public static Boolean APK = true;

    /**
     * 基本项目目录
     */
    public static String basePackages = "com.orange.ldv";

    /**
     * 是否显示title
     */
    public static boolean showTitle = false;

    /**
     * 是否显示状态栏
     */
    public static boolean showStatusBar = true;



    /**
     * 获取当前webView请求页面
     *
     * @return
     */
    public static String getWebIndexUrl() {
        return !APK ? "http://192.168.1.15:8080" : "file:///android_asset/ui/index.html";
    }


    public static DateFormat[] df = new SimpleDateFormat[]{
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZZZ"),
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
            new SimpleDateFormat("yyyy-MM-dd HH:mm"),
            new SimpleDateFormat("yyyy-MM-dd"),
    };

    /**
     * 日期转换
     *
     * @param val
     * @param tryCount
     * @return
     * @throws Exception
     */
    public static Date parseDate(String val, int tryCount) throws Exception {
        if (tryCount >= df.length) {
            return null;
        }
        try {
            Date date = df[tryCount].parse(val);
            return date;
        } catch (ParseException e) {
            tryCount++;
            return parseDate(val, tryCount);
        }
    }

    public static Context mainContext;

    public static int Dp2Px(float dpi) {
        final float scale = mainContext.getResources().getDisplayMetrics().density;
        float add = -0.5f;
        return (int) (scale > 0 ? (dpi / scale + add) : (dpi * scale + add));
    }

}
