package com.tuzhi.application.moudle.repository.knowledgachannel.bean;

import java.util.List;

/**
 * Created by wangpeng on 2017/6/21.
 */

public class KnowledgeChannelHttpBean {

    /**
     * resultMsg : 正常
     * knowledgeChannelsMap : [{"id":73,"createTime":"2017-08-03 21:03","articleCount":0,"name":"fsfsdf","creator":29},{"id":70,"createTime":"2017-08-03 20:22","articleCount":0,"name":"dddds","creator":29},{"id":69,"createTime":"2017-08-03 20:22","articleCount":0,"name":"ssss","creator":29},{"id":68,"createTime":"2017-08-03 20:20","articleCount":0,"name":"dddd","creator":29},{"id":67,"createTime":"2017-08-03 20:14","articleCount":0,"name":"88","creator":29},{"id":66,"createTime":"2017-08-03 19:56","articleCount":0,"name":"dd","creator":29},{"id":65,"createTime":"2017-08-03 19:55","articleCount":0,"name":"item","creator":29},{"id":64,"createTime":"2017-08-03 14:03","articleCount":0,"name":"第一个频道","creator":29}]
     * isDelKnowledgeChannel : false
     * resultCode : 0
     */

    private String resultMsg;
    private boolean isDelKnowledgeChannel;
    private String resultCode;
    private List<KnowledgeChannelsMapBean> knowledgeChannelsMap;

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public boolean isIsDelKnowledgeChannel() {
        return isDelKnowledgeChannel;
    }

    public void setIsDelKnowledgeChannel(boolean isDelKnowledgeChannel) {
        this.isDelKnowledgeChannel = isDelKnowledgeChannel;
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
        private int creator;

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

        public int getCreator() {
            return creator;
        }

        public void setCreator(int creator) {
            this.creator = creator;
        }
    }
}
