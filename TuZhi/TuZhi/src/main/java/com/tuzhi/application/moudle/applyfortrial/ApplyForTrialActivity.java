package com.tuzhi.application.moudle.applyfortrial;


import android.databinding.ViewDataBinding;
import android.text.TextUtils;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityApplyForTrialBinding;
import com.tuzhi.application.moudle.applyfortrialcompletion.ApplyForTrialCompletionActivity;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.utils.ToastUtilsKt;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class ApplyForTrialActivity extends MVPBaseActivity<ApplyForTrialContract.View, ApplyForTrialPresenter> implements ApplyForTrialContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_for_trial;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        ActivityApplyForTrialBinding binding = (ActivityApplyForTrialBinding) viewDataBinding;
        binding.setActivity(this);
        binding.setData(new ApplyForTrialBean());
    }


    public void back() {
        onBackPressed();
    }

    public void commit(ApplyForTrialBean bean) {
        if (TextUtils.isEmpty(bean.getName())) {
            ToastUtilsKt.toast(this, "请填写姓名");
        } else if (TextUtils.isEmpty(bean.getPhoneOrEmail())) {
            ToastUtilsKt.toast(this, "请填写手机号或邮箱");
        } else if (TextUtils.isEmpty(bean.getCompany())) {
            ToastUtilsKt.toast(this, "请填写公司或团队名称");
        } else {
            mPresenter.commit(bean);
        }
    }

    @Override
    public void commitSuccess() {
        ActivitySkipUtilsKt.toActivity(this, ApplyForTrialCompletionActivity.class);
        onBackPressed();
    }
}
