package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.commentlist.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.commentlist.bean.CommentListBean;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.commentlist.bean.HttpCommentListBean;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.commentlist.item.CommentListItem;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CommentListPresenter extends BasePresenterImpl<CommentListContract.View> implements CommentListContract.Presenter {

    private static final String URL = "tzkm/comment";

    @Override
    public void downLoadData(String aid, final String cid, int page) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("cId", cid);
        parameter.put("pageNo", page + "");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, HttpCommentListBean.class, new HttpCallBack<HttpCommentListBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable HttpCommentListBean httpCommentListBean, @NotNull String text) {
                ArrayList<CommentListBean> data = new ArrayList<>();
                HttpCommentListBean.CommentBean comment = httpCommentListBean.getComment();
                HttpCommentListBean.CommentPageBean commentPage = httpCommentListBean.getCommentPage();
                boolean next = commentPage.isNext();
                int index = commentPage.getIndex();
                if (index == 0) {
                    CommentListBean commentListBean = new CommentListBean(CommentListItem.TYPE);
                    commentListBean.setInfo(comment.getContent());
                    commentListBean.setTime(comment.getTime());
                    commentListBean.setAuthor(comment.getNickname());
                    commentListBean.setImageUrl(comment.getUserImage());
                    data.add(commentListBean);
                }
                List<HttpCommentListBean.CommentPageBean.ResultBean> result = commentPage.getResult();
                for (HttpCommentListBean.CommentPageBean.ResultBean bean : result) {
                    CommentListBean commentListBean = new CommentListBean(CommentListItem.TYPE);
                    commentListBean.setInfo(bean.getContent());
                    commentListBean.setTime(bean.getTime());
                    commentListBean.setAuthor(bean.getNickname());
                    commentListBean.setImageUrl(bean.getUserImage());
                    data.add(commentListBean);
                }
                mView.downLoadFinish(index, next, data);
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });


    }
}
