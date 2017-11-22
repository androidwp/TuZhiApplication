package com.tuzhi.application.moudle.dynamiccollaboration;

import com.tuzhi.application.bean.BaseListItemBean;

/**
 *
 * @author wangpeng
 * @date 2017/10/26
 */

public class DynamicCollaborationItemBean extends BaseListItemBean {
    private String id;
    private String imageUrl;
    private String nickName;
    private String dynamic;
    private String time;
    private String title;
    private String position;

    public DynamicCollaborationItemBean(String itemType) {
        super(itemType);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDynamic() {
        return dynamic;
    }

    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
