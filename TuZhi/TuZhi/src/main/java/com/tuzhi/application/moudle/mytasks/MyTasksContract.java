package com.tuzhi.application.moudle.mytasks;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyTasksContract {
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
         * 下载失败回调
         */
        void downloadError();

        /**
         * 完成任务回调
         */
        void taskFinishSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 下载数据接口
         *
         * @param page
         */
        void downloadData(int page);

        /**
         * 完成任务接口
         *
         * @param id 任务id
         */
        void taskFinish(String id);
    }
}
