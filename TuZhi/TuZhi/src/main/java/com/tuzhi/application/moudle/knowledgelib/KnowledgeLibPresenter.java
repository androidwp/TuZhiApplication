package com.tuzhi.application.moudle.knowledgelib;

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

public class KnowledgeLibPresenter extends BasePresenterImpl<KnowledgeLibContract.View> implements KnowledgeLibContract.Presenter {
    private final String URL_LIB = "tzkm/knowledgeLib";

    @Override
    public void deleteLib(String id) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "4");
        parameter.put("klId", id);
        HttpUtilsKt.get(mView.getContext(), URL_LIB, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.deleteLibSuccess();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }

    @Override
    public void rename(String id, final String name) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "3");
        parameter.put("klId", id);
        parameter.put("name", name);
        HttpUtilsKt.get(mView.getContext(), URL_LIB, parameter, String.class, new HttpCallBack<String>() {
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
}
