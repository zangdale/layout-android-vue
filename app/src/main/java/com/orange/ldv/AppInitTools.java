package com.orange.ldv;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class AppInitTools extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentBefore();
        setContentView(R.layout.activity_app);
        //获得控件
    }

    public int getStatusHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);

        }
        return result;
    }

    /**
     * 初始化
     */
    public void initContentBefore() {
        showTitle(Config.showTitle);
        showStatusBar(Config.showStatusBar);
    }


    /**
     * 初始化
     *
     * @param show
     */
    private void showStatusBar(boolean show) {
        if (!show) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    private void showTitle(boolean show) {
        if (!show) {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        }
    }


}
