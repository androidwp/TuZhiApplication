package com.tuzhi.application.moudle.search.searchpage.bean;

import com.tuzhi.application.bean.BaseListItemBean;

import java.util.ArrayList;

/**
 * Created by wangpeng on 2017/8/2.
 */

public class SearchResultListBean extends BaseListItemBean {
    private String id;
    private String portrait;
    private String nickName;
    //用于判断是笔记还是文件的item,0是文件1是笔记
    private int resultType;
    private String fileSuffix;
    private String aid;
    private String cid;
    private String content;
    private String articleUrl;
    private String articleTitle;
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

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

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

    public boolean isDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(boolean downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public ArrayList<String> getPreviewUrls() {
        return previewUrls;
    }

    public void setPreviewUrls(ArrayList<String> previewUrls) {
        this.previewUrls = previewUrls;
    }

    public String getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(String praiseNumber) {
        this.praiseNumber = praiseNumber;
    }

    public String getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(String commentNumber) {
        this.commentNumber = commentNumber;
    }

    public boolean isPraiseStatus() {
        return praiseStatus;
    }

    public void setPraiseStatus(boolean praiseStatus) {
        this.praiseStatus = praiseStatus;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public SearchResultListBean(String itemType) {
        super(itemType);
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getResultType() {
        return resultType;
    }

    public void setResultType(int resultType) {
        this.resultType = resultType;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
