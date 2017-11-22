package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.choosecolleague.bean.ChooseColleagueItemBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class ChooseColleagueContract {
    interface View extends BaseView {
        /**
         * 下载回调
         *
         * @param data
         * @param haveNextPage
         * @param page
         */
        void downloadFinish(ArrayList<ChooseColleagueItemBean> data, boolean haveNextPage, int page);

        /**
         * 下载回调
         */
        void downloadFinish();

        /**
         * 分享卡片回调
         */
        void shareCardSuccess();

        /**
         * 提交回调
         */
        void commitSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 下载分享卡片成员
         *
         * @param cId
         * @param page
         */
        void downloadDataColleague(String cId, int page);

        /**
         * 下载频道
         *
         * @param lId
         * @param page
         */
        void downloadDataChannel(String lId, int page);

        /**
         * 下载卡片
         *
         * @param channelId
         * @param page
         */

        void downloadDataCare(String channelId, int page);

        /**
         * 下载管理员数据
         *
         * @param klId
         * @param ktId
         * @param page
         */
        void downloadDataManager(String klId, String ktId, int page);

        /**
         * 下载成员数据
         *
         * @param klId
         * @param page
         * @param ktId
         */
        void downloadDataPeople(String klId, String ktId, int page);

        /**
         * 分享卡片
         *
         * @param cid
         * @param staffIds
         * @param content
         */
        void shareCard(String cid, String staffIds, String content);

        /**
         * 下载可添加的成员列表
         *
         * @param type
         * @param id
         */

        void downloadAddMemberList(String type, String id, int pageNo);

        /**
         * 下载可移除的成员列表
         *
         * @param type
         * @param id
         */

        void downloadRemoveMemberList(String type, String id);

        /**
         * 添加成员
         *
         * @param addMembers
         */
        void commitAddMember(ArrayList<ChooseColleagueItemBean> addMembers);

        /**
         * 删除成员
         *
         * @param addMembers
         */
        void commitRemoveMember(ArrayList<ChooseColleagueItemBean> addMembers);


    }
}
