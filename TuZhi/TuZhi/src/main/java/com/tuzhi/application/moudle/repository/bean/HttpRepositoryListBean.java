package com.tuzhi.application.moudle.repository.bean;

import java.util.List;

/**
 * @author wangpeng
 * @date 2017/6/21
 */

public class HttpRepositoryListBean {

    private String resultMsg;
    private String resultCode;
    private boolean isDelKnowledgeLib;
    private List<KnowledgeLibsMapBean> knowledgeLibsMap;

    public boolean isDelKnowledgeLib() {
        return isDelKnowledgeLib;
    }

    public void setDelKnowledgeLib(boolean delKnowledgeLib) {
        isDelKnowledgeLib = delKnowledgeLib;
    }

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

    public List<KnowledgeLibsMapBean> getKnowledgeLibsMap() {
        return knowledgeLibsMap;
    }

    public void setKnowledgeLibsMap(List<KnowledgeLibsMapBean> knowledgeLibsMap) {
        this.knowledgeLibsMap = knowledgeLibsMap;
    }

    public static class KnowledgeLibsMapBean {

        private String participatePersonNum;
        private String id;
        private String createTime;
        private String editNum;
        private String name;
        private String contentCount;
        private String createPerson;
        private String coverImage;
        private boolean isOpen;

        public String getCoverImage() {
            return coverImage;
        }

        public void setCoverImage(String coverImage) {
            this.coverImage = coverImage;
        }

        public boolean isOpen() {
            return isOpen;
        }

        public void setOpen(boolean open) {
            isOpen = open;
        }

        public String getParticipatePersonNum() {
            return participatePersonNum;
        }

        public void setParticipatePersonNum(String participatePersonNum) {
            this.participatePersonNum = participatePersonNum;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getEditNum() {
            return editNum;
        }

        public void setEditNum(String editNum) {
            this.editNum = editNum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContentCount() {
            return contentCount;
        }

        public void setContentCount(String contentCount) {
            this.contentCount = contentCount;
        }

        public String getCreatePerson() {
            return createPerson;
        }

        public void setCreatePerson(String createPerson) {
            this.createPerson = createPerson;
        }
    }
}
