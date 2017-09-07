package com.tuzhi.application.service;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.tuzhi.application.dialog.ClipperDialog;

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
        if (dialog == null) {
            dialog = new ClipperDialog(getApplicationContext());
        }
        if (!dialog.isShowing()) {
            if (text != null) {
                dialog.setInfo(text.toString());
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.show();
            }
        }
    }
}
