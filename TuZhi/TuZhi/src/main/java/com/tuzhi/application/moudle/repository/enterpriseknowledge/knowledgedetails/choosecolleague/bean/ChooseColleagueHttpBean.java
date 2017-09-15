package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.bean;

import java.util.List;

/**
 * Created by wangpeng on 2017/9/8.
 */

public class ChooseColleagueHttpBean {

    /**
     * resultMsg : 正常
     * resultCode : 0
     * commentPage : {"prev":false,"next":false,"size":20,"total":1,"count":19,"index":0,"result":[{"id":29,"username":"18565125455","userImage":""},{"id":28,"username":"15268965656","userImage":"http://upload.guigutang.cn:8082/tuzhikmMobile/20170809/095644770709.jpg"},{"id":27,"username":"18526589654","userImage":""},{"id":26,"username":"18565213258","userImage":""},{"id":25,"username":"18565125456","userImage":"http://upload.tuzhikm.com:8082/userImage/20170725/170508798124.png"},{"id":23,"username":"15265965553","userImage":""},{"id":22,"username":"15067158565","userImage":"http://192.168.0.132:8082/userImage/20170703/17562227691.jpg"},{"id":20,"username":"15865965657","userImage":""},{"id":19,"username":"15865965656","userImage":""},{"id":18,"username":"15265965554","userImage":""},{"id":17,"username":"18668067327","userImage":"http://192.168.0.132:8082/userImage/20170630/173819946955.jpg"},{"id":15,"username":"17682318322","userImage":"http://192.168.0.132:8082/tuzhikmMobile/20170630/163217668579.jpg"},{"id":11,"username":"15089873321","userImage":""},{"id":10,"username":"18265659565","userImage":"http://192.168.0.132:8082/tuzhikmMobile/20170706/180339163428.jpg"},{"id":9,"username":"15869635588","userImage":""},{"id":7,"username":"15689453332","userImage":"http://upload.guigutang.cn:8082/type/20170807/173316405939.jpg"},{"id":6,"username":"18565325458","userImage":""},{"id":5,"username":"admin","userImage":""},{"id":2,"username":"tuzhi002","userImage":"http://192.168.0.132:8082/userImage/20170725/170654850473.jpg"}]}
     */

    private String resultMsg;
    private String resultCode;
    private CommentPageBean commentPage;

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

    public CommentPageBean getCommentPage() {
        return commentPage;
    }

    public void setCommentPage(CommentPageBean commentPage) {
        this.commentPage = commentPage;
    }

    public static class CommentPageBean {
        /**
         * prev : false
         * next : false
         * size : 20
         * total : 1
         * count : 19
         * index : 0
         * result : [{"id":29,"username":"18565125455","userImage":""},{"id":28,"username":"15268965656","userImage":"http://upload.guigutang.cn:8082/tuzhikmMobile/20170809/095644770709.jpg"},{"id":27,"username":"18526589654","userImage":""},{"id":26,"username":"18565213258","userImage":""},{"id":25,"username":"18565125456","userImage":"http://upload.tuzhikm.com:8082/userImage/20170725/170508798124.png"},{"id":23,"username":"15265965553","userImage":""},{"id":22,"username":"15067158565","userImage":"http://192.168.0.132:8082/userImage/20170703/17562227691.jpg"},{"id":20,"username":"15865965657","userImage":""},{"id":19,"username":"15865965656","userImage":""},{"id":18,"username":"15265965554","userImage":""},{"id":17,"username":"18668067327","userImage":"http://192.168.0.132:8082/userImage/20170630/173819946955.jpg"},{"id":15,"username":"17682318322","userImage":"http://192.168.0.132:8082/tuzhikmMobile/20170630/163217668579.jpg"},{"id":11,"username":"15089873321","userImage":""},{"id":10,"username":"18265659565","userImage":"http://192.168.0.132:8082/tuzhikmMobile/20170706/180339163428.jpg"},{"id":9,"username":"15869635588","userImage":""},{"id":7,"username":"15689453332","userImage":"http://upload.guigutang.cn:8082/type/20170807/173316405939.jpg"},{"id":6,"username":"18565325458","userImage":""},{"id":5,"username":"admin","userImage":""},{"id":2,"username":"tuzhi002","userImage":"http://192.168.0.132:8082/userImage/20170725/170654850473.jpg"}]
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
             * id : 29
             * username : 18565125455
             * userImage : 
             */

            private String id;
            private String username;
            private String userImage;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
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
