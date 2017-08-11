package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.openfile.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class OpenFileContract {
    interface View extends BaseView {
        void deleteSuccess();

        void renameSuccess(String name);
    }

    interface  Presenter extends BasePresenter<View> {

        void renameFile(String id,String name);

        void deleteFile(String id);

        void downloadFile(String aid, String fid, String fileName);

        //后台记录
        void reviewFile(String aid, String fid);
    }
}
