package com.tuzhi.application.moudle.enterpriseknowledge.mvp;

import android.text.TextUtils;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.enterpriseknowledge.bean.EnterpriseKnowledgeListItemBean;
import com.tuzhi.application.moudle.enterpriseknowledge.bean.HttpKnowledgeModuleBean;
import com.tuzhi.application.moudle.enterpriseknowledge.item.EnterpriseKnowledgeListItem;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;
import com.tuzhi.application.utils.LogUtilsKt;
import com.tuzhi.application.utils.SharedPreferencesUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class EnterpriseKnowledgePresenter extends BasePresenterImpl<EnterpriseKnowledgeContract.View> implements EnterpriseKnowledgeContract.Presenter {

    private final String URL = "tzkm/articleList";

    @Override
    public void downLoadData(String id, int page) {

        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("klId", id);
        parameter.put("pageNo", page + "");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, HttpKnowledgeModuleBean.class, new HttpCallBack<HttpKnowledgeModuleBean>() {
            @Override
            public void onFinish() {
                mView.downloadFinishNothing();
            }

            @Override
            public void onSuccess(@Nullable HttpKnowledgeModuleBean httpKnowledgeModuleBean, @NotNull String text) {
                LogUtilsKt.showLog("TAG", text);
                ArrayList<EnterpriseKnowledgeListItemBean> data = new ArrayList<>();
                HttpKnowledgeModuleBean.ArticlePageBean articlePage = httpKnowledgeModuleBean.getArticlePage();
                boolean next = articlePage.isNext();
                int index = articlePage.getIndex();
                List<HttpKnowledgeModuleBean.ArticlePageBean.ResultBean> result = articlePage.getResult();
                for (HttpKnowledgeModuleBean.ArticlePageBean.ResultBean bean : result) {
                    data.add(new EnterpriseKnowledgeListItemBean(EnterpriseKnowledgeListItem.TYPE, bean.getId(), bean.getTitle(), bean.getSummary() == null ? "" : bean.getSummary(), bean.getFileNum(), bean.getCommentNum(), bean.getUpdateTime() + " 更新"));
                }
                SharedPreferencesUtilsKt.saveLongCache(mView.getContext(), ConstantKt.getFLAG_DELETE_MOUDLE(), httpKnowledgeModuleBean.isDelArticle() ? ConstantKt.getValue_true() : ConstantKt.getValue_false());
                mView.downloadFinish(index, next, data);
            }

            @Override
            public void onFailure(@NotNull String text) {
                if (TextUtils.equals(text, "列表无内容显示")) {
                    mView.downloadFinish(0, false, null);
                }
            }
        });

    }
}
