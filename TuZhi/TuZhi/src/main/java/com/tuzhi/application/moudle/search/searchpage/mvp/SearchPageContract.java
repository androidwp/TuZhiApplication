package com.tuzhi.application.moudle.search.searchpage.mvp;

import com.tuzhi.application.moudle.basemvp.BasePresenter;
import com.tuzhi.application.moudle.basemvp.BaseView;
import com.tuzhi.application.moudle.search.searchpage.bean.SearchResultListBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SearchPageContract {
    interface View extends BaseView {
        void downloadFinish(ArrayList<SearchResultListBean> data, boolean haveNextPage, int page);
    }

    interface Presenter extends BasePresenter<View> {
        void downloadData(String type, int page);
    }
}
