package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wangpeng
 * @date 2017/6/21
 */

public class HttpKnowledgeDetailsListBean {

    private String resultMsg;
    private ArticleMapBean articleMap;
    private String resultCode;
    private CommentPageBean commentPage;
    private boolean articlePraise;
    private List<ArticleFilesMapBean> articleFilesMap;

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

    public CommentPageBean getCommentPage() {
        return commentPage;
    }

    public void setCommentPage(CommentPageBean commentPage) {
        this.commentPage = commentPage;
    }

    public boolean isArticlePraise() {
        return articlePraise;
    }

    public void setArticlePraise(boolean articlePraise) {
        this.articlePraise = articlePraise;
    }

    public List<ArticleFilesMapBean> getArticleFilesMap() {
        return articleFilesMap;
    }

    public void setArticleFilesMap(List<ArticleFilesMapBean> articleFilesMap) {
        this.articleFilesMap = articleFilesMap;
    }

    public static class ArticleMapBean {
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
        private String articleUrl;
        private String editContentUrl;
        private String viewContentUrl;

        public String getEditContentUrl() {
            return editContentUrl;
        }

        public void setEditContentUrl(String editContentUrl) {
            this.editContentUrl = editContentUrl;
        }

        public String getViewContentUrl() {
            return viewContentUrl;
        }

        public void setViewContentUrl(String viewContentUrl) {
            this.viewContentUrl = viewContentUrl;
        }

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

        public String getArticleUrl() {
            return articleUrl;
        }

        public void setArticleUrl(String articleUrl) {
            this.articleUrl = articleUrl;
        }
    }

    public static class CommentPageBean {
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

            private boolean userPraiseStatus;
            private String praiseNum;
            private String content;
            private String id;
            private String time;
            private String staffId;
            private String nickname;
            private String userImage;
            private String replyNum;

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

            public String getReplyNum() {
                return replyNum;
            }

            public void setReplyNum(String replyNum) {
                this.replyNum = replyNum;
            }
        }
    }

    public static class ArticleFilesMapBean {
        private String fileSize;
        private String id;
        private String author;
        private String fileType;
        private String updateTime;
        private String fileName;
        private String fileSuffix;
        private String fileUrl;
        private boolean isPreview;
        private ArrayList<String> previewUrls;

        public ArrayList<String> getPreviewUrls() {
            return previewUrls;
        }

        public void setPreviewUrls(ArrayList<String> previewUrls) {
            this.previewUrls = previewUrls;
        }

        public boolean isPreview() {
            return isPreview;
        }

        public void setPreview(boolean preview) {
            isPreview = preview;
        }

        public String getFileSize() {
            return fileSize;
        }

        public void setFileSize(String fileSize) {
            this.fileSize = fileSize;
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

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
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
    }
}
