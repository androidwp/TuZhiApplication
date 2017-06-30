package com.tuzhi.application.moudle.mine.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MineContract {
    interface View extends BaseView {
        void logOut();
    }

    interface  Presenter extends BasePresenter<View> {
        void logOut();
    }
}
