package com.tuzhi.application.moudle.repository.mvp;

import android.text.TextUtils;

import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.chooseknowledgelib.ChooseKnowledgeLibItem;
import com.tuzhi.application.moudle.repository.bean.HttpKnowledgeLibBean;
import com.tuzhi.application.moudle.repository.item.CommonKnowledgeLibItem;
import com.tuzhi.application.moudle.repository.item.KnowledgeLibTitleItem;
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
 *
 * @author wangpeng
 */

public class RepositoryPresenter extends BasePresenterImpl<RepositoryContract.View> implements RepositoryContract.Presenter {

    private final String URL = "app/index";

    @Override
    public void downLoadData(final int page) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "1");
        parameter.put("type", "1");
        parameter.put("pageNo", page + "");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, HttpKnowledgeLibBean.class, new HttpCallBack<HttpKnowledgeLibBean>() {
            @Override
            public void onFinish() {
                mView.downloadFinish();
            }

            @Override
            public void onSuccess(@Nullable HttpKnowledgeLibBean knowledgeLibBean, @NotNull String text) {
                ArrayList<ItemBean> data = new ArrayList<>();
                List<HttpKnowledgeLibBean.PublicListMapsBean> collectionKnowledgeLibsMap = knowledgeLibBean.getCollectionKnowledgeLibsMap();
                //设置常用数据库
                if (collectionKnowledgeLibsMap != null && collectionKnowledgeLibsMap.size() > 0) {
                    ItemBean itemTitle = new ItemBean(KnowledgeLibTitleItem.TYPE);
                    itemTitle.setTitle("常用知识库");
                    itemTitle.setViewSpace(0);
                    data.add(itemTitle);
                    ItemBean collectionItem = new ItemBean(CommonKnowledgeLibItem.TYPE);
                    ArrayList<ItemBean> collectionBeans = new ArrayList<>();
                    for (HttpKnowledgeLibBean.PublicListMapsBean publicListMapsBean : collectionKnowledgeLibsMap) {
                        ItemBean bean = new ItemBean(ChooseKnowledgeLibItem.TYPE);
                        bean.setId(publicListMapsBean.getId());
                        bean.setTitle(publicListMapsBean.getName());
                        bean.setText(publicListMapsBean.getContentCount() + "  频道");
                        bean.setImage(publicListMapsBean.getCoverImage());
                        bean.setFlag(publicListMapsBean.isIsOpen());
                        collectionBeans.add(bean);
                    }
                    collectionItem.setItemBeans(collectionBeans);
                }

                //设置公共知识库
                List<HttpKnowledgeLibBean.PublicListMapsBean> publicListMaps = knowledgeLibBean.getPublicListMaps();
                if (publicListMaps != null && publicListMaps.size() > 0) {
                    ItemBean titlePublic = new ItemBean(KnowledgeLibTitleItem.TYPE);
                    titlePublic.setTitle("公共知识库");
                    if (collectionKnowledgeLibsMap != null && collectionKnowledgeLibsMap.size() > 0) {
                        titlePublic.setViewSpace(1);
                    } else {
                        titlePublic.setViewSpace(-1);
                    }
                    data.add(titlePublic);

                    for (HttpKnowledgeLibBean.PublicListMapsBean publicListMap : publicListMaps) {
                        ItemBean bean = new ItemBean(RepositoryListItem.TYPE);
                        bean.setId(publicListMap.getId());
                        bean.setTitle(publicListMap.getName());
                        bean.setFlag(publicListMap.isIsOpen());
                        bean.setText(publicListMap.getContentCount() + "  频道");
                        bean.setImage(publicListMap.getCoverImage());
                        data.add(bean);
                    }
                }

                //设置部门知识库
                List<HttpKnowledgeLibBean.PublicListMapsBean> departmentListMaps = knowledgeLibBean.getDepartmentListMaps();
                if (departmentListMaps != null && departmentListMaps.size() > 0) {
                    ItemBean titlePublic = new ItemBean(KnowledgeLibTitleItem.TYPE);
                    titlePublic.setTitle("部门知识库");
                    titlePublic.setViewSpace(-1);
                    data.add(titlePublic);

                    for (HttpKnowledgeLibBean.PublicListMapsBean publicListMap : departmentListMaps) {
                        ItemBean bean = new ItemBean(RepositoryListItem.TYPE);
                        bean.setId(publicListMap.getId());
                        bean.setTitle(publicListMap.getName());
                        bean.setFlag(publicListMap.isIsOpen());
                        bean.setText(publicListMap.getContentCount() + "  频道");
                        bean.setImage(publicListMap.getCoverImage());
                        data.add(bean);
                    }
                }

                //设置项目知识库
                List<HttpKnowledgeLibBean.PublicListMapsBean> projectListMaps = knowledgeLibBean.getProjectListMaps();
                if (projectListMaps != null && projectListMaps.size() > 0) {
                    ItemBean titlePublic = new ItemBean(KnowledgeLibTitleItem.TYPE);
                    titlePublic.setTitle("项目知识库");
                    titlePublic.setViewSpace(-1);
                    data.add(titlePublic);

                    for (HttpKnowledgeLibBean.PublicListMapsBean publicListMap : projectListMaps) {
                        ItemBean bean = new ItemBean(RepositoryListItem.TYPE);
                        bean.setId(publicListMap.getId());
                        bean.setFlag(publicListMap.isIsOpen());
                        bean.setTitle(publicListMap.getName());
                        bean.setText(publicListMap.getContentCount() + "  频道");
                        bean.setImage(publicListMap.getCoverImage());
                        data.add(bean);
                    }
                }

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
