package com.tuzhi.application.moudle.createtask;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.bean.HttpInitBean;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.bean.Person;
import com.tuzhi.application.databinding.ActivityCreateTaskBinding;
import com.tuzhi.application.inter.ItemClickListener;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.chooseknowledgelib.ChooseKnowledgeLibActivity;
import com.tuzhi.application.moudle.knowledgelibtask.KnowledgeLibTaskFragment;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.bean.ChooseColleagueItemBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.mvp.ChooseColleagueActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.utils.ToastUtilsKt;
import com.tuzhi.application.utils.UserInfoUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class CreateTaskActivity extends MVPBaseActivity<CreateTaskContract.View, CreateTaskPresenter> implements CreateTaskContract.View, ItemClickListener {
    public static final String EVENT_NAME = "CreateTaskActivity";
    public static final int EVENT_TYPE_MANAGER = 0;
    public static final int EVENT_TYPE_PEOPLE = 1;
    public static final int EVENT_TYPE_CARD = 2;
    public static final int EVENT_TYPE_LIB = 3;
    public static final String TYPE = "type";
    /**
     * 从库进入创建
     */
    public static final String TYPE_LIB = "TYPE_LIB";
    /**
     * 从库搜索进入创建
     */
    public static final String TYPE_SEARCH = "TYPE_SEARCH";
    /**
     * 知识库id
     */
    public static final String LIB_ID = "LIB_ID";
    /**
     * 知识库名字
     */
    public static final String LIB_NAME = "LIB_NAME";

    private NewTaskBean bean;

    private CommonRcvAdapter<ItemBean> adapter;

    private ArrayList<Person> persons = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_task;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        HttpInitBean userInfo = UserInfoUtils.getUserInfo(this);
        Person person = new Person();
        person.setId(userInfo.getUserId());
        person.setName(userInfo.getNickname());
        person.setImage(userInfo.getUserImage());
        ActivityCreateTaskBinding binding = (ActivityCreateTaskBinding) viewDataBinding;
        bean = new NewTaskBean();
        bean.setTaskManager(person);
        persons.add(person);
        bean.setTaskPeople(persons);
        bean.setData(new ArrayList<ItemBean>());
        if (getIntent().getStringExtra(TYPE).equals(TYPE_LIB)) {
            bean.setTaskLib(getIntent().getStringExtra(LIB_NAME));
            bean.setTaskLibId(getIntent().getStringExtra(LIB_ID));
            binding.iv.setVisibility(View.GONE);
        }
        binding.setActivity(this);
        binding.setData(bean);
        adapter = new CommonRcvAdapter<ItemBean>(bean.getData()) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                TaskCardItem taskCardItem = new TaskCardItem();
                taskCardItem.setClickListener(CreateTaskActivity.this);
                return taskCardItem;
            }
        };
        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(EventBusBean busBean) {
        if (busBean.getName().equals(EVENT_NAME)) {
            if (busBean.getEventType() == EVENT_TYPE_MANAGER) {
                dealTaskManager(busBean);
            } else if (busBean.getEventType() == EVENT_TYPE_PEOPLE) {
                dealTaskPeople(busBean);
            } else if (busBean.getEventType() == EVENT_TYPE_CARD) {
                dealTaskCard(busBean);
            } else if (busBean.getEventType() == EVENT_TYPE_LIB) {
                dealTaskLib(busBean);
            }
        }
    }

    private void dealTaskLib(EventBusBean busBean) {
        ItemBean itemBean = (ItemBean) busBean.getObject();
        bean.setTaskLibId(itemBean.getId());
        bean.setTaskLib(itemBean.getTitle());
    }

    private void dealTaskCard(EventBusBean busBean) {
        ItemBean itemBean = (ItemBean) busBean.getObject();
        bean.addData(itemBean);
        adapter.notifyDataSetChanged();
    }

    private void dealTaskPeople(EventBusBean busBean) {
        ArrayList<ChooseColleagueItemBean> chooseData = (ArrayList<ChooseColleagueItemBean>) busBean.getObject();
        persons.clear();
        for (ChooseColleagueItemBean chooseDatum : chooseData) {
            Person person = new Person();
            person.setId(chooseDatum.getId());
            person.setImage(chooseDatum.getPortrait());
            person.setName(chooseDatum.getNickName());
            persons.add(person);
        }
        bean.setTaskPeople(persons);
    }

    private void dealTaskManager(EventBusBean busBean) {
        ChooseColleagueItemBean colleagueItemBean = (ChooseColleagueItemBean) busBean.getObject();
        Person person = new Person();
        person.setId(colleagueItemBean.getId());
        person.setImage(colleagueItemBean.getPortrait());
        person.setName(colleagueItemBean.getNickName());
        bean.setTaskManager(person);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void back() {
        onBackPressed();
    }

    public void commit(NewTaskBean taskBean) {
        if (TextUtils.isEmpty(taskBean.getTaskTitle())) {
            ToastUtilsKt.toast(this, "请填写任务标题");
            return;
        } else if (TextUtils.isEmpty(taskBean.getTaskLibId())) {
            ToastUtilsKt.toast(this, "请选择保存到的库");
            return;
        }
        mPresenter.createTask(taskBean);
    }

    public void chooseLib() {
        if (!TextUtils.equals(getIntent().getStringExtra(TYPE), TYPE_LIB)) {
            ActivitySkipUtilsKt.toActivity(this, ChooseKnowledgeLibActivity.class, ChooseKnowledgeLibActivity.TYPE, ChooseKnowledgeLibActivity.TYPE_CHOOSE_LIB);
        }
    }

    public void chooseManager() {
        Intent intent = new Intent(this, ChooseColleagueActivity.class);
        intent.putExtra(ChooseColleagueActivity.TYPE, ChooseColleagueActivity.TYPE_TASK_MANAGER);
        intent.putExtra(ChooseColleagueActivity.EVENT_NAME, EVENT_NAME);
        intent.putExtra(ChooseColleagueActivity.LIB_ID, bean.getTaskLibId());
        startActivity(intent);
    }

    public void choosePeople() {
        Intent intent = new Intent(this, ChooseColleagueActivity.class);
        ArrayList<Person> taskPeople = bean.getTaskPeople();
        ArrayList<String> peopleId = new ArrayList<>();
        if (taskPeople != null) {
            for (Person taskPerson : taskPeople) {
                peopleId.add(taskPerson.getId());
            }
        }
        intent.putStringArrayListExtra(ChooseColleagueActivity.TASK_PEOPLE_ID, peopleId);
        intent.putExtra(ChooseColleagueActivity.TYPE, ChooseColleagueActivity.TYPE_TASK_PEOPLE);
        intent.putExtra(ChooseColleagueActivity.EVENT_NAME, EVENT_NAME);
        intent.putExtra(ChooseColleagueActivity.LIB_ID, bean.getTaskLibId());
        startActivity(intent);
    }

    public void chooseCard() {
        if (!TextUtils.isEmpty(bean.getTaskLibId())) {
            Intent intent = new Intent(this, ChooseColleagueActivity.class);
            intent.putExtra(ChooseColleagueActivity.TYPE, ChooseColleagueActivity.TYPE_CHOOSE_CHANNEL);
            intent.putExtra(ChooseColleagueActivity.LIB_ID, bean.getTaskLibId());
            intent.putExtra(ChooseColleagueActivity.EVENT_NAME, EVENT_NAME);
            startActivity(intent);
        } else {
            ToastUtilsKt.toast(this, "请先选择知识库");
        }

    }

    @Override
    public void onItemClick(View view) {
        switch (view.getId()) {
            case R.id.tvRemove:
                bean.getData().remove((int) view.getTag());
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    @Override
    public void createTaskSuccess() {
        EventBus.getDefault().post(KnowledgeLibTaskFragment.EVENT_REFRESH);
        ToastUtilsKt.toast(this, "创建成功");
        back();
    }
}
