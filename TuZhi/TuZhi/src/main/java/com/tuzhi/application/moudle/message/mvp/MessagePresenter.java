package com.tuzhi.application.moudle.message.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MessagePresenter extends BasePresenterImpl<MessageContract.View> implements MessageContract.Presenter {

    private static final String URL = "user/notice";

    @Override
    public void allMarkedAsRead() {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "4");
        parameter.put("pageNo", "0");
        parameter.put("rStatus", "0");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(String s, String text) {
                mView.allMarkedAsReadSuccess();
            }

            @Override
            public void onFailure(String text) {

            }
        });
    }
}
