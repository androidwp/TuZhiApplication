package com.tuzhi.application.moudle.memberlist;

import java.util.List;

/**
 *
 * @author wangpeng
 * @date 2017/11/22
 */

public class HttpMemberListBean {

    /**
     * resultMsg : 正常
     * resultCode : 0
     * staffMapList : [{"id":0,"knowledgeRoleId":0,"staffId":30,"knowledgeRoleStr":"所有者","nickname":"所有者","userImage":"http://upload.guigutang.cn:8082/tuzhikmMobile/20170823/100115481536.jpg"}]
     */

    private String resultMsg;
    private String resultCode;
    private List<StaffMapListBean> staffMapList;

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

    public List<StaffMapListBean> getStaffMapList() {
        return staffMapList;
    }

    public void setStaffMapList(List<StaffMapListBean> staffMapList) {
        this.staffMapList = staffMapList;
    }

    public static class StaffMapListBean {
        /**
         * id : 0
         * knowledgeRoleId : 0
         * staffId : 30
         * knowledgeRoleStr : 所有者
         * nickname : 所有者
         * userImage : http://upload.guigutang.cn:8082/tuzhikmMobile/20170823/100115481536.jpg
         */
        private String id;
        private int knowledgeRoleId;
        private String staffId;
        private String knowledgeRoleStr;
        private String nickname;
        private String userImage;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getKnowledgeRoleId() {
            return knowledgeRoleId;
        }

        public void setKnowledgeRoleId(int knowledgeRoleId) {
            this.knowledgeRoleId = knowledgeRoleId;
        }

        public String getStaffId() {
            return staffId;
        }

        public void setStaffId(String staffId) {
            this.staffId = staffId;
        }

        public String getKnowledgeRoleStr() {
            return knowledgeRoleStr;
        }

        public void setKnowledgeRoleStr(String knowledgeRoleStr) {
            this.knowledgeRoleStr = knowledgeRoleStr;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUserImage() {
            return userImage;
        }

        public void setUserImage(String userImage) {
            this.userImage = userImage;
        }
    }
}
