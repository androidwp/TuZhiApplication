package com.tuzhi.application.moudle.knowledgelibtask;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.mytasks.CompletedTaskItem;
import com.tuzhi.application.moudle.mytasks.MyTasksItem;
import com.tuzhi.application.moudle.mytasks.MyTasksItemBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class KnowledgeLibTaskPresenter extends BasePresenterImpl<KnowledgeLibTaskContract.View> implements KnowledgeLibTaskContract.Presenter{

    @Override
    public void downloadData(int page) {
        ArrayList<MyTasksItemBean> data = new ArrayList<>();
        MyTasksItemBean barBean = new MyTasksItemBean(KnowledgeLibTaskBarItem.TYPE);
        data.add(barBean);
        for (int i = 0; i < 10; i++) {
            MyTasksItemBean bean = new MyTasksItemBean(MyTasksItem.TYPE);
            bean.setContent("对12387号客户电话回访，确认其反馈的问题被解对12387号客户电话回访，确认其反馈的问题被解");
            data.add(bean);
        }
        MyTasksItemBean bean = new MyTasksItemBean(CompletedTaskItem.TYPE);
        data.add(bean);
        mView.downloadFinish(data, false, page);
    }
}
