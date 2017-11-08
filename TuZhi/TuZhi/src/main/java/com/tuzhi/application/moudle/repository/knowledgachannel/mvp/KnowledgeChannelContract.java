package com.tuzhi.application.moudle.repository.knowledgachannel.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;
import com.tuzhi.application.moudle.repository.knowledgachannel.bean.KnowledgeChannelItemBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class KnowledgeChannelContract {
    interface View extends BaseView {
        void downloadFinish(int page, boolean haveNextPage, ArrayList<KnowledgeChannelItemBean> data);

        void downloadFinishNothing();

    }

    interface Presenter extends BasePresenter<View> {
        void downLoadData(String id, int page);


    }
}
