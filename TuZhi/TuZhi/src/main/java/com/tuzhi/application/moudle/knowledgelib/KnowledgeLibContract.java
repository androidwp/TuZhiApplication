package com.tuzhi.application.moudle.knowledgelib;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class KnowledgeLibContract {
    interface View extends BaseView {
        void deleteLibSuccess();

        void renameSuccess(String name);
    }

    interface  Presenter extends BasePresenter<View> {
        void deleteLib(String id);

        void rename(String id, String name);
    }
}
