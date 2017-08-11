package com.tuzhi.application.bean;

/**
 * Created by wangpeng on 2017/8/1.
 */

public class EventBusBean {
    //用于判断那个消息传递到哪个类的唯一标示
    private String name;
    //用户分辨一个类中不同事件
    private int eventType;
    //字符串内容传递
    private String sContent;
    //数字内容传递
    private int iContent;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsContent() {
        return sContent;
    }

    public void setsContent(String sContent) {
        this.sContent = sContent;
    }

    public int getiContent() {
        return iContent;
    }

    public void setiContent(int iContent) {
        this.iContent = iContent;
    }
}
