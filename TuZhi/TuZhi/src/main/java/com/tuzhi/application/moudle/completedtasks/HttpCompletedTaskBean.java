package com.tuzhi.application.moudle.completedtasks;

import java.util.List;

/**
 * Created by wangpeng on 2017/11/21.
 */

public class HttpCompletedTaskBean {

    /**
     * resultMsg : 正常
     * resultCode : 0
     * knowledgeTaskMapLists : {"prev":false,"next":false,"size":10,"total":1,"count":2,"index":0,"result":[{"id":6,"createTime":"2017-11-21 18:01","title":"新建任务","libId":340},{"id":5,"createTime":"2017-11-21 17:53","title":"创建任务","libId":340}]}
     */

    private String resultMsg;
    private String resultCode;
    private KnowledgeTaskMapListsBean knowledgeTaskMapLists;

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

    public KnowledgeTaskMapListsBean getKnowledgeTaskMapLists() {
        return knowledgeTaskMapLists;
    }

    public void setKnowledgeTaskMapLists(KnowledgeTaskMapListsBean knowledgeTaskMapLists) {
        this.knowledgeTaskMapLists = knowledgeTaskMapLists;
    }

    public static class KnowledgeTaskMapListsBean {
        /**
         * prev : false
         * next : false
         * size : 10
         * total : 1
         * count : 2
         * index : 0
         * result : [{"id":6,"createTime":"2017-11-21 18:01","title":"新建任务","libId":340},{"id":5,"createTime":"2017-11-21 17:53","title":"创建任务","libId":340}]
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
             * id : 6
             * createTime : 2017-11-21 18:01
             * title : 新建任务
             * libId : 340
             */

            private String id;
            private String createTime;
            private String title;
            private String libId;

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLibId() {
                return libId;
            }

            public void setLibId(String libId) {
                this.libId = libId;
            }
        }
    }
}
