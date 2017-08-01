package com.tuzhi.application.moudle.message.mvp;


import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.EventBusBean;
import com.tuzhi.application.databinding.FragmentMessageBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseFragment;
import com.tuzhi.application.moudle.message.read.mvp.ReadFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MessageFragment extends MVPBaseFragment<MessageContract.View, MessagePresenter> implements MessageContract.View {

    public static final String NAME = "MessageFragment";

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private FragmentMessageBinding binding;

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        //监听为了获取未读消息个数
        EventBus.getDefault().register(this);
        binding = (FragmentMessageBinding) viewDataBinding;
        fragmentList.add(getFragment(ReadFragment.TYPE_READ));
        fragmentList.add(getFragment(ReadFragment.TYPE_UNREAD));
        binding.vp.setAdapter(new MyFragmentAdapter(getChildFragmentManager()));
    }

    private Fragment getFragment(String type) {
        ReadFragment readFragment = new ReadFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ReadFragment.TYPE, type);
        readFragment.setArguments(bundle);
        return readFragment;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBusMain(EventBusBean busBean) {
        if (TextUtils.equals(busBean.getName(), NAME)) {
            titleList.add("未读(" + busBean.getiContent() + ")");
            titleList.add("已读");
            binding.stl.setViewPager(binding.vp);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    class MyFragmentAdapter extends FragmentPagerAdapter {

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
