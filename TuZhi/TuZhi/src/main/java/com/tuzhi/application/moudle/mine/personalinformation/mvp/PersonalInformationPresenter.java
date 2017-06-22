package com.tuzhi.application.moudle.mine.personalinformation.mvp;

import com.alibaba.fastjson.JSONObject;
import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PersonalInformationPresenter extends BasePresenterImpl<PersonalInformationContract.View> implements PersonalInformationContract.Presenter {

    private static final String URL_IMAGE = "http://upload.guigutang.com:8082/upload.htm?app=userImage&type=json";
    private static final String URL = "user/updateStaffInfo";

    @Override
    public void uploadImage(final String name, File image) {
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
                JSONObject jsonObject = JSONObject.parseObject(text);
                String httpUrl = jsonObject.getString("httpUrl");
                PersonalInformationPresenter.this.uploadData(name, httpUrl);
            }

            @Override
            public void onFailure(@NotNull String text) {

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
