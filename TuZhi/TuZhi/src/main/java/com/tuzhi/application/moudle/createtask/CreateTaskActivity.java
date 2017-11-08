package com.tuzhi.application.moudle.createtask;


import android.content.Intent;
import android.databinding.ViewDataBinding;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.bean.Person;
import com.tuzhi.application.databinding.ActivityCreateTaskBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.bean.ChooseColleagueItemBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.mvp.ChooseColleagueActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CreateTaskActivity extends MVPBaseActivity<CreateTaskContract.View, CreateTaskPresenter> implements CreateTaskContract.View {

    public static final String EVENT_NAME = "CreateTaskActivity";
    public static final int EVENT_TYPE_MANAGER = 0;
    public static final int EVENT_TYPE_PEOPLE = 1;
    public static final String TYPE = "type";
    public static final String TYPE_LIB = "TYPE_LIB";
    public static final String TYPE_SEARCH = "TYPE_SEARCH";
    public static final String LIB_ID = "LIB_ID";
    public static final String LIB_NAME = "LIB_NAME";
    private NewTaskBean bean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_task;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        ActivityCreateTaskBinding binding = (ActivityCreateTaskBinding) viewDataBinding;
        bean = new NewTaskBean();
        if (getIntent().getStringExtra(TYPE).equals(TYPE_LIB)) {
            bean.setTaskLib(getIntent().getStringExtra(LIB_NAME));
            bean.setTaskLibId(getIntent().getStringExtra(LIB_ID));
        }
        binding.setActivity(this);
        binding.setData(bean);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(EventBusBean busBean) {
        if (busBean.getName().equals(EVENT_NAME)) {
            if (busBean.getEventType() == EVENT_TYPE_MANAGER) {
                dealTaskManager(busBean);
            } else if (busBean.getEventType() == EVENT_TYPE_PEOPLE) {
                dealTaskPeople(busBean);
            }
        }
    }

    private void dealTaskPeople(EventBusBean busBean) {
        ArrayList<ChooseColleagueItemBean> chooseData = (ArrayList<ChooseColleagueItemBean>) busBean.getObject();
        ArrayList<Person> persons = new ArrayList<>();
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

    }

    public void chooseManager() {
        ActivitySkipUtilsKt.toActivity(this, ChooseColleagueActivity.class, ChooseColleagueActivity.TYPE, ChooseColleagueActivity.TYPE_TASK_MANAGER);
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
        startActivity(intent);
    }

}
