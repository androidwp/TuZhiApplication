package com.tuzhi.application.moudle.memberlist;

import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
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

public class MemberListPresenter extends BasePresenterImpl<MemberListContract.View> implements MemberListContract.Presenter {

    private static final String URL = "tzkm/getKnowledgeLimitsStaffList";

    private static final String URL_SAVE = "tzkm/saveStaffKnowledgeLimits";

    @Override
    public void downloadData(String type, String id) {
        final WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("oType", type);
        parameter.put("oId", id);
        HttpUtilsKt.get(mView.getContext(), URL, parameter, HttpMemberListBean.class, new HttpCallBack<HttpMemberListBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(HttpMemberListBean memberListBean, String text) {
                List<HttpMemberListBean.StaffMapListBean> staffMapList = memberListBean.getStaffMapList();
                ArrayList<ItemBean> arrayList = new ArrayList<>();
                String userId = parameter.get("userId");
                for (HttpMemberListBean.StaffMapListBean resultBean : staffMapList) {
                    ItemBean itemBean = new ItemBean(MemberListItem.TYPE);
                    itemBean.setId(resultBean.getStaffId());
                    itemBean.setName(resultBean.getNickname());
                    itemBean.setImage(resultBean.getUserImage());
                    itemBean.setIndex(resultBean.getKnowledgeRoleId());
                    itemBean.setPermissions(itemBean.getIndex());
                    arrayList.add(itemBean);
                    if (itemBean.getId().equals(userId)) {
                        mView.setPermissions(itemBean.getIndex());
                    }
                }

                mView.downloadSuccess(arrayList);
            }

            @Override
            public void onFailure(String text) {

            }
        });
    }

    @Override
    public void commit(String type, String id, String data) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("oType", type);
        parameter.put("oId", id);
        parameter.put("data", data);
        HttpUtilsKt.post(mView.getContext(), URL_SAVE, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.commitSuccess();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }
}
