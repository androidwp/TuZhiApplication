package com.tuzhi.application.moudle.repository.crepository.bean;

/**
 * Created by wangpeng on 2017/11/23.
 */

public class HttpCreateCardBean {

    /**
     * resultMsg : 正常
     * articleMap : {"commentNum":0,"praiseNum":0,"content":"","summary":null,"id":617,"sourceTypeStr":"PGC","sourceType":3,"author":30,"title":"啦啦啦","readNum":0,"updateTime":null,"viewContentUrl":"http://192.168.0.151:8089/mobile/articleContent/2/30/617-0.html"}
     * resultCode : 0
     * resultStatus : Success
     */

    private String resultMsg;
    private ArticleMapBean articleMap;
    private String resultCode;
    private String resultStatus;

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public ArticleMapBean getArticleMap() {
        return articleMap;
    }

    public void setArticleMap(ArticleMapBean articleMap) {
        this.articleMap = articleMap;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public static class ArticleMapBean {
        /**
         * commentNum : 0
         * praiseNum : 0
         * content :
         * summary : null
         * id : 617
         * sourceTypeStr : PGC
         * sourceType : 3
         * author : 30
         * title : 啦啦啦
         * readNum : 0
         * updateTime : null
         * viewContentUrl : http://192.168.0.151:8089/mobile/articleContent/2/30/617-0.html
         */

        private String commentNum;
        private String praiseNum;
        private String content;
        private String summary;
        private String id;
        private String sourceTypeStr;
        private String sourceType;
        private String author;
        private String title;
        private String readNum;
        private String updateTime;
        private String viewContentUrl;

        public String getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(String commentNum) {
            this.commentNum = commentNum;
        }

        public String getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(String praiseNum) {
            this.praiseNum = praiseNum;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSourceTypeStr() {
            return sourceTypeStr;
        }

        public void setSourceTypeStr(String sourceTypeStr) {
            this.sourceTypeStr = sourceTypeStr;
        }

        public String getSourceType() {
            return sourceType;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getReadNum() {
            return readNum;
        }

        public void setReadNum(String readNum) {
            this.readNum = readNum;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getViewContentUrl() {
            return viewContentUrl;
        }

        public void setViewContentUrl(String viewContentUrl) {
            this.viewContentUrl = viewContentUrl;
        }
    }
}
