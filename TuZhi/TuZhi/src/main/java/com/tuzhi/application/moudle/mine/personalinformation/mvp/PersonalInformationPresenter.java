package com.tuzhi.application.moudle.mine.personalinformation.mvp;

import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.tuzhi.application.bean.HttpInitBean;
import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PersonalInformationPresenter extends BasePresenterImpl<PersonalInformationContract.View> implements PersonalInformationContract.Presenter {
    private static final String URL = "user/updateStaffInfo";
    private static final String URL_USER_INFO = "user/staffDetail";


    @Override
    public void uploadImage(View view, final String name, File image) {
        HttpUtilsKt.uploadSummaryImage(mView.getContext(), "userImage", view, image, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                JSONObject jsonObject = JSONObject.parseObject(text);
                String httpUrl = jsonObject.getString("httpUrl");
                PersonalInformationPresenter.this.uploadData(name, httpUrl);
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });

    }

    @Override
    public void downloadUserInfo() {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        HttpUtilsKt.get(mView.getContext(), URL_USER_INFO, parameter, HttpInitBean.class, new HttpCallBack<HttpInitBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable HttpInitBean httpInitBean, @NotNull String text) {
                mView.downloadSuccess(httpInitBean);
            }

            @Override
            public void onFailure(@NotNull String text) {
                mView.downloadError();
            }
        });
    }

    private void uploadData(String name, final String fileUrl) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("nickname", name);
        parameter.put("userImage", fileUrl);
        HttpUtilsKt.post(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.uploadFinish(fileUrl);
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }
}
