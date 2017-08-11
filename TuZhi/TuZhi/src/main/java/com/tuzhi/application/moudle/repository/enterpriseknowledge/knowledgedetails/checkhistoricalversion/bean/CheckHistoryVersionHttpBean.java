package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.checkhistoricalversion.bean;

import java.util.List;

/**
 * Created by wangpeng on 2017/8/10.
 */

public class CheckHistoryVersionHttpBean {

    /**
     * resultMsg : 正常
     * resultCode : 0
     * editRecordPage : {"prev":false,"next":false,"size":10,"total":1,"count":2,"index":0,"result":[{"authorImg":"http://upload.guigutang.cn:8082/type/20170807/173316405939.jpg","id":2046,"author":"改的啥","updateTime":"2017-08-10 16:31"},{"authorImg":"http://upload.guigutang.cn:8082/type/20170807/173316405939.jpg","id":2045,"author":"改的啥","updateTime":"2017-08-10 16:31"}]}
     */

    private String resultMsg;
    private String resultCode;
    private EditRecordPageBean editRecordPage;

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

    public EditRecordPageBean getEditRecordPage() {
        return editRecordPage;
    }

    public void setEditRecordPage(EditRecordPageBean editRecordPage) {
        this.editRecordPage = editRecordPage;
    }

    public static class EditRecordPageBean {
        /**
         * prev : false
         * next : false
         * size : 10
         * total : 1
         * count : 2
         * index : 0
         * result : [{"authorImg":"http://upload.guigutang.cn:8082/type/20170807/173316405939.jpg","id":2046,"author":"改的啥","updateTime":"2017-08-10 16:31"},{"authorImg":"http://upload.guigutang.cn:8082/type/20170807/173316405939.jpg","id":2045,"author":"改的啥","updateTime":"2017-08-10 16:31"}]
         */

        private boolean prev;
        private boolean next;
        private int size;
        private int total;
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

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
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
             * authorImg : http://upload.guigutang.cn:8082/type/20170807/173316405939.jpg
             * id : 2046
             * author : 改的啥
             * updateTime : 2017-08-10 16:31
             */

            private String authorImg;
            private String id;
            private String author;
            private String updateTime;

            public String getAuthorImg() {
                return authorImg;
            }

            public void setAuthorImg(String authorImg) {
                this.authorImg = authorImg;
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

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
