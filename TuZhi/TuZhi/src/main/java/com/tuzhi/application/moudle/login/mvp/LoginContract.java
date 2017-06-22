package com.tuzhi.application.moudle.login.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;
import com.tuzhi.application.moudle.login.bean.User;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginContract {

    public interface View extends BaseView {
        //登录成功跳转到知识库
        void skip();
    }

    interface Presenter extends BasePresenter<View> {
        //提交后台检验账号密码
        void commitUser(User user);
    }

}
