package com.tuzhi.application.moudle.search.searchpage.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2017/8/9.
 */

public class SearchResultHttpBean {

    /**
     * resultMsg : 正常
     * resultCode : 0
     * articlePage : {"prev":false,"next":false,"size":10,"total":1,"count":1,"index":0,"result":[{"userPraiseStatus":true,"praiseNum":2,"content":"The ","id":464,"time":"2017-08-09 12:22","staffId":7,"nickname":"改的啥","userImage":"http://upload.guigutang.cn:8082/type/20170807/173316405939.jpg","articleId":442,"replyNum":15}]}
     */

    private String resultMsg;
    private String resultCode;

    private ArticlePageBean articlePage;
    /**
     * roleLimits : {"addLimit":false,"viewLimit":true,"editLimit":false,"deleteLimit":false}
     */

    private RoleLimitsBean roleLimits;

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

    public ArticlePageBean getArticlePage() {
        return articlePage;
    }

    public void setArticlePage(ArticlePageBean articlePage) {
        this.articlePage = articlePage;
    }

    public RoleLimitsBean getRoleLimits() {
        return roleLimits;
    }

    public void setRoleLimits(RoleLimitsBean roleLimits) {
        this.roleLimits = roleLimits;
    }

    public static class ArticlePageBean {
        /**
         * prev : false
         * next : false
         * size : 10
         * total : 1
         * count : 1
         * index : 0
         * result : [{"userPraiseStatus":true,"praiseNum":2,"content":"The ","id":464,"time":"2017-08-09 12:22","staffId":7,"nickname":"改的啥","userImage":"http://upload.guigutang.cn:8082/type/20170807/173316405939.jpg","articleId":442,"replyNum":15}]
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
             * userPraiseStatus : true
             * praiseNum : 2
             * content : The
             * id : 464
             * time : 2017-08-09 12:22
             * staffId : 7
             * nickname : 改的啥
             * userImage : http://upload.guigutang.cn:8082/type/20170807/173316405939.jpg
             * articleId : 442
             * replyNum : 15
             */

            /**
             * fileType : 2
             * isDownload : false
             * updateTime : 2017-08-09 19:21
             * previewUrls : ["http://upload.guigutang.cn:8082/tuzhikmMobile/20170809/191909524655.png"]
             * previewNum : 1
             * articleId : 442
             * fileSuffix : png
             * fileUrl : http://upload.guigutang.cn:8082/tuzhikmMobile/20170809/191909524655.png
             * id : 570
             * fileSize : 232.9KB
             * author : 改的啥
             * fileName : 王鹏
             * isPreview : true
             */

            private boolean userPraiseStatus;
            private String praiseNum;
            private String content;
            private String time;
            private String staffId;
            private String nickname;
            private String userImage;
            private String replyNum;
            private String fileType;
            private boolean isDownload;
            private String updateTime;
            private String previewNum;
            private String articleId;
            private String title;
            private String summary;
            private String fileSuffix;
            private String fileUrl;
            private String articleTitle;
            private String id;
            private String fileSize;
            private String author;
            private String fileName;
            private boolean isPreview;
            private boolean isLimit;
            private ArrayList<String> previewUrls;

            public boolean isLimit() {
                return isLimit;
            }

            public void setLimit(boolean limit) {
                isLimit = limit;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getArticleTitle() {
                return articleTitle;
            }

            public void setArticleTitle(String articleTitle) {
                this.articleTitle = articleTitle;
            }

            public String getFileType() {
                return fileType;
            }

            public void setFileType(String fileType) {
                this.fileType = fileType;
            }

            public boolean isDownload() {
                return isDownload;
            }

            public void setDownload(boolean download) {
                isDownload = download;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getPreviewNum() {
                return previewNum;
            }

            public void setPreviewNum(String previewNum) {
                this.previewNum = previewNum;
            }

            public String getFileSuffix() {
                return fileSuffix;
            }

            public void setFileSuffix(String fileSuffix) {
                this.fileSuffix = fileSuffix;
            }

            public String getFileUrl() {
                return fileUrl;
            }

            public void setFileUrl(String fileUrl) {
                this.fileUrl = fileUrl;
            }

            public String getFileSize() {
                return fileSize;
            }

            public void setFileSize(String fileSize) {
                this.fileSize = fileSize;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }

            public boolean isPreview() {
                return isPreview;
            }

            public void setPreview(boolean preview) {
                isPreview = preview;
            }

            public ArrayList<String> getPreviewUrls() {
                return previewUrls;
            }

            public void setPreviewUrls(ArrayList<String> previewUrls) {
                this.previewUrls = previewUrls;
            }

            public boolean isUserPraiseStatus() {
                return userPraiseStatus;
            }

            public void setUserPraiseStatus(boolean userPraiseStatus) {
                this.userPraiseStatus = userPraiseStatus;
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

            public String getStaffId() {
                return staffId;
            }

            public void setStaffId(String staffId) {
                this.staffId = staffId;
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

            public String getArticleId() {
                return articleId;
            }

            public void setArticleId(String articleId) {
                this.articleId = articleId;
            }

            public String getReplyNum() {
                return replyNum;
            }

            public void setReplyNum(String replyNum) {
                this.replyNum = replyNum;
            }
        }
    }

    public static class RoleLimitsBean {
        /**
         * addLimit : false
         * viewLimit : true
         * editLimit : false
         * deleteLimit : false
         */

        private boolean addLimit;
        private boolean viewLimit;
        private boolean editLimit;
        private boolean deleteLimit;

        public boolean isAddLimit() {
            return addLimit;
        }

        public void setAddLimit(boolean addLimit) {
            this.addLimit = addLimit;
        }

        public boolean isViewLimit() {
            return viewLimit;
        }

        public void setViewLimit(boolean viewLimit) {
            this.viewLimit = viewLimit;
        }

        public boolean isEditLimit() {
            return editLimit;
        }

        public void setEditLimit(boolean editLimit) {
            this.editLimit = editLimit;
        }

        public boolean isDeleteLimit() {
            return deleteLimit;
        }

        public void setDeleteLimit(boolean deleteLimit) {
            this.deleteLimit = deleteLimit;
        }
    }
}
