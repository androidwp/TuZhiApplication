package com.tuzhi.application.moudle.completedtasks;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CompletedTasksPresenter extends BasePresenterImpl<CompletedTasksContract.View> implements CompletedTasksContract.Presenter {

    @Override
    public void downloadData(int page) {
        ArrayList<CompletedTasksItemBean> mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CompletedTasksItemBean com = new CompletedTasksItemBean(CompletedTasksItem.TYPE);
            com.setTitle("对12387号客户电话回访，确认其反馈的问题被解觉打发生地方是的");
            mData.add(com);
        }
        mView.downloadFinish(mData, true, page);
    }
}
