package com.tuzhi.application.moudle.memberlist;

import com.tuzhi.application.bean.ItemBean;
import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 *
 * @author wangpeng
 */

public class MemberListContract {
    interface View extends BaseView {
        /**
         * 下载完成回调
         *
         * @param httpData
         */
        void downloadSuccess(ArrayList<ItemBean> httpData);

        /**
         * 设置用户权限
         *
         * @param permissions
         */
        void setPermissions(int permissions);

        /**
         * 提交回调
         */
        void commitSuccess();


    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 下载成员信息
         *
         * @param type
         * @param lid
         */
        void downloadData(String type, String lid);

        /**
         * 提交
         *
         * @param type
         * @param id
         * @param data
         */
        void commit(String type, String id, String data);
    }
}
