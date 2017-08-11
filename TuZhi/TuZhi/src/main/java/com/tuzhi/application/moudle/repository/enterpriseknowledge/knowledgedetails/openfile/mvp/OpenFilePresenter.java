package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.openfile.mvp;

import com.alibaba.fastjson.JSONObject;
import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.FileUtils;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OpenFilePresenter extends BasePresenterImpl<OpenFileContract.View> implements OpenFileContract.Presenter {

    private static final String URL = "tzkm/downloadFile";
    private static final String URL_MOUDLE = "tzkm/editTitle";

    @Override
    public void renameFile(String id, final String name) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "2");
        parameter.put("fileId", id);
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
    public void deleteFile(String id) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "3");
        parameter.put("fileId", id);
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
    public void downloadFile(String aid, final String fid, final String fileName) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("aId", aid);
        parameter.put("fId", fid);
        parameter.put("operate", "2");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(String s, String text) {
                // {"resultMsg":"正常","resultCode":"0","downloadUrl":"http://192.168.0.132:8082//downloadFile?sx=png&hd=tuzhikmMobile&dy=20170627&fn=TopicRssListScreenShotZiXuanGuImage.png&fin=193715334422"}
                JSONObject jsonObject = JSONObject.parseObject(text);
                String downloadUrl = jsonObject.getString("downloadUrl");
                FileUtils.downloadFile(mView.getContext(), fid, fileName, downloadUrl);
            }

            @Override
            public void onFailure(String text) {

            }
        });
    }

    @Override
    public void reviewFile(String aid, String fid) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("aId", aid);
        parameter.put("fId", fid);
        parameter.put("operate", "1");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(String s, String text) {


            }

            @Override
            public void onFailure(String text) {

            }
        });
    }
}
