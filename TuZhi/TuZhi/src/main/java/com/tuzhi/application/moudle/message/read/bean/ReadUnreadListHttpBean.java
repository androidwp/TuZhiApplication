package com.tuzhi.application.moudle.message.read.bean;

import java.util.List;

/**
 * Created by wangpeng on 2017/8/9.
 */

public class ReadUnreadListHttpBean {

    /**
     * resultMsg : 正常
     * noticePage : {"prev":false,"next":false,"size":10,"total":1,"count":2,"index":0,"result":[{"content":"测测测","sourceContent":"The ","id":4,"time":"2017-08-09 12:22","nickname":"双击666","description":"评论了你的:","userImage":"http://upload.guigutang.cn:8082/tuzhikmMobile/20170809/095644770709.jpg","operateUserId":7,"type":2,"commentId":465,"articleId":442},{"content":"","sourceContent":"The ","id":3,"time":"2017-08-09 12:22","nickname":"双击666","description":"赞了你的:","userImage":"http://upload.guigutang.cn:8082/tuzhikmMobile/20170809/095644770709.jpg","operateUserId":7,"type":3,"commentId":464,"articleId":442}]}
     * readCount : 0
     * resultCode : 0
     */

    private String resultMsg;
    private NoticePageBean noticePage;
    private String readCount;
    private String resultCode;

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public NoticePageBean getNoticePage() {
        return noticePage;
    }

    public void setNoticePage(NoticePageBean noticePage) {
        this.noticePage = noticePage;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public static class NoticePageBean {
        /**
         * prev : false
         * next : false
         * size : 10
         * total : 1
         * count : 2
         * index : 0
         * result : [{"content":"测测测","sourceContent":"The ","id":4,"time":"2017-08-09 12:22","nickname":"双击666","description":"评论了你的:","userImage":"http://upload.guigutang.cn:8082/tuzhikmMobile/20170809/095644770709.jpg","operateUserId":7,"type":2,"commentId":465,"articleId":442},{"content":"","sourceContent":"The ","id":3,"time":"2017-08-09 12:22","nickname":"双击666","description":"赞了你的:","userImage":"http://upload.guigutang.cn:8082/tuzhikmMobile/20170809/095644770709.jpg","operateUserId":7,"type":3,"commentId":464,"articleId":442}]
         */

        private boolean prev;
        private boolean next;
        private String size;
        private String total;
        private int count;
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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
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
             * content : 测测测
             * sourceContent : The
             * id : 4
             * time : 2017-08-09 12:22
             * nickname : 双击666
             * description : 评论了你的:
             * userImage : http://upload.guigutang.cn:8082/tuzhikmMobile/20170809/095644770709.jpg
             * operateUserId : 7
             * type : 2
             * commentId : 465
             * articleId : 442
             */

            private String content;
            private String sourceContent;
            private String id;
            private String time;
            private String nickname;
            private String description;
            private String userImage;
            private String operateUserId;
            private int type;
            private String commentId;
            private String articleId;
            private String articleTitle;

            public String getArticleTitle() {
                return articleTitle;
            }

            public void setArticleTitle(String articleTitle) {
                this.articleTitle = articleTitle;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getSourceContent() {
                return sourceContent;
            }

            public void setSourceContent(String sourceContent) {
                this.sourceContent = sourceContent;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getUserImage() {
                return userImage;
            }

            public void setUserImage(String userImage) {
                this.userImage = userImage;
            }

            public String getOperateUserId() {
                return operateUserId;
            }

            public void setOperateUserId(String operateUserId) {
                this.operateUserId = operateUserId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getCommentId() {
                return commentId;
            }

            public void setCommentId(String commentId) {
                this.commentId = commentId;
            }

            public String getArticleId() {
                return articleId;
            }

            public void setArticleId(String articleId) {
                this.articleId = articleId;
            }
        }
    }
}
