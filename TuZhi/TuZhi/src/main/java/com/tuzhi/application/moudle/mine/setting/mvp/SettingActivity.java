package com.tuzhi.application.moudle.mine.setting.mvp;


import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivitySettingBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.mine.setting.changepassword.mvp.ChangePasswordActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.SharedPreferencesUtilsKt;
import com.tuzhi.application.view.SwitchView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SettingActivity extends MVPBaseActivity<SettingContract.View, SettingPresenter> implements SettingContract.View, SwitchView.OnStateChangedListener {

    private ActivitySettingBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        binding = (ActivitySettingBinding) viewDataBinding;
        binding.setActivity(this);
        binding.sv.setOnStateChangedListener(this);
        String allow = SharedPreferencesUtilsKt.getLongCache(this, ConstantKt.getKey_AllowMobileInternetDownload());
        if (TextUtils.equals(allow,ConstantKt.getValue_true())){
            binding.sv.setOpened(true);
        }else{
            binding.sv.setOpened(false);
        }

    }

    public void back() {
        onBackPressed();
    }


    public void cleanCache(){

    }

    public void skipChangePassword(){
        ActivitySkipUtilsKt.toActivity(this, ChangePasswordActivity.class);
    }

    @Override
    public void toggleToOn(View view) {
        binding.sv.setOpened(true);
        SharedPreferencesUtilsKt.saveLongCache(this, ConstantKt.getKey_AllowMobileInternetDownload(), ConstantKt.getValue_true());
    }

    @Override
    public void toggleToOff(View view) {
        binding.sv.setOpened(false);
        SharedPreferencesUtilsKt.saveLongCache(this, ConstantKt.getKey_AllowMobileInternetDownload(), ConstantKt.getValue_false());
    }
}
