package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.checkhistoricalversion.recoverhistoryversion.mvp;

import com.alibaba.fastjson.JSONObject;
import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RecoverHistoryVersionPresenter extends BasePresenterImpl<RecoverHistoryVersionContract.View> implements RecoverHistoryVersionContract.Presenter {

    public static final String URL = "tzkm/historyVersion";

    @Override
    public void downloadData(String aid, String id) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "2");
        parameter.put("pageNo", "0");
        parameter.put("aId", aid);
        parameter.put("id", id);
        HttpUtilsKt.get(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(String info, String text) {
                JSONObject jsonObject = JSONObject.parseObject(text);
                JSONObject editRecordMap = jsonObject.getJSONObject("editRecordMap");
                String viewContentUrl = editRecordMap.getString("viewContentUrl");
                String title = editRecordMap.getString("title");
                mView.downloadSuccess(title,viewContentUrl);
            }

            @Override
            public void onFailure(String text) {

            }
        });
    }

    @Override
    public void recoverHistory(String aid, String id) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "3");
        parameter.put("pageNo", "0");
        parameter.put("aId", aid);
        parameter.put("id", id);
        HttpUtilsKt.get(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(String info, String text) {
                mView.recoverSuccess();
            }

            @Override
            public void onFailure(String text) {

            }
        });
    }
}
