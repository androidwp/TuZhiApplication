package com.tuzhi.application.moudle.mytasks;

import android.databinding.Bindable;

import com.tuzhi.application.BR;
import com.tuzhi.application.bean.BaseListItemBean;

/**
 * Created by wangpeng on 2017/10/30.
 */

public class MyTasksItemBean extends BaseListItemBean {
    private String id;
    private String content;
    //知识库进去任务部分使用
    private boolean flagAllTaskOrMyTask = true;
    private boolean checkStatue;

    @Bindable
    public boolean isFlagAllTaskOrMyTask() {
        return flagAllTaskOrMyTask;
    }

    public void setFlagAllTaskOrMyTask(boolean flagAllTaskOrMyTask) {
        this.flagAllTaskOrMyTask = flagAllTaskOrMyTask;
        notifyPropertyChanged(BR.flagAllTaskOrMyTask);
    }

    public MyTasksItemBean(String itemType) {
        super(itemType);
    }

    public boolean isCheckStatue() {
        return checkStatue;
    }

    public void setCheckStatue(boolean checkStatue) {
        this.checkStatue = checkStatue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
