package com.tuzhi.application.moudle.repository.knowledgachannel.mvp;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.repository.knowledgachannel.bean.KnowledgeChannelHttpBean;
import com.tuzhi.application.moudle.repository.knowledgachannel.bean.KnowledgeChannelItemBean;
import com.tuzhi.application.moudle.repository.knowledgachannel.item.CreateChannelItem;
import com.tuzhi.application.moudle.repository.knowledgachannel.item.KnowledgeChannelItem;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import org.greenrobot.eventbus.EventBus;
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

public class KnowledgeChannelPresenter extends BasePresenterImpl<KnowledgeChannelContract.View> implements KnowledgeChannelContract.Presenter {

    private final String URL = "tzkm/knowledgeChannel";

    @Override
    public void downLoadData(final String id, final int page) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("klId", id);
        parameter.put("operate", "1");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, KnowledgeChannelHttpBean.class, new HttpCallBack<KnowledgeChannelHttpBean>() {
            @Override
            public void onFinish() {
                mView.downloadFinishNothing();
            }

            @Override
            public void onSuccess(@Nullable KnowledgeChannelHttpBean knowledgeChannelHttpBean, @NotNull String text) {
                List<KnowledgeChannelHttpBean.KnowledgeChannelsMapBean> knowledgeChannelsMap = knowledgeChannelHttpBean.getKnowledgeChannelsMap();
                ArrayList<KnowledgeChannelItemBean> data = new ArrayList<>();
                //创建频道
                if (page == 0) {
                    KnowledgeChannelItemBean bean = new KnowledgeChannelItemBean(CreateChannelItem.TYPE);
                    bean.setKlid(id);
                    data.add(bean);
                }
                if (knowledgeChannelsMap != null) {
                    for (KnowledgeChannelHttpBean.KnowledgeChannelsMapBean knowledgeChannelsMapBean : knowledgeChannelsMap) {
                        KnowledgeChannelItemBean bean = new KnowledgeChannelItemBean(KnowledgeChannelItem.TYPE);
                        bean.setKlid(id);
                        bean.setKcid(knowledgeChannelsMapBean.getId());
                        bean.setTitle(knowledgeChannelsMapBean.getName());
                        bean.setText(knowledgeChannelsMapBean.getArticleCount() + " 知识卡片");
                        bean.setFlag(knowledgeChannelsMapBean.isOpen());
                        data.add(bean);
                    }
                }
                KnowledgeChannelHttpBean.KnowledgeLibMapBean knowledgeLibMap = knowledgeChannelHttpBean.getKnowledgeLibMap();
                sentEventToKnowledgeLib(knowledgeLibMap);
                mView.downloadFinish(0, false, data);
            }

            @Override
            public void onFailure(@NotNull String text) {
                ArrayList<KnowledgeChannelItemBean> data = new ArrayList<>();
                if (page == 0) {
                    KnowledgeChannelItemBean bean = new KnowledgeChannelItemBean(CreateChannelItem.TYPE);
                    bean.setKlid(id);
                    data.add(bean);
                }
                KnowledgeChannelHttpBean knowledgeChannelHttpBean = JSON.parseObject(text, KnowledgeChannelHttpBean.class);
                if (TextUtils.equals(knowledgeChannelHttpBean.getResultCode(), ConstantKt.getLIST_NOT_CONTENT())) {
                    KnowledgeChannelHttpBean.KnowledgeLibMapBean knowledgeLibMap = knowledgeChannelHttpBean.getKnowledgeLibMap();
                    sentEventToKnowledgeLib(knowledgeLibMap);
                    mView.downloadFinish(0, false, data);
                }
            }
        });

    }

    /**
     * 回传 知识库信息
     *
     * @param knowledgeLibMap
     */
    private void sentEventToKnowledgeLib(KnowledgeChannelHttpBean.KnowledgeLibMapBean knowledgeLibMap) {
        EventBus.getDefault().post(knowledgeLibMap);
    }
}
