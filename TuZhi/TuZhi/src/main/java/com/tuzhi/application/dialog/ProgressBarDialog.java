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

    private ViewProgressBarDialogBinding binding;
    private View.OnClickListener clickListener;
    private String title;

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public ProgressBarDialog(Context context) {
        this(context, R.style.dialog);
    }

    public ProgressBarDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.view_progress_bar_dialog, null);
        setContentView(view);
        binding = DataBindingUtil.bind(view);
        binding.setTitle(title);
        binding.setDialog(this);
    }

    public void changeProgress(int currentProgress, int totalProgress) {
        binding.pb.setProgress(currentProgress);
        binding.pb.setMax(totalProgress);
        float progress = ((float) currentProgress / (float) totalProgress) * 100;
        binding.tv.setText(progress + " %");
    }

    public void cancel() {
        if (clickListener != null)
            clickListener.onClick(null);
        dismiss();
    }
}
