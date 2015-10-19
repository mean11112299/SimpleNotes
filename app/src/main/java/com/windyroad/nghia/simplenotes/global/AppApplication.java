package com.windyroad.nghia.simplenotes.global;

import android.app.Application;

import com.windyroad.nghia.common.FileUtil;
import com.windyroad.nghia.common.FontUtil;

/**
 * Created by Nghia-PC on 10/2/2015.
 * Application của ứng dụng
 */
public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Tạo font mặc định để thêm vào AppTheme
        FontUtil.setDefaultFont(this, "MONOSPACE", "GIGI.TTF");
    }
}
