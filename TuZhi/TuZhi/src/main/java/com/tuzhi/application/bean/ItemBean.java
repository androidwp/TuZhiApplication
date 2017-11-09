package com.tuzhi.application.bean;

/**
 * Created by wangpeng on 2017/11/9.
 */

public class ItemBean extends BaseListItemBean {
    private String id;
    private String title;
    private String position;
    private String text;

    public ItemBean(String itemType) {
        super(itemType);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
