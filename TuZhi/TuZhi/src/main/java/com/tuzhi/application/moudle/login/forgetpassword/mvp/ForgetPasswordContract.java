package com.tuzhi.application.moudle.login.forgetpassword.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ForgetPasswordContract {
    interface View extends BaseView {
        void getVerificationCodeSuccess(String phoneOrEmail);

        void getVerificationCodeError();

        void changePasswordSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getVerificationCode(String type, String phoneOrEmail);

        void changePassword(String type, String phoneOrEmail,String verificationCode,String password);
    }
}
