package com.tuzhi.application.moudle.repository.mvp;

import android.text.TextUtils;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.repository.bean.HttpRepositoryListBean;
import com.tuzhi.application.moudle.repository.bean.RepositoryListItemBean;
import com.tuzhi.application.moudle.repository.item.RepositoryListItem;
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

public class RepositoryPresenter extends BasePresenterImpl<RepositoryContract.View> implements RepositoryContract.Presenter {

    private final String URL = "tzkm/knowledgeLib";

    @Override
    public void downLoadData(final int page) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "1");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, HttpRepositoryListBean.class, new HttpCallBack<HttpRepositoryListBean>() {
            @Override
            public void onFinish() {
                mView.downloadFinish();
            }

            @Override
            public void onSuccess(@Nullable HttpRepositoryListBean httpRepositoryListBean, @NotNull String text) {
                LogUtilsKt.showLog("TAG", text);
                ArrayList<RepositoryListItemBean> data = new ArrayList<>();
                List<HttpRepositoryListBean.KnowledgeLibsMapBean> knowledgeLibsMap = httpRepositoryListBean.getKnowledgeLibsMap();
                for (HttpRepositoryListBean.KnowledgeLibsMapBean knowledgeLibsMapBean : knowledgeLibsMap) {
                    RepositoryListItemBean bean = new RepositoryListItemBean(RepositoryListItem.TYPE);
                    bean.setId(knowledgeLibsMapBean.getId());
                    bean.setTitle(knowledgeLibsMapBean.getName());
                    bean.setText(knowledgeLibsMapBean.getContentCount() + "  频道");
                    data.add(bean);
                }
                SharedPreferencesUtilsKt.saveLongCache(mView.getContext(), ConstantKt.getFLAG_DELETE_LIB(), httpRepositoryListBean.isDelKnowledgeLib() ? ConstantKt.getValue_true() : ConstantKt.getValue_false());
                mView.downLoadFinish(data, false, 0);
            }

            @Override
            public void onFailure(@NotNull String text) {
                if (TextUtils.equals(text, "列表无内容显示")) {
                    mView.downLoadFinish(null, false, 0);
                }
            }
        });
    }
}
