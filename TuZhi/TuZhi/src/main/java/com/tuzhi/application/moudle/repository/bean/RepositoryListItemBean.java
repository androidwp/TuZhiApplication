package com.tuzhi.application.moudle.repository.bean;

import com.tuzhi.application.bean.BaseListItemBean;

/**
 * Created by wangpeng on 2017/6/21.
 */

public class RepositoryListItemBean extends BaseListItemBean {
    private String id;
    private String title;
    private String text;

    public RepositoryListItemBean(String itemType) {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
