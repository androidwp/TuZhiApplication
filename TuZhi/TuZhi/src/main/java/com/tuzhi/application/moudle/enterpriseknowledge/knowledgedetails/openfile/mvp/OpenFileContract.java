package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.openfile.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class OpenFileContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
        void downloadFile(String aid,String fid,String fileName);

        //后台记录
        void reviewFile(String aid,String fid);
    }
}
