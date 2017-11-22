package com.tuzhi.application.moudle.dynamiccollaboration;

import java.util.List;

/**
 * Created by wangpeng on 2017/10/26.
 */

public class DynamicCollaborationHttpBean {

    /**
     * resultMsg : 正常
     * resultCode : 0
     * editRecordPage : {"prev":false,"next":false,"size":10,"total":1,"count":3,"index":0,"result":[{"authorImg":"所有者","id":3405,"author":"http://upload.guigutang.cn:8082/tuzhikmMobile/20170823/100115481536.jpg","channelName":"wangpeng","libName":"王鹏测","editDesc":"创建了知识卡片","updateTime":"2017-11-21 20:47","channelId":680,"articleTitle":"啦啦啦","libId":340,"articleId":601},{"authorImg":"所有者","id":3221,"author":"http://upload.guigutang.cn:8082/tuzhikmMobile/20170823/100115481536.jpg","channelName":"其他2","libName":"天文","editDesc":"创建了知识卡片","updateTime":"2017-11-21 15:47","channelId":674,"articleTitle":"模版","libId":339,"articleId":600},{"authorImg":"所有者","id":3220,"author":"http://upload.guigutang.cn:8082/tuzhikmMobile/20170823/100115481536.jpg","channelName":"其他2","libName":"天文","editDesc":"创建了知识卡片","updateTime":"2017-11-21 15:47","channelId":674,"articleTitle":"54545","libId":339,"articleId":599}]}
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
         * count : 3
         * index : 0
         * result : [{"authorImg":"所有者","id":3405,"author":"http://upload.guigutang.cn:8082/tuzhikmMobile/20170823/100115481536.jpg","channelName":"wangpeng","libName":"王鹏测","editDesc":"创建了知识卡片","updateTime":"2017-11-21 20:47","channelId":680,"articleTitle":"啦啦啦","libId":340,"articleId":601},{"authorImg":"所有者","id":3221,"author":"http://upload.guigutang.cn:8082/tuzhikmMobile/20170823/100115481536.jpg","channelName":"其他2","libName":"天文","editDesc":"创建了知识卡片","updateTime":"2017-11-21 15:47","channelId":674,"articleTitle":"模版","libId":339,"articleId":600},{"authorImg":"所有者","id":3220,"author":"http://upload.guigutang.cn:8082/tuzhikmMobile/20170823/100115481536.jpg","channelName":"其他2","libName":"天文","editDesc":"创建了知识卡片","updateTime":"2017-11-21 15:47","channelId":674,"articleTitle":"54545","libId":339,"articleId":599}]
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
             * authorImg : 所有者
             * id : 3405
             * author : http://upload.guigutang.cn:8082/tuzhikmMobile/20170823/100115481536.jpg
             * channelName : wangpeng
             * libName : 王鹏测
             * editDesc : 创建了知识卡片
             * updateTime : 2017-11-21 20:47
             * channelId : 680
             * articleTitle : 啦啦啦
             * libId : 340
             * articleId : 601
             */

            private String authorImg;
            private String id;
            private String author;
            private String channelName;
            private String libName;
            private String editDesc;
            private String updateTime;
            private String channelId;
            private String articleTitle;
            private String libId;
            private String articleId;

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

            public String getChannelName() {
                return channelName;
            }

            public void setChannelName(String channelName) {
                this.channelName = channelName;
            }

            public String getLibName() {
                return libName;
            }

            public void setLibName(String libName) {
                this.libName = libName;
            }

            public String getEditDesc() {
                return editDesc;
            }

            public void setEditDesc(String editDesc) {
                this.editDesc = editDesc;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getChannelId() {
                return channelId;
            }

            public void setChannelId(String channelId) {
                this.channelId = channelId;
            }

            public String getArticleTitle() {
                return articleTitle;
            }

            public void setArticleTitle(String articleTitle) {
                this.articleTitle = articleTitle;
            }

            public String getLibId() {
                return libId;
            }

            public void setLibId(String libId) {
                this.libId = libId;
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
