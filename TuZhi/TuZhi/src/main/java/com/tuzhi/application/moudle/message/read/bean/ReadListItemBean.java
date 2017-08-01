package com.tuzhi.application.moudle.message.read.bean;

import com.tuzhi.application.bean.BaseListItemBean;

/**
 * Created by wangpeng on 2017/8/1.
 */

public class ReadListItemBean extends BaseListItemBean{
    private String id;
    private String portrait;
    private String nickName;
    //0是赞，1是评论
    private int commentType;
    private String time;
    private String Content;
    private String commentContent;
    private String commentTypeContent;

    public ReadListItemBean(String itemType) {
        super(itemType);
    }

    public String getCommentTypeContent() {
        return commentTypeContent;
    }

    public void setCommentTypeContent(String commentTypeContent) {
        this.commentTypeContent = commentTypeContent;
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

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
        setCommentTypeContent(commentType == 0 ? "赞了你的:" : "评论了你的:");
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
