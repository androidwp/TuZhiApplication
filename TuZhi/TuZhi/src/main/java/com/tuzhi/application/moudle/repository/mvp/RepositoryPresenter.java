package com.tuzhi.application.moudle.repository.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.repository.bean.HttpRepositoryListBean;
import com.tuzhi.application.moudle.repository.bean.RepositoryListItemBean;
import com.tuzhi.application.moudle.repository.item.RepositoryListItem;
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

            }

            @Override
            public void onSuccess(@Nullable HttpRepositoryListBean httpRepositoryListBean, @NotNull String text) {
                ArrayList<RepositoryListItemBean> data = new ArrayList<>();
                List<HttpRepositoryListBean.KnowledgeLibsMapBean> knowledgeLibsMap = httpRepositoryListBean.getKnowledgeLibsMap();
                for (HttpRepositoryListBean.KnowledgeLibsMapBean knowledgeLibsMapBean : knowledgeLibsMap) {
                    RepositoryListItemBean bean = new RepositoryListItemBean(RepositoryListItem.TYPE);
                    bean.setId(knowledgeLibsMapBean.getId());
                    bean.setTitle(knowledgeLibsMapBean.getName());
                    bean.setText(knowledgeLibsMapBean.getContentCount() + "  频道");
                    data.add(bean);
                }
                mView.downLoadFinish(data, false, 0);
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });

    }
}
