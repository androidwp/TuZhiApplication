package com.tuzhi.application.moudle.taskdetails;

import android.support.annotation.NonNull;

import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.bean.Person;
import com.tuzhi.application.moudle.basemvp.BasePresenterImpl;
import com.tuzhi.application.moudle.createtask.NewTaskBean;
import com.tuzhi.application.moudle.createtask.TaskCardItem;
import com.tuzhi.application.utils.EventBusUtils;
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

public class TaskDetailsPresenter extends BasePresenterImpl<TaskDetailsContract.View> implements TaskDetailsContract.Presenter {

    private static final String URL = "tzkm/knowledgeTaskOperate";

    private static final String URL_COMMENT = "tzkm/publishComment";

    @Override
    public void downloadData(String id, int page) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "1");
        parameter.put("ktId", id);
        parameter.put("pageNo", page + "");
        HttpUtilsKt.post(mView.getContext(), URL, parameter, HttpTaskDetailsBean.class, new HttpCallBack<HttpTaskDetailsBean>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable HttpTaskDetailsBean taskDetailsBean, @NotNull String text) {
                HttpTaskDetailsBean.CommentPageBean commentPage = taskDetailsBean.getCommentPage();
                int index = commentPage.getIndex();
                boolean next = commentPage.isNext();
                ArrayList<ItemBean> data = new ArrayList<>();
                if (index == 0) {
                    HttpTaskDetailsBean.KnowledgeTaskMapBean knowledgeTaskMap = taskDetailsBean.getKnowledgeTaskMap();
                    data.add(getTaskItemBean(knowledgeTaskMap));
                }
                List<HttpTaskDetailsBean.CommentBean> result = commentPage.getResult();
                for (HttpTaskDetailsBean.CommentBean commentBean : result) {
                    data.add(getCommentItemBean(commentBean));
                }
                mView.downloadSuccess(data, next, index);
            }


            @Override
            public void onFailure(@NotNull String text) {

            }
        });

    }

    /**
     * 封装评论数据
     *
     * @param commentBean
     * @return
     */
    @NonNull
    private ItemBean getCommentItemBean(HttpTaskDetailsBean.CommentBean commentBean) {
        ItemBean itemBean = new ItemBean(TaskDetailsCommentItem.TYPE);
        itemBean.setName(commentBean.getNickname());
        itemBean.setTime(commentBean.getTime());
        itemBean.setImage(commentBean.getUserImage());
        itemBean.setText(commentBean.getContent());
        return itemBean;
    }

    @Override
    public void commitComment(String tid, String comment, String ids) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("oId", tid);
        parameter.put("pId", "0");
        parameter.put("content", comment);
        parameter.put("oType", "2");
        parameter.put("staffIds", ids);
        HttpUtilsKt.post(mView.getContext(), URL_COMMENT, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                mView.commitCommentSuccess();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });

    }

    @Override
    public void taskFinished(String id) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("ktId", id);
        parameter.put("operate", "4");
        parameter.put("status", "1");
        HttpUtilsKt.post(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                EventBusUtils.taskStatueChange();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });

    }

    @Override
    public void taskUnfinished(String id) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("ktId", id);
        parameter.put("operate", "4");
        parameter.put("status", "0");
        HttpUtilsKt.post(mView.getContext(), URL, parameter, String.class, new HttpCallBack<String>() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(@Nullable String s, @NotNull String text) {
                EventBusUtils.taskStatueChange();
            }

            @Override
            public void onFailure(@NotNull String text) {

            }
        });
    }

    @Override
    public void updateTask(NewTaskBean bean) {
        WeakHashMap<String, String> parameter = HttpUtilsKt.getParameter(mView.getContext());
        parameter.put("operate", "3");
        parameter.put("ktId", bean.getTaskId());
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

    /**
     * 封装关联卡片
     *
     * @param relevanceCardList
     * @return
     */
    private ArrayList<ItemBean> getCards(List<HttpTaskDetailsBean.KnowledgeTaskMapBean.RelevanceCardListBean> relevanceCardList) {
        ArrayList<ItemBean> data = new ArrayList<>();
        for (HttpTaskDetailsBean.KnowledgeTaskMapBean.RelevanceCardListBean relevanceCardListBean : relevanceCardList) {
            ItemBean itemBeanCare = new ItemBean(TaskCardItem.TYPE);
            itemBeanCare.setId(relevanceCardListBean.getId());
            itemBeanCare.setTitle(relevanceCardListBean.getTitle());
            itemBeanCare.setTime(relevanceCardListBean.getUpdateTime());
            data.add(itemBeanCare);
        }
        return data;
    }

    /**
     * 封装负责人
     *
     * @param bean
     * @return
     */
    private Person getPerson(HttpTaskDetailsBean.KnowledgeTaskMapBean.ParticipantListBean bean) {
        Person person = new Person();
        person.setId(bean.getId());
        person.setName(bean.getNickname());
        person.setImage(bean.getUserImage());
        return person;
    }

    /**
     * 封装参与者
     *
     * @param participantList
     * @return
     */
    private ArrayList<Person> getPeople(List<HttpTaskDetailsBean.KnowledgeTaskMapBean.ParticipantListBean> participantList) {
        ArrayList<Person> data = new ArrayList<>();
        for (HttpTaskDetailsBean.KnowledgeTaskMapBean.ParticipantListBean participantListBean : participantList) {
            data.add(getPerson(participantListBean));
        }
        return data;
    }

    /**
     * 封装任务详情的数据
     *
     * @param knowledgeTaskMap
     * @return
     */

    @NonNull
    private ItemBean getTaskItemBean(HttpTaskDetailsBean.KnowledgeTaskMapBean knowledgeTaskMap) {
        ItemBean taskDetails = new ItemBean(TaskDetailsItem.TYPE);
        NewTaskBean taskBean = new NewTaskBean();
        //任务id
        taskBean.setTaskId(knowledgeTaskMap.getId());
        //任务状态
        taskBean.setChecked(knowledgeTaskMap.getTaskStatus());
        //任务标题
        taskBean.setTaskTitle(knowledgeTaskMap.getTitle());
        //任务描述
        taskBean.setTaskSummary(knowledgeTaskMap.getDescription());
        //知识库id
        taskBean.setTaskLibId(knowledgeTaskMap.getLibId());
        //添加管理员
        taskBean.setTaskManager(getPerson(knowledgeTaskMap.getResponsibleMap()));
        //添加参与者
        taskBean.setTaskPeople(getPeople(knowledgeTaskMap.getParticipantList()));
        //添加卡片
        taskBean.setData(getCards(knowledgeTaskMap.getRelevanceCardList()));
        taskDetails.setTaskBean(taskBean);
        return taskDetails;
    }

}
