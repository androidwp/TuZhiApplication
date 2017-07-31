package com.tuzhi.application.moudle.search.mvp;


import android.databinding.ViewDataBinding;

import com.tuzhi.application.R;
import com.tuzhi.application.moudle.basemvp.MVPBaseFragment;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SearchFragment extends MVPBaseFragment<SearchContract.View, SearchPresenter> implements SearchContract.View {

    @Override
    protected void init(ViewDataBinding viewDataBinding) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }
}
