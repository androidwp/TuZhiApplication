package com.tuzhi.application.moudle.mine.problemfeedback.mvp;


import android.databinding.ViewDataBinding;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityProblemFeedbackBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ProblemFeedbackActivity extends MVPBaseActivity<ProblemFeedbackContract.View, ProblemFeedbackPresenter> implements ProblemFeedbackContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_problem_feedback;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        ActivityProblemFeedbackBinding binding = (ActivityProblemFeedbackBinding) viewDataBinding;
        binding.setActivity(this);
        binding.setText("");
    }


    @Override
    public void back() {
        onBackPressed();
    }


    public void commitFeedback(String text){
        mPresenter.commitFeedback(text);
    }
}
