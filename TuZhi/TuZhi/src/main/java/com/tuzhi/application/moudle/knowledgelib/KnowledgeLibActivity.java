package com.tuzhi.application.moudle.knowledgelib;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.EditText;

import com.tuzhi.application.R;
import com.tuzhi.application.databinding.ActivityLibBinding;
import com.tuzhi.application.dialog.DeleteDialog;
import com.tuzhi.application.dialog.RenameDialog;
import com.tuzhi.application.inter.OnDialogClickListener;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.createknowledgelib.CreateKnowledgeLibActivity;
import com.tuzhi.application.moudle.knowledgelibtask.KnowledgeLibTaskFragment;
import com.tuzhi.application.moudle.memberlist.MemberListActivity;
import com.tuzhi.application.moudle.repository.knowledgachannel.bean.KnowledgeChannelHttpBean;
import com.tuzhi.application.moudle.repository.knowledgachannel.mvp.KnowledgeChannelActivity;
import com.tuzhi.application.moudle.repository.mvp.RepositoryFragment;
import com.tuzhi.application.utils.KeyBoardUtils;
import com.tuzhi.application.utils.ToastUtilsKt;
import com.tuzhi.application.view.ActionSheet;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识库详情页   显示频道
 *
 * @author wangpeng
 */

public class KnowledgeLibActivity extends MVPBaseActivity<KnowledgeLibContract.View, KnowledgeLibPresenter> implements KnowledgeLibContract.View, ActionSheet.ActionSheetListener, OnDialogClickListener {
    public static String ID = "ID";
    public static String TITLE = "TITLE";
    private List<String> titles = new ArrayList<>();
    private ActionSheet actionSheet;
    private List<Fragment> fragments = new ArrayList<>();
    private DeleteDialog deleteDialog;
    private RenameDialog renameDialog;
    private ActivityLibBinding binding;
    private int setCommonLib = 0;
    private int setLib = 1;
    private int rename = 2;
    private int delete = 3;
    private String id;
    private String title;
    private KnowledgeChannelHttpBean.KnowledgeLibMapBean knowledgeLibMap;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lib;
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        binding = (ActivityLibBinding) viewDataBinding;
        binding.setActivity(this);
        id = getIntent().getStringExtra(ID);
        title = getIntent().getStringExtra(TITLE);
        binding.tvTitle.setText(title);
        titles.add("知识");
        titles.add("任务");
        fragments.add(getKnowledgeLibFragment());
        fragments.add(getKnowledgeLibTaskFragment());
        binding.vp.setAdapter(new MyFragmentPageAdapter(getSupportFragmentManager()));
        binding.stl.setViewPager(binding.vp);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMain(KnowledgeChannelHttpBean.KnowledgeLibMapBean knowledgeLibMap) {
        this.knowledgeLibMap = knowledgeLibMap;
        binding.setOpen(knowledgeLibMap.isIsOpen());
    }

    private Fragment getKnowledgeLibTaskFragment() {
        KnowledgeLibTaskFragment taskFragment = new KnowledgeLibTaskFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KnowledgeLibTaskFragment.ID, id);
        bundle.putString(KnowledgeLibTaskFragment.TITLE, title);
        taskFragment.setArguments(bundle);
        return taskFragment;
    }

    private Fragment getKnowledgeLibFragment() {
        KnowledgeChannelActivity taskFragment = new KnowledgeChannelActivity();
        Bundle bundle = new Bundle();
        bundle.putString(KnowledgeChannelActivity.ID, id);
        bundle.putString(KnowledgeChannelActivity.TITLE, title);
        taskFragment.setArguments(bundle);
        return taskFragment;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void back() {
        onBackPressed();
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
        actionSheet.dismiss();
    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        if (index == setCommonLib) {
            if (knowledgeLibMap.isIsCollection()) {
                mPresenter.cancelCollectionLib(id);
            } else {
                mPresenter.collectionLib(id);
            }
        } else if (index == setLib) {
            if (knowledgeLibMap != null) {
                Intent intent = new Intent(this, CreateKnowledgeLibActivity.class);
                intent.putExtra(CreateKnowledgeLibActivity.TYPE, CreateKnowledgeLibActivity.TYPE_SET);
                intent.putExtra(CreateKnowledgeLibActivity.ID, id);
                intent.putExtra(CreateKnowledgeLibActivity.TITLE, title);
                intent.putExtra(CreateKnowledgeLibActivity.OPENNESS, knowledgeLibMap.isIsOpen());
                intent.putExtra(CreateKnowledgeLibActivity.CLASSIFICATION, knowledgeLibMap.getType());
                intent.putExtra(CreateKnowledgeLibActivity.IMAGE, knowledgeLibMap.getCoverImage());
                startActivity(intent);
            }
        } else if (index == rename) {
            renameDialog = new RenameDialog(getContext(), R.style.dialog);
            renameDialog.setView(new EditText(getContext()));
            renameDialog.setText(title);
            renameDialog.setClickListener(this);
            renameDialog.show();
            KeyBoardUtils.showKeyBoard(getContext());
        } else if (index == delete) {
            deleteDialog = new DeleteDialog(getContext(), R.style.dialog);
            deleteDialog.setText("你确定要删除该知识库吗？删除后将无法恢复。");
            deleteDialog.setClickListener(this);
            deleteDialog.show();
        }
        actionSheet.dismiss();
    }

    @Override
    public void onBackPressed() {
        if (actionSheet != null && !actionSheet.ismDismissed()) {
            actionSheet.dismiss();
        } else {
            super.onBackPressed();
        }
    }

    public void skipMemberManagementActivity() {
        Intent intent = new Intent(this, MemberListActivity.class);
        intent.putExtra(MemberListActivity.ID, id);
        intent.putExtra(MemberListActivity.TYPE, MemberListActivity.TYPE_LIB);
        startActivity(intent);
    }

    public void openMenu() {
        if (knowledgeLibMap != null) {
            actionSheet = ActionSheet.createBuilder(getContext(), getSupportFragmentManager())
                    .setCancelButtonTitle("取消")
                    .setOtherButtonTitles(knowledgeLibMap.isIsCollection() ? "删除常用知识库" : "设为常用知识库", "设置知识库", "重命名知识库", "删除知识库")
                    .setCancelableOnTouchOutside(true)
                    .setListener(this).show();
        }
    }


    @Override
    public void onDialogClick(View view) {
        switch (view.getId()) {
            case R.id.tvDelete:
                mPresenter.deleteLib(id);
                break;
            case R.id.tvRename:
                mPresenter.rename(id, (String) view.getTag());
                break;
            default:
                break;
        }
    }

    @Override
    public void deleteLibSuccess() {
        deleteDialog.dismiss();
        back();
        ToastUtilsKt.toast(getContext(), "删除成功");
        //通知刷新
        EventBus.getDefault().post(RepositoryFragment.MESSAGE);
    }

    @Override
    public void renameSuccess(String name) {
        renameDialog.dismiss();
        ToastUtilsKt.toast(getContext(), "修改成功");
        setTitle(name);
        EventBus.getDefault().post(RepositoryFragment.MESSAGE);
    }

    @Override
    public void collectionLibSuccess() {
        ToastUtilsKt.toast(this, "设置成功");
        EventBus.getDefault().post(KnowledgeChannelActivity.MESSAGE);
        EventBus.getDefault().post(RepositoryFragment.MESSAGE);
    }

    class MyFragmentPageAdapter extends FragmentPagerAdapter {

        public MyFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
