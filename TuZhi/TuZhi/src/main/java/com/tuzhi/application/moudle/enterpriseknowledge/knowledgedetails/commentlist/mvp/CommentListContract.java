package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.commentlist.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.commentlist.bean.CommentListBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CommentListContract {
    interface View extends BaseView {
        void downLoadFinish(int page, boolean haveNextPage, ArrayList<CommentListBean> data);
    }

    interface Presenter extends BasePresenter<View> {
        void downLoadData(String aid, String cid, int page);
    }
}
