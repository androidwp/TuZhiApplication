package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.bean;

import java.util.List;

/**
 * Created by wangpeng on 2017/11/21.
 */

public class HttpManagerBean {

    /**
     * resultCode : 0
     * resultMsg : 正常
     * staffMapList : [{"nickname":"zzz","id":41,"userImage":""},{"nickname":"zy","id":40,"userImage":""},{"nickname":"南","id":38,"userImage":""},{"nickname":"sss","id":37,"userImage":""},{"nickname":"員工2","id":32,"userImage":""},{"nickname":"所有者","id":30,"userImage":"http://upload.guigutang.cn:8082/tuzhikmMobile/20170823/100115481536.jpg"}]
     */

    private String resultCode;
    private String resultMsg;
    private List<StaffMapListBean> staffMapList;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public List<StaffMapListBean> getStaffMapList() {
        return staffMapList;
    }

    public void setStaffMapList(List<StaffMapListBean> staffMapList) {
        this.staffMapList = staffMapList;
    }

    public static class StaffMapListBean {
        /**
         * nickname : zzz
         * id : 41
         * userImage : 
         */

        private String nickname;
        private String id;
        private String userImage;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserImage() {
            return userImage;
        }

        public void setUserImage(String userImage) {
            this.userImage = userImage;
        }
    }
}
