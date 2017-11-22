package com.tuzhi.application.moudle.search.searchpage.item;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ItemSearchPageNoteFileBinding;
import com.tuzhi.application.item.BaseItem;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.mvp.KnowledgeDetailsActivity;
import com.tuzhi.application.moudle.repository.enterpriseknowledge.knowledgedetails.openfile.mvp.OpenFileActivity;
import com.tuzhi.application.moudle.search.searchpage.bean.SearchResultListBean;
import com.tuzhi.application.utils.ImageUtils;
import com.tuzhi.application.utils.ToastUtilsKt;

/**
 * Created by wangpeng on 2017/8/2.
 */

public class SearchPageNoteItem extends BaseItem<SearchResultListBean> {

    public static final String TYPE = "SearchPageNoteItem";

    private ItemSearchPageNoteFileBinding binding;
    //0是文件1是笔记
    private int resultType;

    @Override
    public void bindView(View view) {
        binding = DataBindingUtil.bind(view);
        binding.setItem(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_search_page_note_file;
    }

    @Override
    public void handleData(SearchResultListBean searchResultListBean, int i) {
        binding.setData(searchResultListBean);
        binding.executePendingBindings();
        resultType = searchResultListBean.getResultType();
        if (resultType == 0) {
            binding.iv.setImageResource(ImageUtils.getFileImage(searchResultListBean.getFileType(), 0));
        } else {
            binding.iv.setImageResource(R.drawable.note);
        }
    }

    public void skipActivity(SearchResultListBean bean) {
        if (bean.isLimit()){
            if (resultType==0){
                Intent intent = new Intent(getContext(), OpenFileActivity.class);
                intent.putExtra(OpenFileActivity.ARTICLE_ID, bean.getAid());
                intent.putExtra(OpenFileActivity.FILE_ID, bean.getFileId());
                intent.putExtra(OpenFileActivity.FILE_PREVIEW_URLS, bean.getPreviewUrls());
                intent.putExtra(OpenFileActivity.FILE_NAME, bean.getFileName());
                intent.putExtra(OpenFileActivity.TYPE, bean.isFileStatus());
                intent.putExtra(OpenFileActivity.FILE_SIZE, bean.getFileSize());
                intent.putExtra(OpenFileActivity.FILE_SUFFIX, bean.getFileType());
                context.startActivity(intent);
            }else{
                Intent intent = new Intent(context, KnowledgeDetailsActivity.class);
                intent.putExtra(KnowledgeDetailsActivity.ID, bean.getAid());
                context.startActivity(intent);
            }
        }else{
            ToastUtilsKt.toast(context,"无查看权限");
        }

    }
}
