package com.tuzhi.application.moudle.mine.personalinformation.bindingphoneoremailfirst.mvp;


import android.content.Intent;
import android.databinding.ViewDataBinding;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityBindingPhoneEmailFirstBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.mine.personalinformation.bindingphoneoremailfirst.bean.BindingPhoneOrEmailBean;
import com.tuzhi.application.moudle.mine.personalinformation.bindingphoneoremailsecond.mvp.BindingPhoneOrEmailSecondActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BindingPhoneOrEmailFirstActivity extends MVPBaseActivity<BindingPhoneOrEmailFirstContract.View, BindingPhoneOrEmailFirstPresenter> implements BindingPhoneOrEmailFirstContract.View {

    public static final String TYPE = "TYPE";
    public static final String PHONE = "PHONE";
    public static final String EMAIL = "EMAIL";
    private String type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_binding_phone_email_first;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        ActivityBindingPhoneEmailFirstBinding binding = (ActivityBindingPhoneEmailFirstBinding) viewDataBinding;
        type = getIntent().getStringExtra(TYPE);
        BindingPhoneOrEmailBean bean;
        switch (type) {
            case PHONE:
                bean = new BindingPhoneOrEmailBean(PHONE, "绑定手机号", "请输入手机号", "");
                break;
            default:
                bean = new BindingPhoneOrEmailBean(EMAIL, "绑定邮箱", "请输入邮箱", "");
                break;
        }
        binding.setActivity(this);
        binding.setData(bean);
    }

    public void back() {
        onBackPressed();
    }

    public void commit(String text) {
        mPresenter.commitPhoneOrEmail(text);
    }

    public void skipNextPage(String text) {
        Intent intent = new Intent(this, BindingPhoneOrEmailSecondActivity.class);
        intent.putExtra(TYPE, type);
        intent.putExtra(BindingPhoneOrEmailSecondActivity.TEXT, text);
        startActivity(intent);
        back();
    }
}
