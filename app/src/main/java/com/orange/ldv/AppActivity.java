package com.orange.ldv;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AppActivity extends AppInitTools {

    public WebView webView;
    public static AppActivity appActivity;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initContentBefore();
//        setContentView(R.layout.activity_app);
        //获得控件
        initRefresh();
        initWebView();
        AppActivity.appActivity = this;
        Config.mainContext = AppActivity.this;
    }


    public void initWebView() {
        webView = findViewById(R.id.mainWebView);
        //访问网页
        webView.loadUrl(Config.getWebIndexUrl());
        System.out.println("loading url............" + Config.getWebIndexUrl());

        //系统默认会通过手机浏览器打开网页，为了能够直接通过WebView显示网页，则必须设置
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //使用WebView加载显示url
                view.loadUrl(url);
                System.out.println("loading url............" + url);
                //返回true
                return true;
            }
        });
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true); //-> 是否开启JS支持
//        webSettings.setPluginsEnabled(true); //-> 是否开启插件支持
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //-> 是否允许JS打开新窗口
//
        webSettings.setUseWideViewPort(true); //-> 缩放至屏幕大小
        webSettings.setLoadWithOverviewMode(true); //-> 缩放至屏幕大小
        webSettings.setSupportZoom(false); //-> 是否支持缩放
        webSettings.setBuiltInZoomControls(false); //-> 是否支持缩放变焦，前提是支持缩放
        webSettings.setDisplayZoomControls(false); //-> 是否隐藏缩放控件
//
        webSettings.setAllowFileAccess(true); //-> 是否允许访问文件
        webSettings.setAllowContentAccess(true); //-> 是否允许访问文件
        webSettings.setDomStorageEnabled(false); //-> 是否节点缓存
        webSettings.setDatabaseEnabled(false); //-> 是否数据缓存
        webSettings.setAppCacheEnabled(false); //-> 是否应用缓存
//        webSettings.setAppCachePath(uri); //-> 设置缓存路径

//        webSettings.setMediaPlaybackRequiresUserGesture(false); //-> 是否要手势触发媒体
//        webSettings.setStandardFontFamily("sans-serif"); //-> 设置字体库格式
//        webSettings.setFixedFontFamily("monospace"); //-> 设置字体库格式
//        webSettings.setSansSerifFontFamily("sans-serif"); //-> 设置字体库格式
//        webSettings.setSerifFontFamily("sans-serif"); //-> 设置字体库格式
//        webSettings.setCursiveFontFamily("cursive"); //-> 设置字体库格式
//        webSettings.setFantasyFontFamily("fantasy"); //-> 设置字体库格式
        webSettings.setTextZoom(100); //-> 设置文本缩放的百分比
        webSettings.setMinimumFontSize(8); //-> 设置文本字体的最小值(1~72)
        webSettings.setDefaultFontSize(16); //-> 设置文本字体默认的大小
//
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //-> 按规则重新布局
//        webSettings.setLoadsImagesAutomatically(false); //-> 是否自动加载图片
//        webSettings.setDefaultTextEncodingName("UTF-8"); //-> 设置编码格式
//        webSettings.setNeedInitialFocus(true); //-> 是否需要获取焦点
//        webSettings.setGeolocationEnabled(false); //-> 设置开启定位功能
//        webSettings.setBlockNetworkLoads(true); //-> 是否从网络获取资源

        //设置本地调用对象及其接口
        webView.addJavascriptInterface(new JavaScriptObject(this, webView), "androidJS");
    }

    public void initRefresh() {
        Button button = findViewById(R.id.refresh);
        button.setVisibility( View.INVISIBLE);
//        button.setVisibility(!Config.APK ? View.VISIBLE : View.INVISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("点击按钮事件！");
                webView.reload();
            }
        });
    }

}