package com.tuzhi.application.moudle.choosetemplatechannel;

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

public class ChooseTemplateChannelContract {
    interface View extends BaseView {
        /**
         * 下载完成回调
         *
         * @param httpData
         */
        void downloadSuccess(ArrayList<ItemBean> httpData);

        /**
         * 知识库模板创建完成回调
         */
        void createLibSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 获取知识库频道模版
         *
         * @param id 知识库id
         */
        void downloadData(String id);

        /**
         * 创建知识库模版
         *
         * @param id  知识库id
         * @param ids 模版频道的多个id 用都好拼接
         */

        void createLib(String id, String ids);
    }
}
