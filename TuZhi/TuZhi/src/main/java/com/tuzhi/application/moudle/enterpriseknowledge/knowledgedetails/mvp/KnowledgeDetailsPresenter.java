package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.mvp;

import android.text.Html;

import com.alibaba.fastjson.JSONObject;
import com.jph.takephoto.model.TImage;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class KnowledgeDetailsPresenter extends BasePresenterImpl<KnowledgeDetailsContract.View> implements KnowledgeDetailsContract.Presenter {

    private static final String URL = "tzkm/article";

    private static final String URL_UPLOAD_FILE = "tzkm/addFile";

    private static final String URL_IMAGE = "http://upload.guigutang.com:8082/upload.htm?app=userImage&type=json";

    private int index;
    private int upDateIndex;

    private boolean canUpdate = true;

    @Override
    public void downLoadData(final String id, int page) {

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
                addComment(httpKnowledgeDetailsListBean, data, id);

                HttpKnowledgeDetailsListBean.CommentPageBean commentPage = httpKnowledgeDetailsListBean.getCommentPage();

                mView.downLoadFinish(data, commentPage.isNext(), commentPage.getIndex(), content);
            }


            @Override
            public void onFailure(@NotNull String text) {

            }
        });

    }

    @Override
    public void uploadFiles(String aid, ArrayList<TImage> images) {
        canUpdate = true;
        index = 0;
        uploadFile(aid, images, new ArrayList<String>());
    }

    @Override
    public void cancelUpdate() {
        canUpdate = false;
    }

    private void uploadFile(final String aid, final ArrayList<TImage> images, final ArrayList<String> urls) {
        if (index < images.size()) {
            TImage tImage = images.get(index);
            File image = new File(tImage.getOriginalPath());
            MultipartBody.Part[] files = new MultipartBody.Part[1];
            files[0] = MultipartBody.Part.createFormData("file", image.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), image));
            WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
            HttpUtilsKt.uploadFile(mView.getContext(), URL_IMAGE, files, parameter, new HttpCallBack<String>() {
                @Override
                public void onFinish() {

                }

                @Override
                public void onSuccess(@org.jetbrains.annotations.Nullable String s, @NotNull String text) {
                    //{"resultMsg":"文件上传成功","httpUrl":"http://upload.guigutang.com:8082/userImage/20170622/164839766757.jpg","thumbUrl":"http://upload.guigutang.com:8082/userImage/20170622/164839766757.jpg.png","resultCode":0}
                    if (canUpdate) {
                        JSONObject jsonObject = JSONObject.parseObject(text);
                        String httpUrl = jsonObject.getString("httpUrl");
                        urls.add(httpUrl);
                        index++;
                        uploadFile(aid, images, urls);
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

    private void uploadData(String aid, final ArrayList<TImage> images, final ArrayList<String> fileUrl) {
        upDateIndex = 0;
        for (int i = 0; i < images.size(); i++) {
            TImage tImage = images.get(i);
            String filePath = fileUrl.get(i);
            File file = new File(tImage.getOriginalPath());
            WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
            parameter.put("aId", aid);
            parameter.put("type", "2");
            parameter.put("name", file.getName());
            parameter.put("size", file.length() + "");
            parameter.put("suffix", "jpg");
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
