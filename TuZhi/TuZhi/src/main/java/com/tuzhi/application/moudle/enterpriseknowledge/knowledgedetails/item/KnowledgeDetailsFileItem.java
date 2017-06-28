package com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.item;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemKnowledgeDetailsFileBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.bean.KnowledgeDetailsListBean;
import com.tuzhi.application.moudle.enterpriseknowledge.knowledgedetails.openfile.mvp.OpenFileActivity;
import com.tuzhi.application.utils.ImageUtils;

import org.jetbrains.annotations.NotNull;

/**
 * Created by wangpeng on 2017/6/12.
 */

public class KnowledgeDetailsFileItem extends BaseItem<KnowledgeDetailsListBean> {

    public static final String TYPE = "KnowledgeDetailsFileItem";

    private ItemKnowledgeDetailsFileBinding binding;

    @Override
    public void bindView(@NotNull View view) {
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_knowledge_details_file;
    }

    @Override
    public void handleData(KnowledgeDetailsListBean knowledgeDetailsListBean, int i) {
        binding.setItem(this);
        binding.setData(knowledgeDetailsListBean);
        binding.executePendingBindings();
        int fileImage = ImageUtils.getFileImage(knowledgeDetailsListBean.getFileType(), 0);
        binding.iv.setImageResource(fileImage);
        boolean downloadStatus = knowledgeDetailsListBean.getDownloadStatus();
        if (downloadStatus) {
            binding.tvDownLoadStatus.setText("已下载");
        } else {
            binding.tvDownLoadStatus.setText("");
        }
    }

    public void skipOpenActivity(KnowledgeDetailsListBean bean) {
        Intent intent = new Intent(getContext(), OpenFileActivity.class);
        intent.putExtra(OpenFileActivity.ARTICLE_ID, bean.getAid());
        intent.putExtra(OpenFileActivity.FILE_ID, bean.getFileId());
        intent.putExtra(OpenFileActivity.FILE_PREVIEW_URLS, bean.getPreviewUrls());
        intent.putExtra(OpenFileActivity.FILE_NAME, bean.getFileName());
        intent.putExtra(OpenFileActivity.TYPE, bean.isFileStatus());
        intent.putExtra(OpenFileActivity.FILE_SIZE, bean.getFileSize());
        context.startActivity(intent);
    }
}
