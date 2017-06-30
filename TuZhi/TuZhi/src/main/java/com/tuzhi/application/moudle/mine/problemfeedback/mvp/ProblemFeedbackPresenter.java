package com.tuzhi.application.moudle.mine.problemfeedback.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ProblemFeedbackPresenter extends BasePresenterImpl<ProblemFeedbackContract.View> implements ProblemFeedbackContract.Presenter {

    private static final String URL = "feedback";

    @Override
    public void commitFeedback(String text) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("content", text);
        HttpUtilsKt.post(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(String s, String text) {
                mView.back();
            }

            @Override
            public void onFailure(String text) {

            }
        });
    }
}
