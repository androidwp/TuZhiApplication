package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.bean.KnowledgeDetailsListBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class KnowledgeDetailsContract {

    interface View extends BaseView {
        void downLoadFinish(ArrayList<KnowledgeDetailsListBean> data, boolean haveNextPage, int page,String title);

        void downloadFinish();

        void updateProgress(int finishNumber, int totalNumber);

        void updateFinish();

        void skipCreateDocumentActivity(String editContentUrl);

        void canClick();

        void deleteSuccess();

        void renameSuccess(String name);
    }

    interface Presenter extends BasePresenter<View> {

        void renameCard(String id, String name);

        void deleteCard(String id);

        void downLoadData(String id, int page);

        void uploadFiles(android.view.View view, String aid, ArrayList<String> images);

        void cancelUpdate();

        void skipCreateDocumentActivity(String id);
    }
}
