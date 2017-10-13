package com.tuzhi.application.moudle.mine.setting.settingpush.mvp;


import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivitySettingPushBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.SharedPreferencesUtilsKt;
import com.tuzhi.application.view.SwitchView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SettingPushActivity extends MVPBaseActivity<SettingPushContract.View, SettingPushPresenter> implements SettingPushContract.View, SwitchView.OnStateChangedListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_push;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        ActivitySettingPushBinding binding = (ActivitySettingPushBinding) viewDataBinding;
        binding.setActivity(this);
        String pushShare = SharedPreferencesUtilsKt.getLongCache(this, ConstantKt.getKey_Push_Share());
        String pushComment = SharedPreferencesUtilsKt.getLongCache(this, ConstantKt.getKey_Push_Comment());
        String pushPraise = SharedPreferencesUtilsKt.getLongCache(this, ConstantKt.getKey_Push_Praise());
        binding.svShare.setOpened(trueOrFalse(pushShare));
        binding.svComment.setOpened(trueOrFalse(pushComment));
        binding.svPraise.setOpened(trueOrFalse(pushPraise));
        binding.svShare.setOnStateChangedListener(this);
        binding.svComment.setOnStateChangedListener(this);
        binding.svPraise.setOnStateChangedListener(this);
    }

    private boolean trueOrFalse(String type) {
        return TextUtils.isEmpty(type) || TextUtils.equals(type, ConstantKt.getValue_true());
    }

    public void back() {
        onBackPressed();
    }

    @Override
    public void toggleToOn(View view) {
        storeStatue(view, true);
    }

    @Override
    public void toggleToOff(View view) {
        storeStatue(view, false);
    }

    private void storeStatue(View view, boolean state) {
        SwitchView switchView = (SwitchView) view;
        switchView.setOpened(state);
        switch (view.getId()) {
            case R.id.svShare:
                SharedPreferencesUtilsKt.saveLongCache(this, ConstantKt.getKey_Push_Share(), state ? ConstantKt.getValue_true() : ConstantKt.getValue_false());
                mPresenter.commitUpdateStatue("article", state ? ConstantKt.getValue_true() : ConstantKt.getValue_false());
                break;
            case R.id.svComment:
                SharedPreferencesUtilsKt.saveLongCache(this, ConstantKt.getKey_Push_Comment(), state ? ConstantKt.getValue_true() : ConstantKt.getValue_false());
                mPresenter.commitUpdateStatue("comment", state ? ConstantKt.getValue_true() : ConstantKt.getValue_false());
                break;
            case R.id.svPraise:
                SharedPreferencesUtilsKt.saveLongCache(this, ConstantKt.getKey_Push_Praise(), state ? ConstantKt.getValue_true() : ConstantKt.getValue_false());
                mPresenter.commitUpdateStatue("praise", state ? ConstantKt.getValue_true() : ConstantKt.getValue_false());
                break;
        }
    }
}
