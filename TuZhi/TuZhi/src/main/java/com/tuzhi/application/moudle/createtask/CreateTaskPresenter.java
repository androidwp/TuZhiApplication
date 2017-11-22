package com.tuzhi.application.moudle.createtask;

import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.bean.Person;
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

public class CreateTaskPresenter extends BasePresenterImpl<CreateTaskContract.View> implements CreateTaskContract.Presenter {
    private static final String URL = "tzkm/knowledgeTaskOperate";

    @Override
    public void createTask(NewTaskBean bean) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "2");
        parameter.put("klId", bean.getTaskLibId());
        parameter.put("title", bean.getTaskTitle());
        parameter.put("description", bean.getTaskSummary());
        parameter.put("responsible", bean.getTaskManager().getId());
        parameter.put("participant", getParticipant(bean.getTaskPeople()));
        parameter.put("cards", getCards(bean.getData()));
        HttpUtilsKt.post(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.createTaskSuccess();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });

    }

    private String getParticipant(ArrayList<Person> data) {
        StringBuilder participants = new StringBuilder();
        if (data != null && data.size() > 0) {
            for (Person chooseDatum : data) {
                participants.append(",").append(chooseDatum.getId());
            }
            participants.deleteCharAt(0);
        }
        return participants.toString();
    }

    private String getCards(ArrayList<ItemBean> data) {
        StringBuilder cards = new StringBuilder();
        if (data != null && data.size() > 0) {
            for (ItemBean chooseDatum : data) {
                cards.append(",").append(chooseDatum.getId());
            }
            cards.deleteCharAt(0);
        }
        return cards.toString();
    }
}
