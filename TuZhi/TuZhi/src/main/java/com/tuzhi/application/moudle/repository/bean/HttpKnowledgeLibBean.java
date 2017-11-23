package com.tuzhi.application.moudle.repository.bean;

import java.util.List;

/**
 * Created by wangpeng on 2017/11/20.
 */

public class HttpKnowledgeLibBean {

    private String resultMsg;
    private String resultCode;
    private List<PublicListMapsBean> publicListMaps;
    private List<PublicListMapsBean> collectionKnowledgeLibsMap;
    private List<PublicListMapsBean> projectListMaps;
    private List<PublicListMapsBean> departmentListMaps;

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

    public List<PublicListMapsBean> getPublicListMaps() {
        return publicListMaps;
    }

    public void setPublicListMaps(List<PublicListMapsBean> publicListMaps) {
        this.publicListMaps = publicListMaps;
    }

    public List<PublicListMapsBean> getCollectionKnowledgeLibsMap() {
        return collectionKnowledgeLibsMap;
    }

    public void setCollectionKnowledgeLibsMap(List<PublicListMapsBean> collectionKnowledgeLibsMap) {
        this.collectionKnowledgeLibsMap = collectionKnowledgeLibsMap;
    }

    public List<PublicListMapsBean> getProjectListMaps() {
        return projectListMaps;
    }

    public void setProjectListMaps(List<PublicListMapsBean> projectListMaps) {
        this.projectListMaps = projectListMaps;
    }

    public List<PublicListMapsBean> getDepartmentListMaps() {
        return departmentListMaps;
    }

    public void setDepartmentListMaps(List<PublicListMapsBean> departmentListMaps) {
        this.departmentListMaps = departmentListMaps;
    }

    public static class PublicListMapsBean {
        /**
         * participatePersonNum : 0
         * id : 198
         * createTime : 2017-08-09 12:21
         * name : 评论消息测试
         * contentCount : 2
         * createPerson : 28
         * coverImage : null
         * isOpen : false
         */

        private String participatePersonNum;
        private String id;
        private String createTime;
        private String name;
        private String contentCount;
        private String createPerson;
        private String coverImage;
        private String type;
        private boolean isOpen;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getCoverImage() {
            return coverImage;
        }

        public void setCoverImage(String coverImage) {
            this.coverImage = coverImage;
        }

        public boolean isIsOpen() {
            return isOpen;
        }

        public void setIsOpen(boolean isOpen) {
            this.isOpen = isOpen;
        }
    }
}
