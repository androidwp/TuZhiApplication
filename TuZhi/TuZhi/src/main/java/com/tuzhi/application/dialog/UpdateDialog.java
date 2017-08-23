package com.tuzhi.application.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ViewUpdateDialogBinding;
import com.tuzhi.application.service.UpdateService;

/**
 * Created by wangpeng on 2017/6/6.
 */

public class UpdateDialog extends AlertDialog {

    private ViewUpdateDialogBinding binding;

    private String url;

    private String text;

    private boolean isForcedUpdate = false;

    public UpdateDialog(Context context) {
        super(context, R.style.dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.view_update_dialog, null);
        setContentView(view);
        binding = DataBindingUtil.bind(view);
        binding.setDialog(this);
        binding.tv.setText(text);
        if (isForcedUpdate) {
            forcedUpdate();
        }
    }

    public void setForcedUpdate(boolean forcedUpdate) {
        isForcedUpdate = forcedUpdate;
    }

    private void forcedUpdate() {
        binding.btnCancel.setVisibility(View.GONE);
        setCanceledOnTouchOutside(false);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void mDismiss() {
        dismiss();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void downLoadApk() {
        mDismiss();
        if (!TextUtils.isEmpty(url)) {
            Intent intent = new Intent(getContext(), UpdateService.class);
            intent.putExtra("url", url);
            getContext().startService(intent);
        }
    }

}
