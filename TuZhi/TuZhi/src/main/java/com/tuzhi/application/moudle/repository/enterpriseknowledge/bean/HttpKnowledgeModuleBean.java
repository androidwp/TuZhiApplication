package com.tuzhi.application.moudle.repository.enterpriseknowledge.bean;

import java.util.List;

/**
 * @author wangpeng
 * @date 2017/6/21
 */

public class HttpKnowledgeModuleBean {


    /**
     * resultMsg : 正常
     * isDelKnowledgeChannel : false
     * knowledgeChannelMap : {"id":76,"createTime":"2017-08-05 17:34","articleCount":4,"name":"1频道","creator":29}
     * resultCode : 0
     * articlePage : {"prev":false,"next":false,"size":6,"total":1,"count":2,"index":0,"result":[{"praiseNum":1,"commentNum":0,"summary":"","id":430,"author":null,"title":"fdf","updateTime":"2017-08-05","partners":[],"articlePraise":false,"fileNum":0},{"praiseNum":1,"commentNum":0,"summary":"","id":428,"author":null,"title":"ss","updateTime":"2017-08-05","partners":[],"articlePraise":false,"fileNum":0}]}
     */

    private String resultMsg;
    private boolean isDelKnowledgeChannel;
    private KnowledgeChannelMapBean knowledgeChannelMap;
    private String resultCode;
    private ArticlePageBean articlePage;

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

    public KnowledgeChannelMapBean getKnowledgeChannelMap() {
        return knowledgeChannelMap;
    }

    public void setKnowledgeChannelMap(KnowledgeChannelMapBean knowledgeChannelMap) {
        this.knowledgeChannelMap = knowledgeChannelMap;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public ArticlePageBean getArticlePage() {
        return articlePage;
    }

    public void setArticlePage(ArticlePageBean articlePage) {
        this.articlePage = articlePage;
    }

    public static class KnowledgeChannelMapBean {
        /**
         * id : 76
         * createTime : 2017-08-05 17:34
         * articleCount : 4
         * name : 1频道
         * creator : 29
         */

        private String id;
        private String createTime;
        private String articleCount;
        private String name;
        private String creator;
        private String summary;
        private boolean isOpen;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

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

    public static class ArticlePageBean {
        /**
         * prev : false
         * next : false
         * size : 6
         * total : 1
         * count : 2
         * index : 0
         * result : [{"praiseNum":1,"commentNum":0,"summary":"","id":430,"author":null,"title":"fdf","updateTime":"2017-08-05","partners":[],"articlePraise":false,"fileNum":0},{"praiseNum":1,"commentNum":0,"summary":"","id":428,"author":null,"title":"ss","updateTime":"2017-08-05","partners":[],"articlePraise":false,"fileNum":0}]
         */

        private boolean prev;
        private boolean next;
        private String size;
        private String total;
        private String count;
        private int index;
        private List<ResultBean> result;


        public boolean isPrev() {
            return prev;
        }

        public void setPrev(boolean prev) {
            this.prev = prev;
        }

        public boolean isNext() {
            return next;
        }

        public void setNext(boolean next) {
            this.next = next;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }


        public static class ResultBean {
            /**
             * praiseNum : 1
             * commentNum : 0
             * summary :
             * id : 430
             * author : null
             * title : fdf
             * updateTime : 2017-08-05
             * partners : []
             * articlePraise : false
             * fileNum : 0
             */

            private String praiseNum;
            private String commentNum;
            private String summary;
            private String id;
            private String author;
            private String title;
            private String updateTime;
            private boolean articlePraise;
            private String fileNum;
            private List<UserInfoBean> partners;

            public String getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(String praiseNum) {
                this.praiseNum = praiseNum;
            }

            public String getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(String commentNum) {
                this.commentNum = commentNum;
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

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public boolean isArticlePraise() {
                return articlePraise;
            }

            public void setArticlePraise(boolean articlePraise) {
                this.articlePraise = articlePraise;
            }

            public String getFileNum() {
                return fileNum;
            }

            public void setFileNum(String fileNum) {
                this.fileNum = fileNum;
            }

            public List<UserInfoBean> getPartners() {
                return partners;
            }

            public void setPartners(List<UserInfoBean> partners) {
                this.partners = partners;
            }
        }

        public static class UserInfoBean {
            /**
             * userId : 7
             * userImage : http://upload.guigutang.cn:8082/type/20170807/173316405939.jpg
             */

            private String userId;
            private String userImage;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserImage() {
                return userImage;
            }

            public void setUserImage(String userImage) {
                this.userImage = userImage;
            }
        }
    }
}
