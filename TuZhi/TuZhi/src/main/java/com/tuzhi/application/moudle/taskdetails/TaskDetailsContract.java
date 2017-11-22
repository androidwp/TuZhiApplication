package com.tuzhi.application.moudle.taskdetails;

import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;
import com.tuzhi.application.moudle.createtask.NewTaskBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class TaskDetailsContract {
    interface View extends BaseView {
        /**
         * 数据回调
         *
         * @param httpData
         * @param haveNextPage
         * @param page
         */
        void downloadSuccess(ArrayList<ItemBean> httpData, boolean haveNextPage, int page);

        /**
         * 提交成功回调
         */
        void commitCommentSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 下载数据
         *
         * @param id
         * @param page
         */
        void downloadData(String id, int page);

        /**
         * 提交评论
         *
         * @param tid     任务id
         * @param comment 评论
         * @param ids     @的人
         */
        void commitComment(String tid, String comment, String ids);

        /**
         * 完成任务接口
         *
         * @param id 任务id
         */
        void taskFinished(String id);


        /**
         * 未完成任务接口
         *
         * @param id 任务id
         */
        void taskUnfinished(String id);

        /**
         * 创建任务
         *
         * @param bean
         */
        void updateTask(NewTaskBean bean);
    }
}
