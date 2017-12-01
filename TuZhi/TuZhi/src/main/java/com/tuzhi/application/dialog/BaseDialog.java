package com.tuzhi.application.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.inter.OnDialogClickListener;

/**
 *
 * @author wangpeng
 * @date 2017/11/10
 */

public abstract class BaseDialog extends AlertDialog {

    private OnDialogClickListener clickListener;

    public void setClickListener(OnDialogClickListener clickListener) {
        this.clickListener = clickListener;
    }

    BaseDialog(Context context) {
        super(context, R.style.dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(getLayout(), null);
        setContentView(view);
        init(DataBindingUtil.bind(view));
    }

    protected abstract void init(ViewDataBinding bind);

    protected abstract int getLayout();

    public void onViewClick(View view) {
        if (clickListener != null) {
            clickListener.onDialogClick(view);
            dismiss();
        }
    }

    public void onViewClick(View view, Object object) {
        if (clickListener != null) {
            view.setTag(object);
            clickListener.onDialogClick(view);
            dismiss();
        }
    }
}
