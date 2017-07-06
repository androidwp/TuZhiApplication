package com.tuzhi.application.moudle.mine.problemfeedback.mvp;


import android.app.Dialog;
import android.databinding.ViewDataBinding;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityProblemFeedbackBinding;
import com.tuzhi.application.dialog.WarnDialog;
import com.tuzhi.application.inter.DialogMakeSureListener;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ProblemFeedbackActivity extends MVPBaseActivity<ProblemFeedbackContract.View, ProblemFeedbackPresenter> implements ProblemFeedbackContract.View {

    private WarnDialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_problem_feedback;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        ActivityProblemFeedbackBinding binding = (ActivityProblemFeedbackBinding) viewDataBinding;
        binding.setActivity(this);
        binding.setText("");
        dialog = new WarnDialog.Builder().setTitle("提示").setInfo("是否放弃保存,直接退出").setBtnLeftText("取消").setBtnRightText("确定").setClickListener(new DialogMakeSureListener() {
            @Override
            public void makeSure(Dialog dialog) {
                ProblemFeedbackActivity.super.onBackPressed();
            }
        }).builder(this);
    }

    @Override
    public void back() {
      onBackPressed();
    }

    @Override
    public void onBackPressed() {
        dialog.show();
    }

    public void commitFeedback(String text){
        mPresenter.commitFeedback(text);
    }
}
