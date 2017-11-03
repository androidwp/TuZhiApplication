package com.tuzhi.application.moudle.completedtasks;

import com.tuzhi.application.bean.BaseListItemBean;

/**
 * Created by wangpeng on 2017/10/26.
 */

public class CompletedTasksItemBean extends BaseListItemBean{

    private String id;
    private String title;

    public CompletedTasksItemBean(String itemType) {
        super(itemType);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
