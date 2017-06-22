package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.commentlist.bean;

import java.util.List;

/**
 * Created by wangpeng on 2017/6/22.
 */

public class HttpCommentListBean {
    private String resultMsg;
    private String resultCode;
    private CommentPageBean commentPage;
    private CommentBean comment;

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

    public CommentBean getComment() {
        return comment;
    }

    public void setComment(CommentBean comment) {
        this.comment = comment;
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

    public static class CommentBean {

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
