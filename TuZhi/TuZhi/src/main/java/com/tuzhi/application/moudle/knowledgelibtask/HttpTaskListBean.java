package com.tuzhi.application.moudle.knowledgelibtask;

import java.util.List;

/**
 * Created by wangpeng on 2017/11/21.
 */

public class HttpTaskListBean {

    /**
     * resultMsg : 正常
     * resultCode : 0
     * knowledgeTaskMapLists : [{"id":5,"createTime":"2017-11-21 17:53","title":"创建任务","libId":340},{"id":4,"createTime":"2017-11-21 17:53","title":"创建任务","libId":340}]
     */

    private String resultMsg;
    private String resultCode;
    private List<KnowledgeTaskMapListsBean> knowledgeTaskMapLists;

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

    public List<KnowledgeTaskMapListsBean> getKnowledgeTaskMapLists() {
        return knowledgeTaskMapLists;
    }

    public void setKnowledgeTaskMapLists(List<KnowledgeTaskMapListsBean> knowledgeTaskMapLists) {
        this.knowledgeTaskMapLists = knowledgeTaskMapLists;
    }

    public static class KnowledgeTaskMapListsBean {
        /**
         * id : 5
         * createTime : 2017-11-21 17:53
         * title : 创建任务
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
