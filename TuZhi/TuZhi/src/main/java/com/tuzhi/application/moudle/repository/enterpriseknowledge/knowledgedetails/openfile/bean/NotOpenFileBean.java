package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.openfile.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tuzhi.application.BR;

/**
 * Created by wangpeng on 2017/6/26.
 */

public class NotOpenFileBean extends BaseObservable {
    private int fileIcon;
    private String fileId;
    private String fileName;
    private String fileUrl;
    private String fileSize;
    private String fileSffix;
    private String textDownLoad="下载到本地";
    private String textOpen="用其他应用打开";
    private boolean isDownload;

    public String getFileSffix() {
        return fileSffix;
    }

    public void setFileSffix(String fileSffix) {
        this.fileSffix = fileSffix;
    }

    public String getTextDownLoad() {
        return textDownLoad;
    }

    public void setTextDownLoad(String textDownLoad) {
        this.textDownLoad = textDownLoad;
    }

    public String getTextOpen() {
        return textOpen;
    }

    public void setTextOpen(String textOpen) {
        this.textOpen = textOpen;
    }

    public int getFileIcon() {
        return fileIcon;
    }

    public void setFileIcon(int fileIcon) {
        this.fileIcon = fileIcon;
    }

    @Bindable
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
        notifyPropertyChanged(BR.fileId);
    }

    @Bindable
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        notifyPropertyChanged(BR.fileName);
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    @Bindable
    public boolean isDownload() {
        return isDownload;
    }

    public void setDownload(boolean download) {
        isDownload = download;
        notifyPropertyChanged(BR.download);
    }
}
