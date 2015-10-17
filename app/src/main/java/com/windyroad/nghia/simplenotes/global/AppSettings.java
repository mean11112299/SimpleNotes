package com.windyroad.nghia.simplenotes.global;

import android.content.Context;
import android.content.SharedPreferences;

import com.windyroad.nghia.simplenotes.helper.Constants;

/**
 * Created by Nghia-PC on 10/3/2015.
 * Cài đặt ứng dụng
 */
public class AppSettings {
    private static final String PREF_NAME_SETTINGS = "app_settings";
    private static final String PREF_KEY_FORMAT_DATE_TIME = "datetime_format";

    /**
     * Lấy định dạng ngày tháng
     * @return
     */
    public static final String getDateTimeFormat(Context context){
        SharedPreferences spf = context.getSharedPreferences(PREF_NAME_SETTINGS, Context.MODE_PRIVATE);
        return spf.getString(PREF_KEY_FORMAT_DATE_TIME, Constants.DEFAUT_DATE_FORMAT);
    }
}
