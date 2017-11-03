package com.tuzhi.application.moudle.applyfortrialcompletion;


import android.databinding.ViewDataBinding;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityApplyForTrialCompletionBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ApplyForTrialCompletionActivity extends MVPBaseActivity<ApplyForTrialCompletionContract.View, ApplyForTrialCompletionPresenter> implements ApplyForTrialCompletionContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_for_trial_completion;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        ActivityApplyForTrialCompletionBinding binding = (ActivityApplyForTrialCompletionBinding) viewDataBinding;
        binding.setActivity(this);
    }

    public void determine() {
        onBackPressed();
    }
}
