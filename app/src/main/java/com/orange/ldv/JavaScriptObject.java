package com.orange.ldv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.orange.ldv.response.BaseResponse;
import com.orange.ldv.response.StatusCode;

import org.json.JSONObject;

public class JavaScriptObject {

    private Context mContext;
    private WebView webView;

    public static Context mainContext;

    public JavaScriptObject(Context mContext, WebView _webView) {
        this.mContext = mContext;
        this.webView = _webView;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @JavascriptInterface //sdk17版本以上加上注解
    public String executeMethod(String action, String methodType, String jsonParam) {
        try {
            String _action = methodType.toUpperCase() + ":" + action;
            Object res = CoreEventHander.executeMethodOfController(_action, jsonParam, this.mContext);
            if(res instanceof String){
                return "{'code':'0',data:'"+ res.toString()+"'}";
            }
            Gson gson = new Gson();
            return "{'code':'0',data:'"+ gson.toJson(res)+"'}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{'code':'-1',data:'出现错误！"+ e.getMessage()+"'}";
        }

    }
}
