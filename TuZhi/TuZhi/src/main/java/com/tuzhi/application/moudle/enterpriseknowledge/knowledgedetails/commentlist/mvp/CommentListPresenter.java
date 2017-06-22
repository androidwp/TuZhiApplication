package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.commentlist.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.commentlist.bean.CommentListBean;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.commentlist.item.CommentListItem;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CommentListPresenter extends BasePresenterImpl<CommentListContract.View> implements CommentListContract.Presenter {

    @Override
    public void downLoadData(String aid, String cid, int page) {
        ArrayList<CommentListBean> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CommentListBean bean = new CommentListBean(CommentListItem.TYPE);
            bean.setInfo("杭州图知信息科技有限公司致力于打造一个互联网时代的企业智能学习工具。");
            bean.setTime("03-13  23:45");
            bean.setAuthor("马得勇");
            data.add(bean);
        }
        mView.downLoadFinish(page, true, data);
    }
}
