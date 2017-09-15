package com.tuzhi.application.moudle.mine.mvp;


import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.MainActivity;
import com.tuzhi.application.R;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.bean.HttpInitBean;
import com.tuzhi.application.databinding.ActivityMineBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseFragment;
import com.tuzhi.application.moudle.login.mvp.LoginActivity;
import com.tuzhi.application.moudle.mine.personalinformation.mvp.PersonalInformationActivity;
import com.tuzhi.application.moudle.mine.problemfeedback.mvp.ProblemFeedbackActivity;
import com.tuzhi.application.moudle.mine.setting.mvp.SettingActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.utils.CommonUtils;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.ImageUtils;
import com.tuzhi.application.utils.SharedPreferencesUtilsKt;
import com.tuzhi.application.utils.UserInfoUtils;
import com.tuzhi.application.view.SwitchView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * MVPPlugin
 */

public class MineFragment extends MVPBaseFragment<MineContract.View, MinePresenter> implements MineContract.View, SwitchView.OnStateChangedListener {

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
        HttpInitBean userInfo = UserInfoUtils.getUserInfo(getContext());
        binding.setData(userInfo);
        ImageUtils.loadImage(binding.riv, userInfo.getUserImage(), CommonUtils.getDrawable(getContext(), R.drawable.defaulthead));
        binding.sv.setOnStateChangedListener(this);
        String allow = SharedPreferencesUtilsKt.getLongCache(getContext(), ConstantKt.getKey_AllowClipper());
        if (TextUtils.equals(allow, ConstantKt.getValue_true())) {
            binding.sv.setOpened(true);
        } else {
            binding.sv.setOpened(false);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEvent(String event) {
        if (TextUtils.equals(event, ConstantKt.getUPDATE_USER_INFO_EVENT())) {
            HttpInitBean userInfo = UserInfoUtils.getUserInfo(getContext());
            binding.setData(userInfo);
            ImageUtils.loadImage(binding.riv, userInfo.getUserImage(), CommonUtils.getDrawable(getContext(), R.drawable.defaulthead));
        }
    }

    @Override
    public void onDestroy() {
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
        mPresenter.logOut(getContext());
    }

    @Override
    public void logOutSuccess() {
        ActivitySkipUtilsKt.toActivity(getContext(), LoginActivity.class);
        getActivity().finish();
    }

    @Override
    public void toggleToOn(View view) {
        if (view == null) {
            if (binding != null)
                binding.sv.setOpened(true);
        } else {
            EventBusBean eventBusBean = new EventBusBean();
            eventBusBean.setName(MainActivity.NAME);
            eventBusBean.setEventType(MainActivity.TYPE_PERMISSION);
            EventBus.getDefault().post(eventBusBean);
        }

    }

    @Override
    public void toggleToOff(View view) {
        if (view == null) {
            if (binding != null)
                binding.sv.setOpened(false);
        } else {
            binding.sv.setOpened(false);
            SharedPreferencesUtilsKt.saveLongCache(getContext(), ConstantKt.getKey_AllowClipper(), ConstantKt.getValue_false());
        }

    }
}
