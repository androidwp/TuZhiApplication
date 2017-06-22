package com.tuzhi.application.moudle.mine.personalinformation.bindingphoneoremailsecond.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BindingPhoneOrEmailSecondContract {
    interface View extends BaseView {

        void back();

        void sendSuccess();

    }

    interface  Presenter extends BasePresenter<View> {

        //获取验证码
        void sendNote(String type,String text);

        //提交验证码
        void commitAuthCode(String text);
    }
}
