package com.tuzhi.application.moudle.mine.personalinformation.rename.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RenameContract {
    interface View extends BaseView {
        void back();
    }

    interface  Presenter extends BasePresenter<View> {
        void commitName(String name);
    }
}
