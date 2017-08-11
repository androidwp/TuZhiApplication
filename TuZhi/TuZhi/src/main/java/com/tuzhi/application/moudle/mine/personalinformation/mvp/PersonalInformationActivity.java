package com.tuzhi.application.moudle.mine.personalinformation.mvp;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.tuzhi.application.R;
import com.tuzhi.application.bean.HttpInitBean;
import com.tuzhi.application.databinding.ActivityPersonalInformationBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.mine.personalinformation.bindingphoneoremailfirst.mvp.BindingPhoneOrEmailFirstActivity;
import com.tuzhi.application.moudle.mine.personalinformation.rename.mvp.RenameActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.utils.CommonUtils;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.ImageUtils;
import com.tuzhi.application.utils.UserInfoUtils;
import com.tuzhi.application.view.ActionSheet;
import com.yanzhenjie.album.Album;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PersonalInformationActivity extends MVPBaseActivity<PersonalInformationContract.View, PersonalInformationPresenter> implements PersonalInformationContract.View, ActionSheet.ActionSheetListener {
    private ActionSheet actionSheet;
    private ActivityPersonalInformationBinding binding;
    private HttpInitBean httpUserBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        if (TextUtils.equals(event, ConstantKt.getUPDATE_USER_INFO_EVENT())) {
            HttpInitBean userInfo = UserInfoUtils.getUserInfo(this);
            binding.setData(userInfo);
            ImageUtils.loadImage(binding.riv, userInfo.getUserImage(), CommonUtils.getDrawable(this, R.drawable.defaulthead));
        }
    }

    @Override
    protected void init(ViewDataBinding viewDataBinding) {
        binding = (ActivityPersonalInformationBinding) viewDataBinding;
        httpUserBean = UserInfoUtils.getUserInfo(this);
        binding.setData(httpUserBean);
        binding.setActivity(this);
    }

    public void back() {
        onBackPressed();
    }

    public void openSelectDialog() {
        actionSheet = ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("从相册选择", "拍摄新的照片")
                .setCancelableOnTouchOutside(true)
                .setListener(this).show();
    }


    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
        actionSheet.dismiss();
    }


    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        if (index == 0) {
            //打开单图
            Album.albumRadio(this)
                    .title("图库") // 配置title。
                    .columnCount(3) // 相册展示列数，默认是2列。
                    .camera(false) // 是否有拍照功能。
                    .start(999); // 999是请求码，返回时onActivityResult()的第一个参数。
        } else {
            //打开相机
            Album.camera(this).start(999);
        }
        actionSheet.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999) {
            if (resultCode == RESULT_OK) { // Successfully.
                // 这里的List的size肯定是1。
                List<String> pathList = Album.parseResult(data); // Parse path.
                File file = new File(pathList.get(0));
                mPresenter.uploadImage(binding.riv, file.getName(), file);
            }
        }
    }


    public void skipBindPhone() {
        ActivitySkipUtilsKt.toActivity(this, BindingPhoneOrEmailFirstActivity.class, BindingPhoneOrEmailFirstActivity.TYPE, BindingPhoneOrEmailFirstActivity.PHONE);
    }


    public void skipBindEmail() {
        ActivitySkipUtilsKt.toActivity(this, BindingPhoneOrEmailFirstActivity.class, BindingPhoneOrEmailFirstActivity.TYPE, BindingPhoneOrEmailFirstActivity.EMAIL);
    }

    public void skipRename(String name) {
        ActivitySkipUtilsKt.toActivity(this, RenameActivity.class, RenameActivity.NAME, name);
    }


    @Override
    public void onBackPressed() {
        if (actionSheet != null && !actionSheet.ismDismissed()) {
            actionSheet.dismiss();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void uploadFinish(String imageUrl) {
        UserInfoUtils.changeUserInfo(this, "userImage", imageUrl);
        EventBus.getDefault().post(ConstantKt.getUPDATE_USER_INFO_EVENT());
    }
}
