package com.tuzhi.application.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ViewSelectDialogBinding;

/**
 * Created by wangpeng on 2017/6/2.
 */

public class SelectDialog extends AlertDialog {

    public SelectDialog(Context context) {
        super(context);
    }

    public SelectDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public SelectDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.view_select_dialog, null);
        setContentView(view);
        ViewSelectDialogBinding binding = DataBindingUtil.bind(view);
        binding.setDialog(this);
    }

    public void delete() {

    }

    public void rename() {

    }
}
