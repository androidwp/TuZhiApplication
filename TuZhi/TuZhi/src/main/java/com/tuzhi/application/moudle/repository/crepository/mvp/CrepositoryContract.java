package com.tuzhi.application.moudle.repository.crepository.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CrepositoryContract {
    interface View extends BaseView {
        void commitFinish();
    }

    interface Presenter extends BasePresenter<View> {
        void commit(String type, String name, String libId);
    }
}
