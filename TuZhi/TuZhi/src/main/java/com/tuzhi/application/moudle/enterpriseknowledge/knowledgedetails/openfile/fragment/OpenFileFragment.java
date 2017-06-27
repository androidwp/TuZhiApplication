package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.openfile.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.FragmentOpenFileBinding;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.openfile.item.OpenFileVpItem;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.openfile.mvp.OpenFileActivity;

import java.util.ArrayList;

import kale.adapter.CommonPagerAdapter;
import kale.adapter.item.AdapterItem;

/**
 * Created by wangpeng on 2017/6/23.
 */

public class OpenFileFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private FragmentOpenFileBinding bind;
    private ArrayList<String> images;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_file, null);
        bind = DataBindingUtil.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        images = getArguments().getStringArrayList(OpenFileActivity.CONTENT);
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
