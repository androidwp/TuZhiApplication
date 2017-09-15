package com.tuzhi.application.service;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.WindowManager;

import com.tuzhi.application.dialog.ClipperDialog;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.SharedPreferencesUtilsKt;

/**
 * Created by wangpeng on 2017/9/6.
 */

public class ClipboardService extends Service implements ClipboardManager.OnPrimaryClipChangedListener {

    private ClipboardManager clipboardManager;
    private ClipperDialog dialog;

    @Override
    public void onCreate() {
        super.onCreate();
        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.addPrimaryClipChangedListener(this);
        dialog = new ClipperDialog(getApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onPrimaryClipChanged() {
        ClipData.Item itemAt = clipboardManager.getPrimaryClip().getItemAt(0);
        CharSequence text = itemAt.getText();
        if (text != null) {
            String allow = SharedPreferencesUtilsKt.getLongCache(this, ConstantKt.getKey_AllowClipper());
            if (TextUtils.equals(allow, ConstantKt.getValue_true())) {
                if (isClipperUrl(text.toString())) {
                    if (dialog == null) {
                        dialog = new ClipperDialog(getApplicationContext());
                    }
                    if (!dialog.isShowing()) {
                        dialog.setInfo(text.toString());
                        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                        dialog.show();

                    }
                }
            }
        }
    }

    private boolean isClipperUrl(String url) {
        if (url.contains("http")) {
            if (url.contains("weixin")) {
                return true;
            }
        }
        return false;
    }
}
