package com.tuzhi.application.moudle.dynamiccollaboration;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class DynamicCollaborationPresenter extends BasePresenterImpl<DynamicCollaborationContract.View> implements DynamicCollaborationContract.Presenter {

    private static final String URL = "app/index";

    @Override
    public void downloadData(int page) {

        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("type", "2");
        parameter.put("pageNo", page + "");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, DynamicCollaborationHttpBean.class, new HttpCallBack<DynamicCollaborationHttpBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable DynamicCollaborationHttpBean dynamicCollaborationHttpBean, @NotNull String text) {
                DynamicCollaborationHttpBean.EditRecordPageBean editRecordPage = dynamicCollaborationHttpBean.getEditRecordPage();
                int index = editRecordPage.getIndex();
                boolean next = editRecordPage.isNext();
                ArrayList<DynamicCollaborationItemBean> mData = new ArrayList<>();
                for (DynamicCollaborationHttpBean.EditRecordPageBean.ResultBean resultBean : editRecordPage.getResult()) {
                    DynamicCollaborationItemBean dynamicCollaborationItemBean = new DynamicCollaborationItemBean(DynamicCollaborationItem.TYPE);
                    dynamicCollaborationItemBean.setId(resultBean.getArticleId());
                    dynamicCollaborationItemBean.setTitle(resultBean.getArticleTitle());
                    dynamicCollaborationItemBean.setNickName(resultBean.getAuthor());
                    dynamicCollaborationItemBean.setImageUrl(resultBean.getAuthorImg());
                    dynamicCollaborationItemBean.setDynamic(resultBean.getEditDesc());
                    dynamicCollaborationItemBean.setTime(resultBean.getUpdateTime());
                    dynamicCollaborationItemBean.setPosition(resultBean.getLibName() + ">" + resultBean.getChannelName());
                    mData.add(dynamicCollaborationItemBean);
                }
                mView.downloadFinish(mData, next, index);
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }
}
