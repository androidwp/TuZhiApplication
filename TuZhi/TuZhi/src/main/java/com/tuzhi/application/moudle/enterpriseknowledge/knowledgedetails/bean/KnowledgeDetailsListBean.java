package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.bean;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.tuzhi.application.bean.BaseListItemBean;

import java.util.ArrayList;

/**
 * Created by wangpeng on 2017/6/9.
 */

public class KnowledgeDetailsListBean extends BaseListItemBean {
    private String aid;
    private String cid;
    private String content;
    private String articleUrl;
    private String imageUrl;
    private String fileType;
    private String title;
    private String time;
    private String author;
    private String fileSize;
    private String info;
    private String viewContentUrl;
    private String editContentUrl;
    //文件id
    private String fileId;
    //文件是否可以打开
    private boolean fileStatus;
    //文件是否已经下载
    private boolean downloadStatus;
    private ArrayList<String> previewUrls;
    private String praiseNumber;
    private String commentNumber;
    private boolean praiseStatus;
    private String fileName;

    public String getViewContentUrl() {
        return viewContentUrl;
    }

    public void setViewContentUrl(String viewContentUrl) {
        this.viewContentUrl = viewContentUrl;
    }

    public String getEditContentUrl() {
        return editContentUrl;
    }

    public void setEditContentUrl(String editContentUrl) {
        this.editContentUrl = editContentUrl;
    }

    public ArrayList<String> getPreviewUrls() {
        return previewUrls;
    }

    public void setPreviewUrls(ArrayList<String> previewUrls) {
        this.previewUrls = previewUrls;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public boolean isFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(boolean fileStatus) {
        this.fileStatus = fileStatus;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Bindable
    public boolean getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(boolean downloadStatus) {
        this.downloadStatus = downloadStatus;
        notifyPropertyChanged(BR.downloadStatus);
    }

    public KnowledgeDetailsListBean(String itemType) {
        super(itemType);
    }

    @Bindable
    public String getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(String praiseNumber) {
        this.praiseNumber = praiseNumber;
        notifyPropertyChanged(BR.praiseNumber);
    }

    public String getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(String commentNumber) {
        this.commentNumber = commentNumber;
    }

    @Bindable
    public boolean isPraiseStatus() {
        return praiseStatus;
    }

    public void setPraiseStatus(boolean praiseStatus) {
        this.praiseStatus = praiseStatus;
        notifyPropertyChanged(BR.praiseStatus);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private ArrayList<KnowledgeDetailsListBean> files;

    public ArrayList<KnowledgeDetailsListBean> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<KnowledgeDetailsListBean> files) {
        this.files = files;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }


}
