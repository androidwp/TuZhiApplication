package com.tuzhi.application.moudle.mine.problemfeedback.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.ToastUtilsKt;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ProblemFeedbackPresenter extends BasePresenterImpl<ProblemFeedbackContract.View> implements ProblemFeedbackContract.Presenter {

    @Override
    public void commitFeedback(String text) {
        ToastUtilsKt.toast(mView.getContext(), text);
        mView.back();
    }
}
