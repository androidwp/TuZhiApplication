package com.tuzhi.application.moudle.enterpriseknowledge.bean;

import java.util.List;

/**
 * Created by wangpeng on 2017/6/21.
 */

public class HttpKnowledgeModuleBean {

    private String resultMsg;
    private String resultCode;
    private boolean isDelArticle;

    private ArticlePageBean articlePage;

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

    public boolean isDelArticle() {
        return isDelArticle;
    }

    public void setDelArticle(boolean delArticle) {
        isDelArticle = delArticle;
    }

    public void setArticlePage(ArticlePageBean articlePage) {
        this.articlePage = articlePage;
    }

    public static class ArticlePageBean {

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

            private String commentNum;
            private String summary;
            private String id;
            private String title;
            private String updateTime;
            private String fileNum;

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

            public String getFileNum() {
                return fileNum;
            }

            public void setFileNum(String fileNum) {
                this.fileNum = fileNum;
            }
        }
    }
}
