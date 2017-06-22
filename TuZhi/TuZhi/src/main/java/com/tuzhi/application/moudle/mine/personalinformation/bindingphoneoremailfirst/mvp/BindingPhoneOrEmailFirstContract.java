package com.tuzhi.application.moudle.mine.personalinformation.bindingphoneoremailfirst.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BindingPhoneOrEmailFirstContract {
    interface View extends BaseView {
         void skipNextPage(String text);
    }

    interface Presenter extends BasePresenter<View> {
        void commitPhoneOrEmail(String text);
    }
}
