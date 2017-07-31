package com.tuzhi.application.moudle.repository.enterpriseknowledge.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.bean.EnterpriseKnowledgeListItemBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class EnterpriseKnowledgeContract {
    interface View extends BaseView {
        void downloadFinish(int page, boolean haveNextPage, ArrayList<EnterpriseKnowledgeListItemBean> data);

        void downloadFinishNothing();

        void setTitle(String name);
    }

    interface Presenter extends BasePresenter<View> {
        void downLoadData(String id, int page);
    }
}
