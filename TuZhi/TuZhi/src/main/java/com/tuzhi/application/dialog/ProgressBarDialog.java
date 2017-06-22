package com.tuzhi.application.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ViewProgressBarDialogBinding;

/**
 * Created by wangpeng on 2017/6/2.
 */

public class ProgressBarDialog extends AlertDialog {

    public ProgressBarDialog(Context context) {
        super(context);
    }

    public ProgressBarDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public ProgressBarDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.view_progress_bar_dialog, null);
        setContentView(view);
        ViewProgressBarDialogBinding binding = DataBindingUtil.bind(view);
        binding.setDialog(this);
    }

    public void cancel() {
        dismiss();
    }
}
