package com.tuzhi.application.moudle.homepage.mvc;


import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.FragmentHomePageBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseFragment;
import com.tuzhi.application.moudle.dynamiccollaboration.DynamicCollaborationFragment;
import com.tuzhi.application.moudle.repository.mvp.RepositoryFragment;
import com.tuzhi.application.moudle.search.mvp.SearchFragment;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HomePageFragment extends MVPBaseFragment<HomePageContract.View, HomePagePresenter> implements HomePageContract.View {

    private FragmentHomePageBinding binding;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        binding = (FragmentHomePageBinding) viewDataBinding;
        RepositoryFragment repositoryFragment = new RepositoryFragment();
        DynamicCollaborationFragment dynamicCollaborationFragment = new DynamicCollaborationFragment();
        fragmentList.add(repositoryFragment);
        fragmentList.add(dynamicCollaborationFragment);
        titleList.add("知识库");
        titleList.add("协作动态");
        binding.vp.setAdapter(new MyFragmentPageAdapter(getChildFragmentManager()));
        binding.stl.setViewPager(binding.vp);
        binding.setFragment(this);
    }

    public void onSearchClick() {
        ActivitySkipUtilsKt.toActivity(getContext(), SearchFragment.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_page;
    }

    class MyFragmentPageAdapter extends FragmentPagerAdapter {

        public MyFragmentPageAdapter(FragmentManager fm) {
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
