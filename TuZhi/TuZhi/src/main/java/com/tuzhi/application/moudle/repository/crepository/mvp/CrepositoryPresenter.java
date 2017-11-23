package com.tuzhi.application.moudle.repository.crepository.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.repository.crepository.bean.HttpCreateCardBean;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CrepositoryPresenter extends BasePresenterImpl<CrepositoryContract.View> implements CrepositoryContract.Presenter {
    private final String URL_LIB = "tzkm/knowledgeLib";
    private final String URL_CHA = "tzkm/knowledgeChannel";
    private final String URL_MOU = "tzkm/editTitle";

    @Override
    public void commit(String name, String id) {
        addMoudle(name, id);
    }

    private void addChannel(String name, String libId) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "2");
        parameter.put("name", name);
        parameter.put("klId", libId);
        HttpUtilsKt.get(mView.getContext(), URL_CHA, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                //mView.commitFinish();
            }

            @Override
            public void onFailure(@NotNull String text) {
                mView.commitError();
            }
        });
    }

    private void addMoudle(String name, String libId) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "1");
        parameter.put("title", name);
        parameter.put("kcId", libId);
        HttpUtilsKt.post(mView.getContext(), URL_MOU, parameter, HttpCreateCardBean.class, new HttpCallBack<HttpCreateCardBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable HttpCreateCardBean bean, @NotNull String text) {
                mView.commitFinish(bean);
            }

            @Override
            public void onFailure(@NotNull String text) {
                mView.commitError();
            }
        });
    }

    private void addLib(String name) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "2");
        parameter.put("name", name);
        HttpUtilsKt.get(mView.getContext(), URL_LIB, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                // mView.commitFinish();
            }

            @Override
            public void onFailure(@NotNull String text) {
                mView.commitError();
            }
        });
    }
}
