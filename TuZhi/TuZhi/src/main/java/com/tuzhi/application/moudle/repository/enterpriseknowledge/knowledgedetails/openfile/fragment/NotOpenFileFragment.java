package com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.openfile.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.FragmentNotOpenFileBinding;
import com.tuzhi.application.moudle.basemvp.BaseFragment;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.openfile.bean.NotOpenFileBean;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.openfile.mvp.OpenFileActivity;
import com.tuzhi.application.utils.FileUtils;
import com.tuzhi.application.utils.ImageUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

/**
 * Created by wangpeng on 2017/6/23.
 */

public class NotOpenFileFragment extends BaseFragment {

    private NotOpenFileBean bean;
    private FragmentNotOpenFileBinding binding;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_not_open_file;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMain(String text) {
        if (text.equals(FileUtils.DOWNLOAD_FINISH)) {
            bean.setDownload(true);
        }
    }

    public void downloadOrOpenFile(NotOpenFileBean bean) {
        if (bean.isDownload()) {
            String file = FileUtils.getFile(getActivity(), bean.getFileId());
            FileUtils.openFile(getActivity(), new File(file), bean.getFileSffix());
        } else {
            OpenFileActivity activity = (OpenFileActivity) getActivity();
            activity.downloadFile(0);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.bind(view);
        EventBus.getDefault().register(this);
        bean = new NotOpenFileBean();
        String fileId = getArguments().getString(OpenFileActivity.FILE_ID);
        String fileName = getArguments().getString(OpenFileActivity.FILE_NAME);
        bean.setFileId(fileId);
        bean.setFileName(fileName + "." + getArguments().getString(OpenFileActivity.FILE_SUFFIX));
        bean.setFileSffix(getArguments().getString(OpenFileActivity.FILE_SUFFIX));
        bean.setFileSize(getArguments().getString(OpenFileActivity.FILE_SIZE));
        bean.setFileUrl(getArguments().getString(OpenFileActivity.FILE_URL));
        bean.setDownload(FileUtils.fileExist(getActivity(), fileId));
        int fileImage = ImageUtils.getFileImage(getArguments().getString(OpenFileActivity.FILE_SUFFIX), 1);
        binding.setData(bean);
        binding.setFragment(this);
        binding.iv.setImageResource(fileImage);
    }

    public void setFileName(String fileName) {
        bean.setFileName(fileName);
    }


}
