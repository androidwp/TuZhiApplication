package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.publishtopicorcomment.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PublishTopicOrCommentContract {
    interface View extends BaseView {
        void commitSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void commit(String type, String aid,String cid, String content);
    }
}
