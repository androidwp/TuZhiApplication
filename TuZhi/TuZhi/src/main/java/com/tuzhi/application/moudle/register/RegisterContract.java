package com.tuzhi.application.moudle.register;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class RegisterContract {
    interface View extends BaseView {
        void commitSuccess();

        void getAuthCodeSuccess();

        void getAuthCodeErrno();
    }

    interface Presenter extends BasePresenter<View> {
        void getAuthCode(String phone);

        void register(RegisterBean bean);
    }
}
