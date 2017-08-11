package com.tuzhi.application.moudle.message.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MessageContract {
    interface View extends BaseView {
        void allMarkedAsReadSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void allMarkedAsRead();
    }
}
