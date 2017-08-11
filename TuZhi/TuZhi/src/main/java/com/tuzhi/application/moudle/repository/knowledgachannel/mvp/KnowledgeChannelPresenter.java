package com.tuzhi.application.moudle.repository.knowledgachannel.mvp;

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

public class KnowledgeChannelPresenter extends BasePresenterImpl<KnowledgeChannelContract.View> implements KnowledgeChannelContract.Presenter {
    private final String URL_LIB = "tzkm/knowledgeLib";
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
//                KnowledgeChannelHttpBean.ArticlePageBean articlePage = knowledgeChannelHttpBean.getArticlePage();
//                boolean next = articlePage.isNext();
//                int index = articlePage.getIndex();
//                List<KnowledgeChannelHttpBean.ArticlePageBean.ResultBean> result = articlePage.getResult();
//                ArrayList<KnowledgeChannelItemBean> data = new ArrayList<>();
//                for (KnowledgeChannelHttpBean.ArticlePageBean.ResultBean bean : result) {
//                    KnowledgeChannelItemBean itemBean = new KnowledgeChannelItemBean(KnowledgeChannelItem.TYPE);
//                    itemBean.setId(bean.getId());
//                    itemBean.setTitle(bean.getTitle());
//                    itemBean.setText(bean.getSummary());
//                    data.add(itemBean);
//                }
//                SharedPreferencesUtilsKt.saveLongCache(mView.getContext(), ConstantKt.getFLAG_DELETE_MOUDLE(), knowledgeChannelHttpBean.isDelArticle() ? ConstantKt.getValue_true() : ConstantKt.getValue_false());
//                mView.downloadFinish(index, next, data);
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
    public void deleteLib(String id) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "4");
        parameter.put("klId", id);
        HttpUtilsKt.get(mView.getContext(), URL_LIB, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.deleteLibSuccess();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }

    @Override
    public void rename(String id, final String name) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "3");
        parameter.put("klId", id);
        parameter.put("name", name);
        HttpUtilsKt.get(mView.getContext(), URL_LIB, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.renameSuccess(name);
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }
}
