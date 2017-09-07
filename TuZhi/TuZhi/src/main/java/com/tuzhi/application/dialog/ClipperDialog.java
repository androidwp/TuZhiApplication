package com.tuzhi.application.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.DialogClipperBinding;
import com.tuzhi.application.moudle.clipper.mvp.ClipperOneActivity;

/**
 * Created by wangpeng on 2017/9/6.
 */

public class ClipperDialog extends AlertDialog {
    private String info;
    private DialogClipperBinding binding;

    public void setInfo(String info) {
        this.info = info;
        if (binding != null) {
            binding.setInfo(info);
        }
    }

    public ClipperDialog(Context context) {
        super(context, R.style.dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.dialog_clipper, null);
        setContentView(view);
        binding = DataBindingUtil.bind(view);
        binding.setDialog(this);
        binding.setInfo(info);
    }

    public void cancel() {
        dismiss();
    }

    public void sure() {
        Intent intent = new Intent(getContext(), ClipperOneActivity.class);
        intent.putExtra(ClipperOneActivity.ARTICLE_URL, info);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        getContext().startActivity(intent);
        dismiss();
    }

}
