package com.tuzhi.application.moudle.mine.setting.settingpush.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SettingPushPresenter extends BasePresenterImpl<SettingPushContract.View> implements SettingPushContract.Presenter {

    private static final String URL = "setNoticeStatus";

    @Override
    public void commitUpdateStatue(String operate, String status) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", operate);
        parameter.put("status", status);
        HttpUtilsKt.post(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(String s, String text) {

            }

            @Override
            public void onFailure(String text) {

            }
        });
    }
}
