package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.mvp;

import android.text.Html;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.bean.HttpKnowledgeDetailsListBean;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.bean.KnowledgeDetailsListBean;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.item.KnowledgeDetailsArticleItem;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.item.KnowledgeDetailsCommentItem;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.item.KnowledgeDetailsFileItem;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.item.KnowledgeDetailsFilesItem;
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

public class KnowledgeDetailsPresenter extends BasePresenterImpl<KnowledgeDetailsContract.View> implements KnowledgeDetailsContract.Presenter {

    private static final String URL = "tzkm/article";

    @Override
    public void downLoadData(String id, int page) {

        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("id", id);
        parameter.put("pageNo", page + "");

        HttpUtilsKt.get(mView.getContext(), URL, parameter, HttpKnowledgeDetailsListBean.class, new HttpCallBack<HttpKnowledgeDetailsListBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable HttpKnowledgeDetailsListBean httpKnowledgeDetailsListBean, @NotNull String text) {
                ArrayList<KnowledgeDetailsListBean> data = new ArrayList<>();
                //先添加文章
                String content = addArticle(httpKnowledgeDetailsListBean, data);
                //文件
                addFiles(httpKnowledgeDetailsListBean, data);
                //评论
                addComment(httpKnowledgeDetailsListBean, data);

                HttpKnowledgeDetailsListBean.CommentPageBean commentPage = httpKnowledgeDetailsListBean.getCommentPage();

                mView.downLoadFinish(data, commentPage.isNext(), commentPage.getIndex(),content);
            }


            @Override
            public void onFailure(@NotNull String text) {

            }
        });

    }

    private void addComment(HttpKnowledgeDetailsListBean httpKnowledgeDetailsListBean, ArrayList<KnowledgeDetailsListBean> data) {
        HttpKnowledgeDetailsListBean.CommentPageBean commentPage = httpKnowledgeDetailsListBean.getCommentPage();
        List<HttpKnowledgeDetailsListBean.CommentPageBean.ResultBean> comments = commentPage.getResult();
        for (HttpKnowledgeDetailsListBean.CommentPageBean.ResultBean bean : comments) {
            KnowledgeDetailsListBean commentBean = new KnowledgeDetailsListBean(KnowledgeDetailsCommentItem.TYPE);
            commentBean.setAuthor(bean.getNickname());
            commentBean.setTime(bean.getTime());
            commentBean.setInfo(bean.getContent());
            commentBean.setCommentNumber(bean.getReplyNum());
            commentBean.setPraiseNumber(bean.getPraiseNum());
            commentBean.setPraiseStatus(bean.isUserPraiseStatus());
            data.add(commentBean);
        }
    }

    private void addFiles(HttpKnowledgeDetailsListBean httpKnowledgeDetailsListBean, ArrayList<KnowledgeDetailsListBean> data) {
        KnowledgeDetailsListBean bean = new KnowledgeDetailsListBean(KnowledgeDetailsFilesItem.TYPE);
        ArrayList<KnowledgeDetailsListBean> dataFiles = new ArrayList<>();
        List<HttpKnowledgeDetailsListBean.ArticleFilesMapBean> articleFilesMap = httpKnowledgeDetailsListBean.getArticleFilesMap();
        for (HttpKnowledgeDetailsListBean.ArticleFilesMapBean articleFilesMapBean : articleFilesMap) {
            KnowledgeDetailsListBean fileBean = new KnowledgeDetailsListBean(KnowledgeDetailsFileItem.TYPE);
            fileBean.setTitle(articleFilesMapBean.getFileName());
            fileBean.setInfo(articleFilesMapBean.getAuthor() + "  " + articleFilesMapBean.getUpdateTime() + "  " + articleFilesMapBean.getFileSize());
            fileBean.setDownloadStatus("");
            dataFiles.add(fileBean);
        }
        bean.setFiles(dataFiles);
        data.add(bean);

    }

    private String addArticle(HttpKnowledgeDetailsListBean articleMap, ArrayList<KnowledgeDetailsListBean> data) {
        HttpKnowledgeDetailsListBean.ArticleMapBean articleMapBean = articleMap.getArticleMap();
        KnowledgeDetailsListBean bean = new KnowledgeDetailsListBean(KnowledgeDetailsArticleItem.TYPE);
        bean.setContent(Html.fromHtml(articleMapBean.getContent()).toString());
        data.add(bean);
        return bean.getContent();
    }
}
