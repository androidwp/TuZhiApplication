package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.openfile.mvp;

import com.alibaba.fastjson.JSONObject;
import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.FileUtils;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OpenFilePresenter extends BasePresenterImpl<OpenFileContract.View> implements OpenFileContract.Presenter {

    private static final String URL = "tzkm/downloadFile";

    @Override
    public void downloadFile(String aid, final String fid, final String fileName) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("aId", aid);
        parameter.put("fId", fid);
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
}
