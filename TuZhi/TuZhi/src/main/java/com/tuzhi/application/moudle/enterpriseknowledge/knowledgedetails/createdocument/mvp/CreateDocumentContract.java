package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.createdocument.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CreateDocumentContract {
    interface View extends BaseView {
        void commit(String content);

        void commitSuccess();
    }

    interface  Presenter extends BasePresenter<View> {
        void commit(String id,String content);
    }
}
