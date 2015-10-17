package com.windyroad.nghia.simplenotes.global;

import android.app.ProgressDialog;
import android.content.Context;

import com.windyroad.nghia.common.DialogUtil;
import com.windyroad.nghia.simplenotes.R;

/**
 * Created by Nghia on 12-10-15.
 */
public class AppAlertDialog {
    /** Hiện waiting dialog */
    public static void showWaitingDialog (Context context, ProgressDialog progressDlg){
        //progressDlg = new ProgressDialog(context);
        DialogUtil.showProgressDialog(progressDlg, true, null, context.getString(R.string.message_dialog_waiting));
    }

    /** Hiện Mở mạng dialog */
    public static void showNetworkSettingAlert(Context context){
        DialogUtil.showNetworkSettingAlert(
                context,
                context.getString(R.string.title_dialog_question, ""),
                context.getString(R.string.message_dialog_open_network, ""),
                context.getString(R.string.action_settings, ""),
                context.getString(R.string.action_cancel, ""));
    }

    public static void showLocationSettingAlert(Context context) {
        DialogUtil.showLocationSettingAlert(
                context,
                context.getString(R.string.title_dialog_question),
                context.getString(R.string.message_dialog_open_gps),
                context.getString(R.string.action_settings),
                context.getString(R.string.action_cancel)
        );
    }
}
