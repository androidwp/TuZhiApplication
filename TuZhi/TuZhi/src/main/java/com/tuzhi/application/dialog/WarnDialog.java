package com.tuzhi.application.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ViewWarnDialogBinding;
import com.tuzhi.application.inter.DialogMakeSureListener;

/**
 * Created by wangpeng on 2017/6/2.
 */

public class WarnDialog extends AlertDialog {

    private String title;
    private String info;
    private String btnLeftText;
    private String btnRightText;
    private DialogMakeSureListener listener;
    private Builder builder;

    private WarnDialog(Context context, Builder builder) {
        this(context, R.style.dialog);
        this.builder = builder;
    }

    private WarnDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.view_warn_dialog, null);
        setContentView(view);
        ViewWarnDialogBinding binding = DataBindingUtil.bind(view);
        binding.setDialog(this);
        binding.setIsShow(builder.isShowCancel);
        binding.setTitle(builder.getTitle());
        binding.setInfo(builder.getInfo());
        binding.setBtnLeftText(builder.getBtnLeftText());
        binding.setBtnRightText(builder.getBtnRightText());
    }

    public void cancel() {
        dismiss();
    }

    public void sure() {
        DialogMakeSureListener clickListener = builder.getClickListener();
        clickListener.makeSure(this);
        dismiss();
    }

    public static class Builder {
        private String title = "提示";
        private String info = "提示";
        private String btnLeftText = "取消";
        private String btnRightText = "确定";
        private DialogMakeSureListener listener;
        private boolean isShowCancel=true;

        public boolean isShowCancel() {
            return isShowCancel;
        }

        public Builder setShowCancel(boolean showCancel) {
            isShowCancel = showCancel;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public String getInfo() {
            return info;
        }

        public String getBtnLeftText() {
            return btnLeftText;
        }

        public String getBtnRightText() {
            return btnRightText;
        }

        DialogMakeSureListener getClickListener() {
            return listener;
        }


        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setInfo(String info) {
            this.info = info;
            return this;
        }

        public Builder setBtnLeftText(String btnLeftText) {
            this.btnLeftText = btnLeftText;
            return this;
        }

        public Builder setBtnRightText(String btnRightText) {
            this.btnRightText = btnRightText;
            return this;
        }

        public Builder setClickListener(DialogMakeSureListener listener) {
            this.listener = listener;
            return this;
        }

        public WarnDialog builder(Context context) {
            return new WarnDialog(context, this);
        }
    }
}
