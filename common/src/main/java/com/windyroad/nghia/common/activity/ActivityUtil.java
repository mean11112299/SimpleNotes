package com.windyroad.nghia.common.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

/**
 * Created by Nghia-PC on 7/9/2015.
 */
public class ActivityUtil {

    /**
     * Finish Activity after few second
     * @param delaySecond
     * @param currentActivity
     * @param toActivity
     */
    public static void delayFinishActivity(long delaySecond, final Activity currentActivity, final Class<?> toActivity) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(currentActivity.getBaseContext(), toActivity);
                currentActivity.startActivity(mainIntent);
                currentActivity.finish();
            }
        }, delaySecond);
    }

    public static void delayFinishActivity(long delaySecond, final Activity currentActivity, final Intent intent) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentActivity.startActivity(intent);
                currentActivity.finish();
            }
        }, delaySecond);
    }

    /**
     * Đặt kết quả trả về Activity
     * @param activity
     * @param data
     */
    public static void setResultOk(Activity activity, Intent data) {
        if (activity.getParent() == null) {
            activity.setResult(Activity.RESULT_OK, data);
        } else {
            activity.getParent().setResult(Activity.RESULT_OK, data);
        }
    }
}
