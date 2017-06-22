package com.tuzhi.application.moudle.mine.personalinformation.bindingphoneoremailsecond.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;

/**
 * MVPPlugin
 *
 */

public class BindingPhoneOrEmailSecondPresenter extends BasePresenterImpl<BindingPhoneOrEmailSecondContract.View> implements BindingPhoneOrEmailSecondContract.Presenter {

    //向后台请求短信验证码
    @Override
    public void sendNote(String type, String text) {
        mView.sendSuccess();
    }

    @Override
    public void commitAuthCode(String text) {
        mView.back();
    }


}
