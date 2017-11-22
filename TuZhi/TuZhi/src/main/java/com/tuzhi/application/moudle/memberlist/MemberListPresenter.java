package com.tuzhi.application.moudle.memberlist;

import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.utils.HttpCallBack;
import com.tuzhi.application.utils.HttpUtilsKt;

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

    @Override
    public void downloadData(String type, String id) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
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
                for (HttpMemberListBean.StaffMapListBean resultBean : staffMapList) {
                    ItemBean itemBean = new ItemBean(MemberListItem.TYPE);
                    itemBean.setId(resultBean.getId());
                    itemBean.setName(resultBean.getNickname());
                    itemBean.setImage(resultBean.getUserImage());
                    itemBean.setIndex(resultBean.getKnowledgeRoleId());
                    arrayList.add(itemBean);
                }
                mView.downloadSuccess(arrayList);
            }

            @Override
            public void onFailure(String text) {

            }
        });
    }


}
