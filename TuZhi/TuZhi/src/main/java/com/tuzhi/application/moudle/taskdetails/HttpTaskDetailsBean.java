package com.tuzhi.application.moudle.taskdetails;

import java.util.List;

/**
 * @author wangpeng
 * @date 2017/11/22
 */

public class HttpTaskDetailsBean {


    private String resultMsg;
    private String resultCode;
    private CommentPageBean commentPage;
    private KnowledgeTaskMapBean knowledgeTaskMap;

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

    public KnowledgeTaskMapBean getKnowledgeTaskMap() {
        return knowledgeTaskMap;
    }

    public void setKnowledgeTaskMap(KnowledgeTaskMapBean knowledgeTaskMap) {
        this.knowledgeTaskMap = knowledgeTaskMap;
    }


    public static class CommentPageBean {
        /**
         * prev : false
         * next : false
         * size : 10
         * total : 0
         * count : 0
         * index : 0
         * result : []
         */

        private boolean prev;
        private boolean next;
        private String size;
        private String total;
        private String count;
        private int index;
        private List<CommentBean> result;

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

        public List<CommentBean> getResult() {
            return result;
        }

        public void setResult(List<CommentBean> result) {
            this.result = result;
        }


    }


    public static class CommentBean {
        /**
         * userPraiseStatus : false
         * praiseNum : 0
         * content : 努力
         * id : 777
         * time : 2017-11-22 13:42
         * staffId : 30
         * nickname : 所有者
         * userImage : http://upload.guigutang.cn:8082/tuzhikmMobile/20170823/100115481536.jpg
         * deleteStatus : true
         * replyNum : 0
         */

        private boolean userPraiseStatus;
        private int praiseNum;
        private String content;
        private int id;
        private String time;
        private int staffId;
        private String nickname;
        private String userImage;
        private boolean deleteStatus;
        private int replyNum;

        public boolean isUserPraiseStatus() {
            return userPraiseStatus;
        }

        public void setUserPraiseStatus(boolean userPraiseStatus) {
            this.userPraiseStatus = userPraiseStatus;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getStaffId() {
            return staffId;
        }

        public void setStaffId(int staffId) {
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

        public boolean isDeleteStatus() {
            return deleteStatus;
        }

        public void setDeleteStatus(boolean deleteStatus) {
            this.deleteStatus = deleteStatus;
        }

        public int getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(int replyNum) {
            this.replyNum = replyNum;
        }
    }

    public static class KnowledgeTaskMapBean {

        private String createTime;
        private String responsible;
        private String libId;
        private String id;
        private ParticipantListBean responsibleMap;
        private String libName;
        private String title;
        private boolean taskStatus;
        private String description;
        private String participant;
        private String relevanceCard;
        private List<ParticipantListBean> participantList;
        private List<RelevanceCardListBean> relevanceCardList;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getResponsible() {
            return responsible;
        }

        public void setResponsible(String responsible) {
            this.responsible = responsible;
        }

        public String getLibId() {
            return libId;
        }

        public void setLibId(String libId) {
            this.libId = libId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public ParticipantListBean getResponsibleMap() {
            return responsibleMap;
        }

        public void setResponsibleMap(ParticipantListBean responsibleMap) {
            this.responsibleMap = responsibleMap;
        }

        public String getLibName() {
            return libName;
        }

        public void setLibName(String libName) {
            this.libName = libName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean getTaskStatus() {
            return taskStatus;
        }

        public void setTaskStatus(boolean taskStatus) {
            this.taskStatus = taskStatus;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getParticipant() {
            return participant;
        }

        public void setParticipant(String participant) {
            this.participant = participant;
        }

        public String getRelevanceCard() {
            return relevanceCard;
        }

        public void setRelevanceCard(String relevanceCard) {
            this.relevanceCard = relevanceCard;
        }

        public List<ParticipantListBean> getParticipantList() {
            return participantList;
        }

        public void setParticipantList(List<ParticipantListBean> participantList) {
            this.participantList = participantList;
        }

        public List<RelevanceCardListBean> getRelevanceCardList() {
            return relevanceCardList;
        }

        public void setRelevanceCardList(List<RelevanceCardListBean> relevanceCardList) {
            this.relevanceCardList = relevanceCardList;
        }

        public static class ParticipantListBean {
            /**
             * id : 41
             * phone : 15067194667
             * email : null
             * nickname : zzz
             * userImage : null
             */

            private String id;
            private String phone;
            private String email;
            private String nickname;
            private String userImage;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
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
        }

        public static class RelevanceCardListBean {
            /**
             * id : 601
             * title : 啦啦啦
             * updateTime : 2017-11-21 20:47
             */

            private String id;
            private String title;
            private String updateTime;

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
        }
    }
}
