package com.tuzhi.application.bean;

import android.databinding.Bindable;

import com.tuzhi.application.BR;
import com.tuzhi.application.moudle.createtask.NewTaskBean;

import java.util.ArrayList;

/**
 * @author wangpeng
 * @date 2017/11/9
 */

public class ItemBean extends BaseListItemBean {
    private String id;
    private String title;
    private String position;
    private String text;
    private String image;
    private String name;
    private String time;
    private String type;
    private ArrayList<ItemBean> itemBeans;
    private String[] jurisdiction = {"拥有者", "管理员", "成员"};
    private int index;
    private boolean flag;
    private NewTaskBean taskBean;
    private int viewSpace;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getViewSpace() {
        return viewSpace;
    }

    public void setViewSpace(int viewSpace) {
        this.viewSpace = viewSpace;
    }

    public ArrayList<ItemBean> getItemBeans() {
        return itemBeans;
    }

    public void setItemBeans(ArrayList<ItemBean> itemBeans) {
        this.itemBeans = itemBeans;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public NewTaskBean getTaskBean() {
        return taskBean;
    }

    public void setTaskBean(NewTaskBean taskBean) {
        this.taskBean = taskBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getJurisdiction() {
        return jurisdiction;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setPermissions(int index) {
        setText(jurisdiction[index]);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Bindable
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
        notifyPropertyChanged(BR.flag);
    }

    public ItemBean(String itemType) {
        super(itemType);
    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
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
