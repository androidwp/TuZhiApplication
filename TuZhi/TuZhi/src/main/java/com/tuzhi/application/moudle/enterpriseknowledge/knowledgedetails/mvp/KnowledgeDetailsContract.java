package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.mvp;

import com.jph.takephoto.model.TImage;
import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.bean.KnowledgeDetailsListBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class KnowledgeDetailsContract {

    interface View extends BaseView {
        void downLoadFinish(ArrayList<KnowledgeDetailsListBean> data, boolean haveNextPage, int page, String content);

        void updateProgress(int finishNumber, int totalNumber);

        void updateFinish();
    }

    interface Presenter extends BasePresenter<View> {
        void downLoadData(String id, int page);

        void uploadFiles(String aid, ArrayList<TImage> images);

        void cancelUpdate();
    }
}
