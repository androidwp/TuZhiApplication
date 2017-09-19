package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.mvp;

import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.bean.HttpKnowledgeDetailsListBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.bean.KnowledgeDetailsListBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.item.KnowledgeDetailsArticleItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.item.KnowledgeDetailsCommentItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.item.KnowledgeDetailsFileItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.item.KnowledgeDetailsFilesItem;
import com.tuzhi.application.utils.FileUtils;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;
import com.tuzhi.application.utils.LogUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class KnowledgeDetailsPresenter extends BasePresenterImpl<KnowledgeDetailsContract.View> implements KnowledgeDetailsContract.Presenter {

    private static final String URL = "tzkm/article";

    private static final String URL_EDIT = "tzkm/editArticle";

    private static final String URL_UPLOAD_FILE = "tzkm/addFile";

    private static final String URL_MOUDLE = "tzkm/editTitle";

    private int index;

    private int upDateIndex;

    private boolean canUpdate = true;

    @Override
    public void skipCreateDocumentActivity(String id) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("aId", id);
        parameter.put("operate", "1");
        HttpUtilsKt.post(mView.getContext(), URL_EDIT, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {
                mView.canClick();
            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                LogUtilsKt.showLog("TAG", text);
                JSONObject jsonObject = JSONObject.parseObject(text);
                mView.skipCreateDocumentActivity(jsonObject.getString("editContentUrl"));
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }


    @Override
    public void renameCard(String id, final String name) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "2");
        parameter.put("aId", id);
        parameter.put("title", name);
        HttpUtilsKt.post(mView.getContext(), URL_MOUDLE, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.renameSuccess(name);
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }

    @Override
    public void deleteCard(String id) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "3");
        parameter.put("aId", id);
        HttpUtilsKt.post(mView.getContext(), URL_MOUDLE, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.deleteSuccess();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }

    @Override
    public void downLoadData(final String id, int page) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("id", id);
        parameter.put("pageNo", page + "");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, HttpKnowledgeDetailsListBean.class, new HttpCallBack<HttpKnowledgeDetailsListBean>() {
            @Override
            public void onFinish() {
                mView.downloadFinish();
            }

            @Override
            public void onSuccess(@Nullable HttpKnowledgeDetailsListBean httpKnowledgeDetailsListBean, @NotNull String text) {
                LogUtilsKt.showLog("TAG", text);
                ArrayList<KnowledgeDetailsListBean> data = new ArrayList<>();
                //先添加文章
                addArticle(httpKnowledgeDetailsListBean, data);
                //文件
                addFiles(httpKnowledgeDetailsListBean, data);
                //评论
                addComment(httpKnowledgeDetailsListBean, data, id);

                HttpKnowledgeDetailsListBean.CommentPageBean commentPage = httpKnowledgeDetailsListBean.getCommentPage();

                mView.downLoadFinish(data, commentPage.isNext(), commentPage.getIndex(),httpKnowledgeDetailsListBean.getArticleMap().getTitle());
            }


            @Override
            public void onFailure(@NotNull String text) {

            }
        });

    }

    @Override
    public void uploadFiles(View view, String aid, ArrayList<String> images) {
        canUpdate = true;
        index = 0;
        uploadFile(view, aid, images, new ArrayList<String>());
    }

    @Override
    public void cancelUpdate() {
        canUpdate = false;
    }

    private void uploadFile(final View view, final String aid, final ArrayList<String> images, final ArrayList<String> urls) {
        if (index < images.size()) {
            File image = new File(images.get(index));
            HttpUtilsKt.uploadSummaryImage(mView.getContext(), "tuzhikmMobile", view, image, new HttpCallBack<String>() {
                @Override
                public void onFinish() {

                }

                @Override
                public void onSuccess(@Nullable String s, @NotNull String text) {
                    if (canUpdate) {
                        JSONObject jsonObject = JSONObject.parseObject(text);
                        String httpUrl = jsonObject.getString("httpUrl");
                        urls.add(httpUrl);
                        index++;
                        uploadFile(view, aid, images, urls);
                        mView.updateProgress(index, images.size());
                    }
                }

                @Override
                public void onFailure(@NotNull String text) {

                }
            });
        } else {
            if (canUpdate) {
                uploadData(aid, images, urls);
            }
        }

    }

    private void uploadData(String aid, final ArrayList<String> images, final ArrayList<String> fileUrl) {
        upDateIndex = 0;
        for (int i = 0; i < images.size(); i++) {
            String filePath = fileUrl.get(i);
            File file = new File(images.get(i));
            WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
            parameter.put("aId", aid);
            parameter.put("type", "2");
            parameter.put("name", file.getName());
            parameter.put("size", file.length() + "");
            parameter.put("suffix", file.getName().substring(file.getName().lastIndexOf(".") + 1));
            parameter.put("fileUrl", filePath);
            HttpUtilsKt.post(mView.getContext(), URL_UPLOAD_FILE, parameter, String.class, new HttpCallBack<String>() {
                @Override
                public void onFinish() {

                }

                @Override
                public void onSuccess(@Nullable String s, @NotNull String text) {
                    if (canUpdate) {
                        upDateIndex++;
                        if (images.size() == upDateIndex) {
                            mView.updateFinish();
                        }
                    }
                }

                @Override
                public void onFailure(@NotNull String text) {

                }
            });

        }
    }

    private void addComment(HttpKnowledgeDetailsListBean httpKnowledgeDetailsListBean, ArrayList<KnowledgeDetailsListBean> data, String aid) {
        HttpKnowledgeDetailsListBean.CommentPageBean commentPage = httpKnowledgeDetailsListBean.getCommentPage();
        List<HttpKnowledgeDetailsListBean.CommentPageBean.ResultBean> comments = commentPage.getResult();
        for (HttpKnowledgeDetailsListBean.CommentPageBean.ResultBean bean : comments) {
            KnowledgeDetailsListBean commentBean = new KnowledgeDetailsListBean(KnowledgeDetailsCommentItem.TYPE);
            commentBean.setAid(aid);
            commentBean.setCid(bean.getId());
            commentBean.setImageUrl(bean.getUserImage());
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
        if (articleFilesMap != null && articleFilesMap.size() > 0) {
            for (HttpKnowledgeDetailsListBean.ArticleFilesMapBean articleFilesMapBean : articleFilesMap) {
                KnowledgeDetailsListBean fileBean = new KnowledgeDetailsListBean(KnowledgeDetailsFileItem.TYPE);
                fileBean.setAid(httpKnowledgeDetailsListBean.getArticleMap().getId());
                fileBean.setFileId(articleFilesMapBean.getId());
                fileBean.setTitle(articleFilesMapBean.getFileName() + "." + articleFilesMapBean.getFileSuffix());
                fileBean.setFileName(articleFilesMapBean.getFileName());
                fileBean.setFileType(articleFilesMapBean.getFileSuffix());
                fileBean.setFileSize(articleFilesMapBean.getFileSize());
                fileBean.setPreviewUrls(articleFilesMapBean.getPreviewUrls());
                fileBean.setFileStatus(articleFilesMapBean.isPreview());
                fileBean.setInfo(articleFilesMapBean.getAuthor() + "  " + articleFilesMapBean.getUpdateTime() + "  " + articleFilesMapBean.getFileSize());
                fileBean.setDownloadStatus(FileUtils.fileExist(mView.getContext(), articleFilesMapBean.getId()));
                dataFiles.add(fileBean);
            }
            bean.setFiles(dataFiles);
            data.add(bean);
        }
    }

    private void addArticle(HttpKnowledgeDetailsListBean articleMap, ArrayList<KnowledgeDetailsListBean> data) {
        HttpKnowledgeDetailsListBean.ArticleMapBean articleMapBean = articleMap.getArticleMap();
        if (!TextUtils.isEmpty(articleMapBean.getContent()) && !TextUtils.equals(articleMapBean.getContent(), "<p><br></p>")) {
            KnowledgeDetailsListBean bean = new KnowledgeDetailsListBean(KnowledgeDetailsArticleItem.TYPE);
            bean.setContent(articleMapBean.getContent());
            bean.setViewContentUrl(articleMapBean.getViewContentUrl());
            data.add(bean);
        }
    }
}
