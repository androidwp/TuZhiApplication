package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.commentlist.mvp;

import android.text.TextUtils;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.commentlist.bean.CommentListBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.commentlist.bean.HttpCommentListBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.commentlist.item.CommentListHeadItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.commentlist.item.CommentListItem;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;
import com.tuzhi.application.utils.LogUtilsKt;

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
    private static final String URL_PRAISE = "tzkm/praise";

    @Override
    public void downLoadData(String aid, final String cid, int page) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "1");
        parameter.put("cId", cid);
        parameter.put("pageNo", page + "");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, HttpCommentListBean.class, new HttpCallBack<HttpCommentListBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable HttpCommentListBean httpCommentListBean, @NotNull String text) {
                LogUtilsKt.showLog("评论列表",text);
                ArrayList<CommentListBean> data = new ArrayList<>();
                HttpCommentListBean.CommentBean comment = httpCommentListBean.getComment();
                HttpCommentListBean.CommentPageBean commentPage = httpCommentListBean.getCommentPage();
                if (commentPage != null) {
                    boolean next = commentPage.isNext();
                    int index = commentPage.getIndex();
                    //根部评论
                    if (index == 0) {
                        CommentListBean commentListBean = new CommentListBean(CommentListHeadItem.TYPE);
                        commentListBean.setInfo(comment.getContent());
                        commentListBean.setTime(comment.getTime());
                        commentListBean.setAuthor(comment.getNickname());
                        commentListBean.setImageUrl(comment.getUserImage());
                        commentListBean.setCommentNumber("评论 (" + commentPage.getCount() + ")");
                        data.add(commentListBean);
                    }
                    List<HttpCommentListBean.CommentPageBean.ResultBean> result = commentPage.getResult();
                    for (HttpCommentListBean.CommentPageBean.ResultBean bean : result) {
                        CommentListBean commentListBean = new CommentListBean(CommentListItem.TYPE);
                        commentListBean.setId(bean.getId());
                        commentListBean.setInfo(bean.getContent());
                        commentListBean.setTime(bean.getTime());
                        commentListBean.setAuthor(bean.getNickname());
                        commentListBean.setImageUrl(bean.getUserImage());
                        data.add(commentListBean);
                    }
                    mView.downLoadFinish(index, next, data, comment.isUserPraiseStatus(), comment.getPraiseNum());
                } else {
                    CommentListBean commentListBean = new CommentListBean(CommentListHeadItem.TYPE);
                    commentListBean.setInfo(comment.getContent());
                    commentListBean.setTime(comment.getTime());
                    commentListBean.setAuthor(comment.getNickname());
                    commentListBean.setImageUrl(comment.getUserImage());
                    commentListBean.setCommentNumber("评论 (0)");
                    data.add(commentListBean);
                    mView.downLoadFinish(0, false, data, comment.isUserPraiseStatus(), comment.getPraiseNum());
                }

            }

            @Override
            public void onFailure(@NotNull String text) {
                if (TextUtils.equals(text, "列表无内容显示")) {
                    mView.downLoadFinish(0, false, null, false, "0");
                }else {
                    mView.deleteCommentSuccess();
                }
            }
        });
    }

    @Override
    public void commitClickPraise(String aid, String cid, final String praiseNumber) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("falg", "1");
        parameter.put("oType", "1");
        parameter.put("cType", "1");
        parameter.put("oId", aid);
        parameter.put("cId", cid);
        HttpUtilsKt.post(mView.getContext(), URL_PRAISE, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                int mPraiseNumber = Integer.parseInt(praiseNumber);
                mView.clickPraiseSuccess(mPraiseNumber + 1 + "");
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }

    @Override
    public void deleteCommentList(String cid) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "2");
        parameter.put("cId", cid);
        parameter.put("pageNo", "0");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(String s, String text) {
                mView.deleteCommentListSuccess();
            }

            @Override
            public void onFailure(String text) {

            }
        });

    }

    @Override
    public void deleteComment(String cid) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "2");
        parameter.put("cId", cid);
        parameter.put("pageNo", "0");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(String s, String text) {
                mView.deleteCommentSuccess();
            }

            @Override
            public void onFailure(String text) {

            }
        });
    }
}
