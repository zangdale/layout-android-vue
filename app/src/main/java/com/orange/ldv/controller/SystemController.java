package com.orange.ldv.controller;

import com.orange.ldv.AppActivity;
import com.orange.ldv.Config;
import com.orange.ldv.JavaScriptObject;
import com.orange.ldv.annotation.AppController;
import com.orange.ldv.annotation.AppRequestMapper;

import java.lang.reflect.Field;

@AppController(path = "system")
public class SystemController {

    @AppRequestMapper(path = "/setTitle")
    public void setTitle(Boolean isShow) throws Exception {
        throw new Exception("暂不支持，请使用vue相关的标题栏组件");
    }

    @AppRequestMapper(path = "/setStatusBar")
    public void setStatusBar(Boolean isShow) throws Exception {
        throw new Exception("暂不支持，请使用vue相关的状态栏组件");
    }

    @AppRequestMapper(path = "/setStatusBarHeight")
    public double setStatusBarHeight() throws Exception {
        int result = 0;
        int resourceId = AppActivity.appActivity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            System.out.println(resourceId);
            result = AppActivity.appActivity.getResources().getDimensionPixelSize(resourceId);

        }
        return Config.Dp2Px(result) ;
    }

}
