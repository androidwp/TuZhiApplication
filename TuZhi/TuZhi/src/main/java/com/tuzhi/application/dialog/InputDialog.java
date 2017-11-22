package com.tuzhi.application.dialog;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.EditText;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.DialogChangeTaskTitleSummeryBinding;
import com.tuzhi.application.utils.KeyBoardUtils;

/**
 * Created by wangpeng on 2017/11/14.
 */

public class InputDialog extends BaseDialog {

    private DialogChangeTaskTitleSummeryBinding binding;

    private String title;

    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public InputDialog(Context context) {
        super(context);
    }

    @Override
    protected void init(ViewDataBinding bind) {
        binding = (DialogChangeTaskTitleSummeryBinding) bind;
        binding.setDialog(this);
        binding.setTitle(title);
        binding.setText(text);
        binding.et.setText(text);
        binding.et.setSelection(binding.getText().length());
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_change_task_title_summery;
    }

    @Override
    public void show() {
        setView(new EditText(getContext()));
        KeyBoardUtils.showKeyBoard(getContext());
        super.show();
    }

    @Override
    public void onViewClick(View view, Object object) {
        super.onViewClick(view, object);
    }
}
