package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.createdocument.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CreateDocumentPresenter extends BasePresenterImpl<CreateDocumentContract.View> implements CreateDocumentContract.Presenter {

    private static final String URL = "tzkm/editArticle";

    @Override
    public void commit(String id, String content) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("aId", id);
        parameter.put("content", content);
        HttpUtilsKt.post(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.commitSuccess();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }
}
