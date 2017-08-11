package com.tuzhi.application.moudle.repository.enterpriseknowledge.bean;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.tuzhi.application.bean.BaseListItemBean;

import java.util.List;

/**
 * Created by wangpeng on 2017/8/4.
 */

public class KnowledgeCardItemBean extends BaseListItemBean {
    private String id;
    private String title;
    private String portrait;
    private String nickName;
    private List<String> joinPortraits;
    private String text;
    private boolean praiseStatus;
    private String praiseNumber;
    private String fileNumber;
    private String commentNumber;

    public KnowledgeCardItemBean(String itemType) {
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

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<String> getJoinPortraits() {
        return joinPortraits;
    }

    public void setJoinPortraits(List<String> joinPortraits) {
        this.joinPortraits = joinPortraits;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Bindable
    public boolean isPraiseStatus() {
        return praiseStatus;
    }

    public void setPraiseStatus(boolean praiseStatus) {
        this.praiseStatus = praiseStatus;
        notifyPropertyChanged(BR.praiseStatus);
    }

    @Bindable
    public String getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(String praiseNumber) {
        this.praiseNumber = praiseNumber;
        notifyPropertyChanged(BR.praiseNumber);
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(String commentNumber) {
        this.commentNumber = commentNumber;
    }
}
