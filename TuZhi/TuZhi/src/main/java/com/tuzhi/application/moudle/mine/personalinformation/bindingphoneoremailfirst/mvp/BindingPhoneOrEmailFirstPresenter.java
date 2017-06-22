package com.tuzhi.application.moudle.mine.personalinformation.bindingphoneoremailfirst.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BindingPhoneOrEmailFirstPresenter extends BasePresenterImpl<BindingPhoneOrEmailFirstContract.View> implements BindingPhoneOrEmailFirstContract.Presenter {

    //提交检查号码正确性。
    @Override
    public void commitPhoneOrEmail(String text) {
        //验证成功跳转
        mView.skipNextPage(text);
    }
}
