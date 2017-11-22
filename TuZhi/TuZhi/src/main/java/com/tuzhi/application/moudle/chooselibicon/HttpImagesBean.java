package com.tuzhi.application.moudle.chooselibicon;

import java.util.List;

/**
 * Created by wangpeng on 2017/11/21.
 */

public class HttpImagesBean {

    /**
     * resultMsg : 正常
     * resultCode : 0
     * imagelist : ["http://192.168.0.140:8083/libImage/image1.png"]
     */

    private String resultMsg;
    private String resultCode;
    private List<String> imagelist;

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public List<String> getImagelist() {
        return imagelist;
    }

    public void setImagelist(List<String> imagelist) {
        this.imagelist = imagelist;
    }
}
