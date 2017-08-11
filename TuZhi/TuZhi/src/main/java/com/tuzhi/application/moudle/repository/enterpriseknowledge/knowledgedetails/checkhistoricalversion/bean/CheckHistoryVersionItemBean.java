package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.checkhistoricalversion.bean;

import com.tuzhi.application.bean.BaseListItemBean;

/**
 * Created by wangpeng on 2017/8/10.
 */

public class CheckHistoryVersionItemBean extends BaseListItemBean {
    private String id;
    private String aid;
    private String portrait;
    private String nickName;
    private String info;

    public CheckHistoryVersionItemBean(String itemType) {
        super(itemType);
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPortrait() {
        return portrait;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
