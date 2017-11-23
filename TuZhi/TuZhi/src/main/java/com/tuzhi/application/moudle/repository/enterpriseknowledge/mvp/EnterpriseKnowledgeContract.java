package com.tuzhi.application.moudle.repository.enterpriseknowledge.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.bean.HttpKnowledgeModuleBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.bean.KnowledgeCardItemBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class EnterpriseKnowledgeContract {
    interface View extends BaseView {
        void downloadFinish(HttpKnowledgeModuleBean.KnowledgeChannelMapBean knowledgeChannelMap, int page, boolean haveNextPage, ArrayList<KnowledgeCardItemBean> data);

        void downloadFinishNothing();

        void renameSuccess(String name);

        void deleteSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 获取数据
         *
         * @param id
         * @param sort 0为正序  1为倒序
         * @param page
         */
        void downLoadData(String id, String sort, int page);

        void deleteChannel(String klId, String kcId);

        void renameChannel(String klId, String kcId, final String name);
    }
}
