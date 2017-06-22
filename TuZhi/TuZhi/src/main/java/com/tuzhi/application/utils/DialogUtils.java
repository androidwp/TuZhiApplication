package com.tuzhi.application.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by KF on 2015/12/10.
 */
public class DialogUtils {

    private static ProgressDialog dialog;

    public static void showDialog(Context context) {
        showDialog(context, "请稍后...");
    }

    public static void showDialog(Context context, String info) {
        if (context != null) {
            if (isShowing()) {
                return;
            }
            dialog = new ProgressDialog(context);
            dialog.setMessage(info);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }

    public static void cancelDialog() {
        if (isShowing())
            dialog.cancel();
    }

    private static boolean isShowing() {
        return dialog != null && dialog.isShowing();
    }

}
