package com.tuzhi.application.moudle.mine.mvp;


import android.databinding.ViewDataBinding;
import android.text.TextUtils;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityMineBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.login.mvp.LoginActivity;
import com.tuzhi.application.moudle.mine.personalinformation.mvp.PersonalInformationActivity;
import com.tuzhi.application.moudle.mine.problemfeedback.mvp.ProblemFeedbackActivity;
import com.tuzhi.application.moudle.mine.setting.mvp.SettingActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.UserInfoUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * MVPPlugin
 */

public class MineActivity extends MVPBaseActivity<MineContract.View, MinePresenter> implements MineContract.View {

    private ActivityMineBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        binding = (ActivityMineBinding) viewDataBinding;
        binding.setActivity(this);
        binding.setData(UserInfoUtils.getUserInfo(this));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEvent(String event) {
        if (TextUtils.equals(event, ConstantKt.getUPDATE_USER_INFO_EVENT())) {
            binding.setData(UserInfoUtils.getUserInfo(this));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void skipPersonalInformationActivity() {
        ActivitySkipUtilsKt.toActivity(getContext(), PersonalInformationActivity.class);
    }


    public void skipProblemFeedbackActivity() {
        ActivitySkipUtilsKt.toActivity(getContext(), ProblemFeedbackActivity.class);
    }

    public void skipSettingActivity() {
        ActivitySkipUtilsKt.toActivity(getContext(), SettingActivity.class);
    }

    public void logOut() {
        ActivitySkipUtilsKt.toActivity(getContext(), LoginActivity.class);
        setResult(ConstantKt.getKILL_ACTIVITY_CODE());
        back();
    }


    public void back() {
        onBackPressed();
    }

}
