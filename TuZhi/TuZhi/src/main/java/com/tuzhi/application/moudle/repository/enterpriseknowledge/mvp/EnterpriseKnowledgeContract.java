package com.tuzhi.application.moudle.repository.enterpriseknowledge.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.bean.KnowledgeCardItemBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class EnterpriseKnowledgeContract {
    interface View extends BaseView {
        void downloadFinish(int page, boolean haveNextPage, ArrayList<KnowledgeCardItemBean> data);

        void downloadFinishNothing();

        void renameSuccess(String name);

        void deleteSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void downLoadData(String id, int page);

        void deleteChannel(String klId, String kcId);

        void renameChannel(String klId, String kcId, final String name);
    }
}
