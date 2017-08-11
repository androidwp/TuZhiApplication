package com.tuzhi.application.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ViewDeleteDialogBinding;
import com.tuzhi.application.inter.OnDialogClickListener;

/**
 * Created by wangpeng on 2017/6/2.
 */

public class DeleteDialog extends AlertDialog {

    private OnDialogClickListener clickListener;

    private Context context;

    public DeleteDialog(Context context) {
        this(context, R.style.dialog);
    }


    public DeleteDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.view_delete_dialog, null);
        setContentView(view);
        ViewDeleteDialogBinding binding = DataBindingUtil.bind(view);
        binding.setDialog(this);
    }

    public void setClickListener(OnDialogClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void cancel() {
        dismiss();
    }

    public void delete(View view) {
        if (clickListener != null) {
            clickListener.onDialogClick(view);
        }
    }

}
