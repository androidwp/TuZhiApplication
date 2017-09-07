package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.commentlist.bean;

import com.tuzhi.application.bean.BaseListItemBean;

/**
 * Created by wangpeng on 2017/6/14.
 */

public class CommentListBean extends BaseListItemBean {
    private String imageUrl;
    private String author;
    private String time;
    private String info;
    private String commentNumber;

    public String getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(String commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public CommentListBean(String itemType) {
        super(itemType);
    }


}
