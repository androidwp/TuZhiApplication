package com.tuzhi.application.moudle.clipper.clippertwo.mvp;

import android.text.TextUtils;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.repository.knowledgachannel.bean.KnowledgeChannelHttpBean;
import com.tuzhi.application.moudle.repository.knowledgachannel.bean.KnowledgeChannelItemBean;
import com.tuzhi.application.moudle.repository.knowledgachannel.item.KnowledgeChannelItem;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;
import com.tuzhi.application.utils.LogUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ClipperTwoPresenter extends BasePresenterImpl<ClipperTwoContract.View> implements ClipperTwoContract.Presenter {

    private final String URL = "tzkm/knowledgeChannel";

    @Override
    public void downLoadData(final String id, int page) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("klId", id);
        parameter.put("operate", "1");
        //parameter.put("pageNo", page + "");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, KnowledgeChannelHttpBean.class, new HttpCallBack<KnowledgeChannelHttpBean>() {
            @Override
            public void onFinish() {
                mView.downloadFinishNothing();
            }

            @Override
            public void onSuccess(@Nullable KnowledgeChannelHttpBean knowledgeChannelHttpBean, @NotNull String text) {
                LogUtilsKt.showLog("TAG", text);
                List<KnowledgeChannelHttpBean.KnowledgeChannelsMapBean> knowledgeChannelsMap = knowledgeChannelHttpBean.getKnowledgeChannelsMap();
                ArrayList<KnowledgeChannelItemBean> data = new ArrayList<>();
                for (KnowledgeChannelHttpBean.KnowledgeChannelsMapBean knowledgeChannelsMapBean : knowledgeChannelsMap) {
                    KnowledgeChannelItemBean bean = new KnowledgeChannelItemBean(KnowledgeChannelItem.TYPE);
                    bean.setKlid(id);
                    bean.setKcid(knowledgeChannelsMapBean.getId());
                    bean.setTitle(knowledgeChannelsMapBean.getName());
                    bean.setText(knowledgeChannelsMapBean.getArticleCount() + " 知识卡片");
                    data.add(bean);
                }
                mView.downloadFinish(0, false, data);
            }

            @Override
            public void onFailure(@NotNull String text) {
                if (TextUtils.equals(text, "列表无内容显示")) {
                    mView.downloadFinish(0, false, null);
                }
            }
        });

    }

    @Override
    public void createCard(String klid, String kcid, String articleUrl) {
        mView.createCardSuccess();
    }
}
