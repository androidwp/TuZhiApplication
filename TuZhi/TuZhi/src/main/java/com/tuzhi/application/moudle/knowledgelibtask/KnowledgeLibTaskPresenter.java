package com.tuzhi.application.moudle.knowledgelibtask;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.mytasks.CompletedTaskItem;
import com.tuzhi.application.moudle.mytasks.MyTasksItem;
import com.tuzhi.application.moudle.mytasks.MyTasksItemBean;
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

public class KnowledgeLibTaskPresenter extends BasePresenterImpl<KnowledgeLibTaskContract.View> implements KnowledgeLibTaskContract.Presenter {

    private static final String URL = "tzkm/knowledgeTaskList";

    private static final String URL_OPERATE = "tzkm/knowledgeTaskOperate";


    @Override
    public void downloadData(String id, String type, int page) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("data", type);
        parameter.put("klId", id);
        parameter.put("status", "0");
        parameter.put("pageNo", "0");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, HttpTaskListBean.class, new HttpCallBack<HttpTaskListBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable HttpTaskListBean taskListBean, @NotNull String text) {
                List<HttpTaskListBean.KnowledgeTaskMapListsBean> knowledgeTaskMapLists = taskListBean.getKnowledgeTaskMapLists();
                ArrayList<MyTasksItemBean> data = new ArrayList<>();
                if (knowledgeTaskMapLists != null) {
                    for (HttpTaskListBean.KnowledgeTaskMapListsBean knowledgeTaskMapList : knowledgeTaskMapLists) {
                        MyTasksItemBean bean = new MyTasksItemBean(MyTasksItem.TYPE);
                        bean.setId(knowledgeTaskMapList.getId());
                        bean.setlId(knowledgeTaskMapList.getLibId());
                        bean.setContent(knowledgeTaskMapList.getTitle());
                        data.add(bean);
                    }
                }
                //已完成item
                MyTasksItemBean bean = new MyTasksItemBean(CompletedTaskItem.TYPE);
                data.add(bean);
                mView.downloadFinish(data, false, 0);
            }

            @Override
            public void onFailure(@NotNull String text) {
                ArrayList<MyTasksItemBean> data = new ArrayList<>();
                MyTasksItemBean bean = new MyTasksItemBean(CompletedTaskItem.TYPE);
                data.add(bean);
                mView.downloadFinish(data, false, 0);
            }
        });
    }

    @Override
    public void taskFinish(String id) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("ktId", id);
        parameter.put("operate", "4");
        parameter.put("status", "1");
        HttpUtilsKt.post(mView.getContext(), URL_OPERATE, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.taskFinishSuccess();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });

    }
}
