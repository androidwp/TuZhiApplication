package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.openfile.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.FragmentOpenFileBinding;
import com.tuzhi.application.moudle.basemvp.BaseFragment;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.openfile.item.OpenFileVpItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.openfile.mvp.OpenFileActivity;

import java.util.ArrayList;

import kale.adapter.CommonPagerAdapter;
import kale.adapter.item.AdapterItem;

/**
 * Created by wangpeng on 2017/6/23.
 */

public class OpenFileFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    private FragmentOpenFileBinding bind;
    private ArrayList<String> images;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_open_file;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.bind(view);
        images = getArguments().getStringArrayList(OpenFileActivity.FILE_PREVIEW_URLS);
        CommonPagerAdapter<String> adapter = new CommonPagerAdapter<String>(images) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                return new OpenFileVpItem();
            }
        };
        bind.vp.setAdapter(adapter);
        bind.vp.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        bind.setPage(position + 1 + " / " + images.size());
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
