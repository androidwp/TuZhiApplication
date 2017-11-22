package com.tuzhi.application.moudle.completedtasks;

import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
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

public class CompletedTasksPresenter extends BasePresenterImpl<CompletedTasksContract.View> implements CompletedTasksContract.Presenter {

    private static final String URL = "tzkm/knowledgeTaskList";

    private static final String URL_OPERATE = "tzkm/knowledgeTaskOperate";

    @Override
    public void downloadData(String id, String type, final int page) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("data", type);
        parameter.put("klId", id);
        parameter.put("status", "1");
        parameter.put("pageNo", page + "");
        HttpUtilsKt.get(mView.getContext(), URL, parameter, HttpCompletedTaskBean.class, new HttpCallBack<HttpCompletedTaskBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable HttpCompletedTaskBean taskListBean, @NotNull String text) {
                HttpCompletedTaskBean.KnowledgeTaskMapListsBean knowledgeTaskMapLists = taskListBean.getKnowledgeTaskMapLists();
                List<HttpCompletedTaskBean.KnowledgeTaskMapListsBean.ResultBean> result = knowledgeTaskMapLists.getResult();
                ArrayList<MyTasksItemBean> data = new ArrayList<>();
                for (HttpCompletedTaskBean.KnowledgeTaskMapListsBean.ResultBean knowledgeTaskMapList : result) {
                    MyTasksItemBean bean = new MyTasksItemBean(MyTasksItem.TYPE);
                    bean.setId(knowledgeTaskMapList.getId());
                    bean.setlId(knowledgeTaskMapList.getLibId());
                    bean.setContent(knowledgeTaskMapList.getTitle());
                    bean.setCheckStatue(true);
                    data.add(bean);
                }
                mView.downloadFinish(data, knowledgeTaskMapLists.isNext(), knowledgeTaskMapLists.getIndex());
            }

            @Override
            public void onFailure(@NotNull String text) {
                mView.downloadError();
            }
        });
    }

    @Override
    public void taskFinish(String id) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("ktId", id);
        parameter.put("operate", "4");
        parameter.put("status", "0");
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
