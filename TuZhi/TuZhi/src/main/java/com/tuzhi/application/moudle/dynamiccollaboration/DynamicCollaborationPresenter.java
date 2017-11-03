package com.tuzhi.application.moudle.dynamiccollaboration;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DynamicCollaborationPresenter extends BasePresenterImpl<DynamicCollaborationContract.View> implements DynamicCollaborationContract.Presenter {

    @Override
    public void downloadData(int page) {
        ArrayList<DynamicCollaborationItemBean> mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DynamicCollaborationItemBean dynamicCollaborationItemBean = new DynamicCollaborationItemBean(DynamicCollaborationItem.TYPE);
            dynamicCollaborationItemBean.setTitle("A产品V2.1需求文档");
            dynamicCollaborationItemBean.setNickName("马得勇");
            dynamicCollaborationItemBean.setDynamic("创建了知识卡片");
            dynamicCollaborationItemBean.setTime("03-13  23:45");
            dynamicCollaborationItemBean.setPosition("产品部 > 需求文档");
            mData.add(dynamicCollaborationItemBean);
        }
        mView.downloadFinish(mData, true, page);
    }
}
