package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.publishtopicorcomment.mvp;

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

public class PublishTopicOrCommentPresenter extends BasePresenterImpl<PublishTopicOrCommentContract.View> implements PublishTopicOrCommentContract.Presenter {

    private static final String URL = "tzkm/publishComment";

    @Override
    public void commit(String type, String aid,String cid, String content) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        if (type.equals(PublishTopicOrCommentActivity.TOPIC)) {
            parameter.put("oType", "1");
            parameter.put("oId", aid);
            parameter.put("cId", "0");
            parameter.put("pId", "0");
            parameter.put("content", content);
        } else {
            parameter.put("oType", "1");
            parameter.put("oId", aid);
            parameter.put("cId", cid);
            parameter.put("pId", "0");
            parameter.put("content", content);
        }

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
