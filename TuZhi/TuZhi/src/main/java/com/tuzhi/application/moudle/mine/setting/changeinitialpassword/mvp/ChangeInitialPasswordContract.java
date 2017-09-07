package com.tuzhi.application.moudle.mine.setting.changeinitialpassword.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ChangeInitialPasswordContract {
    interface View extends BaseView {
        void changeSuccess();
    }

    interface  Presenter extends BasePresenter<View> {
        void commitPassword(String password, String againPassword);
    }
}
