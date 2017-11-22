package com.tuzhi.application.moudle.choosetemplatechannel;

import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.chooselibtype.ChooseLibTypeItem;
import com.tuzhi.application.moudle.repository.knowledgachannel.bean.KnowledgeChannelHttpBean;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class ChooseTemplateChannelPresenter extends BasePresenterImpl<ChooseTemplateChannelContract.View> implements ChooseTemplateChannelContract.Presenter {

    private final String URL_CHANNEL = "tzkm/knowledgeChannel";
    private final String URL_CREATE = "tzkm/importTemplate";

    @Override
    public void downloadData(String id) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("klId", id);
        parameter.put("operate", "1");
        HttpUtilsKt.get(mView.getContext(), URL_CHANNEL, parameter, KnowledgeChannelHttpBean.class, new HttpCallBack<KnowledgeChannelHttpBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable KnowledgeChannelHttpBean knowledgeChannelHttpBean, @NotNull String text) {
                List<KnowledgeChannelHttpBean.KnowledgeChannelsMapBean> knowledgeChannelsMap = knowledgeChannelHttpBean.getKnowledgeChannelsMap();
                ArrayList<ItemBean> data = new ArrayList<>();
                for (KnowledgeChannelHttpBean.KnowledgeChannelsMapBean knowledgeChannelsMapBean : knowledgeChannelsMap) {
                    ItemBean bean = new ItemBean(ChooseLibTypeItem.TYPE);
                    bean.setId(knowledgeChannelsMapBean.getId());
                    bean.setTitle(knowledgeChannelsMapBean.getName());
                    data.add(bean);
                }
                mView.downloadSuccess(data);
            }

            @Override
            public void onFailure(@NotNull String text) {
            }
        });
    }

    @Override
    public void createLib(String id, String ids) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("klId", id);
        parameter.put("kcIds", ids);
        HttpUtilsKt.post(mView.getContext(), URL_CREATE, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.createLibSuccess();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }
}
