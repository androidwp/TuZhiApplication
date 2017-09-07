package com.tuzhi.application.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ViewRenameDialogBinding;
import com.tuzhi.application.inter.OnDialogClickListener;

/**
 * Created by wangpeng on 2017/6/2.
 */

public class RenameDialog extends AlertDialog {
    private String title = "重命名";
    private String confirmText = "确认修改";
    private String text;
    private ViewRenameDialogBinding binding;
    private OnDialogClickListener clickListener;

    public void setConfirmText(String confirmText) {
        this.confirmText = confirmText;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClickListener(OnDialogClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public RenameDialog(Context context) {
        this(context, R.style.dialog);
    }

    public RenameDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.view_rename_dialog, null);
        setContentView(view);
        binding = DataBindingUtil.bind(view);
        binding.setDialog(this);
        binding.setName(text);
        binding.setTitle(title);
        binding.setConfirmText(confirmText);
        binding.et.post(new Runnable() {
            @Override
            public void run() {
                if (text != null)
                    if (text.length() > 20) {
                        binding.et.setSelection(20);
                    } else {
                        binding.et.setSelection(text.length());
                    }
            }
        });
        setCanceledOnTouchOutside(false);
    }

    public void cancel() {
        dismiss();
    }

    public void rename(View view, String name) {
        if (clickListener != null) {
            view.setTag(name);
            clickListener.onDialogClick(view);
        }
    }
}
