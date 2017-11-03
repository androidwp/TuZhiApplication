package com.tuzhi.application.moudle.applyfortrial;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ApplyForTrialPresenter extends BasePresenterImpl<ApplyForTrialContract.View> implements ApplyForTrialContract.Presenter {

    private String URL = "apply/try";

    @Override
    public void commit(ApplyForTrialBean bean) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("name", bean.getName());
        parameter.put("phoneOrEmail", bean.getPhoneOrEmail());
        parameter.put("companyName", bean.getCompany());
        HttpUtilsKt.post(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(String s, String text) {
                mView.commitSuccess();
            }

            @Override
            public void onFailure(String text) {

            }
        });
    }
}
