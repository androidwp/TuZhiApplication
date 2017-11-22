package com.tuzhi.application.moudle.createchannel;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class CreateChannelPresenter extends BasePresenterImpl<CreateChannelContract.View> implements CreateChannelContract.Presenter {

    private static final String URL = "tzkm/knowledgeChannel";

    @Override
    public void createChannel(CreateChannelBean bean) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "2");
        parameter.put("klId", bean.getKlId());
        parameter.put("name", bean.getChannelName());
        parameter.put("summary", bean.getChannelSummery());
        parameter.put("isOpen", bean.isOpenOrSecret() ? "0" : "1");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.createChannelSuccess();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });

    }
}
