package com.tuzhi.application.moudle.message.mvp;


import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.FragmentMessageBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MessageFragment extends MVPBaseFragment<MessageContract.View, MessagePresenter> implements MessageContract.View {

    private List<Fragment> fragmentList=new ArrayList<>();

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        FragmentMessageBinding binding = (FragmentMessageBinding) viewDataBinding;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    class MyFragmentAdapter extends FragmentPagerAdapter{

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }
}
