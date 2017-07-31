package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.createdocument.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

import java.io.File;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CreateDocumentContract {
    interface View extends BaseView {
        void commit();

        void commitSuccess();

        void uploadImageSuccess(String imageUrl);
    }

    interface  Presenter extends BasePresenter<View> {
        void commit(String id, String content);

        void uploadImage(android.view.View view, File imageFile);

        void inform(String id, String isCancel);
    }
}
