package com.tuzhi.application.moudle.mine.mvp;


import android.databinding.ViewDataBinding;

import com.alibaba.fastjson.JSONObject;
import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityMineBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.login.bean.HttpUserBean;
import com.tuzhi.application.moudle.login.mvp.LoginActivity;
import com.tuzhi.application.moudle.mine.personalinformation.mvp.PersonalInformationActivity;
import com.tuzhi.application.moudle.mine.problemfeedback.mvp.ProblemFeedbackActivity;
import com.tuzhi.application.moudle.mine.setting.mvp.SettingActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.SharedPreferencesUtilsKt;


/**
 * MVPPlugin
 */

public class MineActivity extends MVPBaseActivity<MineContract.View, MinePresenter> implements MineContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        ActivityMineBinding binding = (ActivityMineBinding) viewDataBinding;
        binding.setActivity(this);
        String userInfo = SharedPreferencesUtilsKt.getLongCache(this, ConstantKt.getLOGIN_INFO());
        HttpUserBean httpUserBean = JSONObject.parseObject(userInfo, HttpUserBean.class);
        String headUrl = SharedPreferencesUtilsKt.getLongCache(this, ConstantKt.getIMAGE_HEAD());
        httpUserBean.setUserImage(headUrl);
        binding.setData(httpUserBean);
        binding.executePendingBindings();
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
