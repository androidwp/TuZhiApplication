package com.tuzhi.application.moudle.createknowledgelib;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class CreateKnowledgeLibPresenter extends BasePresenterImpl<CreateKnowledgeLibContract.View> implements CreateKnowledgeLibContract.Presenter {

    private static final String URL = "tzkm/knowledgeLib";

    @Override
    public void createLib(String type, CreateKnowledgeLibBean bean) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        //2是添加3是修改
        parameter.put("operate", type.equals(CreateKnowledgeLibActivity.TYPE_CREATE) ? "2" : "3");
        parameter.put("type", bean.getLibTypeId());
        parameter.put("name", bean.getLibName());
        //0是公开1是私密
        parameter.put("isOpen", bean.isLibOpenness() ? "0" : "1");
        parameter.put("coverImage", bean.getLibIcon());
        HttpUtilsKt.get(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.createLibSuccess();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }
}
