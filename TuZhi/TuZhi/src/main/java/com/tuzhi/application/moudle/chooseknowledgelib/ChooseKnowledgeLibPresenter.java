package com.tuzhi.application.moudle.chooseknowledgelib;

import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.repository.bean.HttpRepositoryListBean;
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

public class ChooseKnowledgeLibPresenter extends BasePresenterImpl<ChooseKnowledgeLibContract.View> implements ChooseKnowledgeLibContract.Presenter {

    private final String URL = "tzkm/knowledgeLib";

    @Override
    public void downLoadData() {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "1");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, HttpRepositoryListBean.class, new HttpCallBack<HttpRepositoryListBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable HttpRepositoryListBean httpRepositoryListBean, @NotNull String text) {
                ArrayList<ItemBean> data = new ArrayList<>();
                List<HttpRepositoryListBean.KnowledgeLibsMapBean> knowledgeLibsMap = httpRepositoryListBean.getKnowledgeLibsMap();
                for (HttpRepositoryListBean.KnowledgeLibsMapBean knowledgeLibsMapBean : knowledgeLibsMap) {
                    ItemBean bean = new ItemBean(ChooseKnowledgeLibItem.TYPE);
                    bean.setId(knowledgeLibsMapBean.getId());
                    bean.setTitle(knowledgeLibsMapBean.getName());
                    bean.setText(knowledgeLibsMapBean.getContentCount() + " 知识频道");
                    data.add(bean);
                }
                mView.downLoadFinish(data);
            }

            @Override
            public void onFailure(@NotNull String text) {
//                if (TextUtils.equals(text, "列表无内容显示")) {
//                    mView.downLoadFinish(null, false, 0);
//                }
            }
        });
    }
}
