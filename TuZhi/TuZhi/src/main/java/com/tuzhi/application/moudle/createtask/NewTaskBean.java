package com.tuzhi.application.moudle.createtask;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tuzhi.application.BR;
import com.tuzhi.application.bean.Person;

import java.util.ArrayList;

/**
 * Created by wangpeng on 2017/11/7.
 */

public class NewTaskBean extends BaseObservable {
    private String taskTitle;
    private String taskSummary;
    private Person taskManager;
    private ArrayList<Person> taskPeople;
    private ArrayList<String> taskCards;
    private String taskLib;
    private String taskLibId;
    private String taskPeopleNumber;

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
        if (taskPeople.size() > 5)
            setTaskPeopleNumber("等" + taskPeople.size() + "人参与");
        else
            setTaskPeopleNumber("");
        notifyPropertyChanged(BR.taskPeople);
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskSummary() {
        return taskSummary;
    }

    public void setTaskSummary(String taskSummary) {
        this.taskSummary = taskSummary;
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
