package com.tuzhi.application.moudle.mine.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MinePresenter extends BasePresenterImpl<MineContract.View> implements MineContract.Presenter {

    private static final String URL = "logout";

    @Override
    public void logOut() {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        HttpUtilsKt.get(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(String s, String text) {
                mView.logOut();
            }

            @Override
            public void onFailure(String text) {

            }
        });
    }
}
