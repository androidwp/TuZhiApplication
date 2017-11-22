package com.tuzhi.application.moudle.completedtasks;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;
import com.tuzhi.application.moudle.mytasks.MyTasksItemBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class CompletedTasksContract {
    interface View extends BaseView {
        /**
         * 下载完成回调
         *
         * @param data
         * @param haveNextPage
         * @param page
         */
        void downloadFinish(ArrayList<MyTasksItemBean> data, boolean haveNextPage, int page);

        /**
         * 下载异常回调
         */

        void downloadError();


        /**
         * 任务完成回调
         */
        void taskFinishSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 下载数据
         *
         * @param id   库id
         * @param page
         * @param type 我的完成  还是全部完成
         */
        void downloadData(String id, String type, int page);

        /**
         * 完成任务接口
         *
         * @param id 任务id
         */
        void taskFinish(String id);
    }
}
