package com.tuzhi.application.moudle.createtask;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tuzhi.application.BR;
import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.bean.Person;

import java.util.ArrayList;

import kale.adapter.CommonRcvAdapter;

/**
 * @author wangpeng
 * @date 2017/11/7
 */

public class NewTaskBean extends BaseObservable {
    private String taskId;
    private String taskTitle = "";
    private String taskSummary = "";
    private Person taskManager;
    private ArrayList<Person> taskPeople;
    private ArrayList<String> taskCards;
    private String taskLib;
    private String taskLibId;
    private String taskPeopleNumber;
    private ArrayList<ItemBean> data;
    private boolean isChecked;
    private CommonRcvAdapter<ItemBean> adapter;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void setData(ArrayList<ItemBean> data) {
        this.data = data;
    }

    public CommonRcvAdapter<ItemBean> getAdapter() {
        return adapter;
    }

    public void setAdapter(CommonRcvAdapter<ItemBean> adapter) {
        this.adapter = adapter;
    }

    public ArrayList<ItemBean> getData() {
        return data;
    }

    public void addData(ItemBean bean) {
        this.data.add(bean);
    }

    public void removeData(ItemBean bean) {
        this.data.remove(bean);
    }

    @Bindable
    public String getTaskPeopleNumber() {
        return taskPeopleNumber;
    }

    public void setTaskPeopleNumber(String taskPeopleNumber) {
        this.taskPeopleNumber = taskPeopleNumber;
        notifyPropertyChanged(BR.taskPeopleNumber);
    }

    public String getTaskLibId() {
        return taskLibId;
    }

    public void setTaskLibId(String taskLibId) {
        this.taskLibId = taskLibId;
    }

    @Bindable
    public Person getTaskManager() {
        return taskManager;
    }

    public void setTaskManager(Person taskManager) {
        this.taskManager = taskManager;
        notifyPropertyChanged(BR.taskManager);
    }

    @Bindable
    public ArrayList<Person> getTaskPeople() {
        return taskPeople;
    }

    public void setTaskPeople(ArrayList<Person> taskPeople) {
        this.taskPeople = taskPeople;
        if (taskPeople.size() > 5) {
            setTaskPeopleNumber("等" + taskPeople.size() + "人参与");
        } else {
            setTaskPeopleNumber("参与");
        }
        notifyPropertyChanged(BR.taskPeople);
    }

    @Bindable
    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
        notifyPropertyChanged(BR.taskTitle);
    }

    @Bindable
    public String getTaskSummary() {
        return taskSummary;
    }

    public void setTaskSummary(String taskSummary) {
        this.taskSummary = taskSummary;
        notifyPropertyChanged(BR.taskSummary);
    }

    public ArrayList<String> getTaskCards() {
        return taskCards;
    }

    public void setTaskCards(ArrayList<String> taskCards) {
        this.taskCards = taskCards;
    }

    @Bindable
    public String getTaskLib() {
        return taskLib;
    }

    public void setTaskLib(String taskLib) {
        this.taskLib = taskLib;
        notifyPropertyChanged(BR.taskLib);
    }
}
