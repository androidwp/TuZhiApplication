package com.tuzhi.application.moudle.repository.knowledgachannel.bean;

import java.util.List;

/**
 * @author wangpeng
 * @date 2017/6/21
 */

public class KnowledgeChannelHttpBean {

    /**
     * resultMsg : 正常
     * knowledgeChannelsMap : [{"id":73,"createTime":"2017-08-03 21:03","articleCount":0,"name":"fsfsdf","creator":29},{"id":70,"createTime":"2017-08-03 20:22","articleCount":0,"name":"dddds","creator":29},{"id":69,"createTime":"2017-08-03 20:22","articleCount":0,"name":"ssss","creator":29},{"id":68,"createTime":"2017-08-03 20:20","articleCount":0,"name":"dddd","creator":29},{"id":67,"createTime":"2017-08-03 20:14","articleCount":0,"name":"88","creator":29},{"id":66,"createTime":"2017-08-03 19:56","articleCount":0,"name":"dd","creator":29},{"id":65,"createTime":"2017-08-03 19:55","articleCount":0,"name":"item","creator":29},{"id":64,"createTime":"2017-08-03 14:03","articleCount":0,"name":"第一个频道","creator":29}]
     * isDelKnowledgeChannel : false
     * resultCode : 0
     */

    private String resultMsg;
    private String resultCode;
    private List<KnowledgeChannelsMapBean> knowledgeChannelsMap;
    private KnowledgeLibMapBean knowledgeLibMap;


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

    public List<KnowledgeChannelsMapBean> getKnowledgeChannelsMap() {
        return knowledgeChannelsMap;
    }

    public void setKnowledgeChannelsMap(List<KnowledgeChannelsMapBean> knowledgeChannelsMap) {
        this.knowledgeChannelsMap = knowledgeChannelsMap;
    }

    public KnowledgeLibMapBean getKnowledgeLibMap() {
        return knowledgeLibMap;
    }

    public void setKnowledgeLibMap(KnowledgeLibMapBean knowledgeLibMap) {
        this.knowledgeLibMap = knowledgeLibMap;
    }

    public static class KnowledgeChannelsMapBean {
        /**
         * id : 73
         * createTime : 2017-08-03 21:03
         * articleCount : 0
         * name : fsfsdf
         * creator : 29
         */

        private String id;
        private String createTime;
        private String articleCount;
        private String name;
        private String creator;
        private boolean isOpen;

        public boolean isOpen() {
            return isOpen;
        }

        public void setOpen(boolean open) {
            isOpen = open;
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

        public String getArticleCount() {
            return articleCount;
        }

        public void setArticleCount(String articleCount) {
            this.articleCount = articleCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }
    }

    public static class KnowledgeLibMapBean {
        /**
         * participatePersonNum : 0
         * id : 343
         * createTime : 2017-11-22 10:52
         * isCollection : true
         * name : 王一臣测试
         * contentCount : 9
         * createPerson : 30
         * type : 1
         * coverImage : http://192.168.0.140:8083/libImage/image1.png
         * isOpen : true
         */

        private String participatePersonNum;
        private String id;
        private String createTime;
        private boolean isCollection;
        private String name;
        private String contentCount;
        private String createPerson;
        private String type;
        private String coverImage;
        private boolean isOpen;

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

        public boolean isIsCollection() {
            return isCollection;
        }

        public void setIsCollection(boolean isCollection) {
            this.isCollection = isCollection;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
