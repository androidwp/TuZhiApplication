package com.tuzhi.application.moudle.knowledgelib;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class KnowledgeLibContract {
    interface View extends BaseView {
        /**
         * 删除成功回调
         */
        void deleteLibSuccess();

        /**
         * 重命名回调
         *
         * @param name
         */

        void renameSuccess(String name);

        /**
         * 收藏知识库回调
         */
        void collectionLibSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 删除数据库
         *
         * @param id
         */
        void deleteLib(String id);

        /**
         * 重命名
         *
         * @param id
         * @param name
         */
        void rename(String id, String name);

        /**
         * 收藏知识库
         *
         * @param id
         */
        void collectionLib(String id);

        /**
         * 取消收藏
         *
         * @param id
         */
        void cancelCollectionLib(String id);
    }
}
