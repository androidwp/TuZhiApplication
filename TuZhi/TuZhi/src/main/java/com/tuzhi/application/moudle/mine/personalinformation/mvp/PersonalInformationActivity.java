package com.tuzhi.application.moudle.mine.personalinformation.mvp;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.tuzhi.application.R;
import com.tuzhi.application.bean.HttpInitBean;
import com.tuzhi.application.databinding.ActivityPersonalInformationBinding;
import com.tuzhi.application.moudle.basemvp.MVPBaseActivity;
import com.tuzhi.application.moudle.mine.personalinformation.bindingphoneoremailfirst.mvp.BindingPhoneOrEmailFirstActivity;
import com.tuzhi.application.moudle.mine.personalinformation.rename.mvp.RenameActivity;
import com.tuzhi.application.utils.ActivitySkipUtilsKt;
import com.tuzhi.application.utils.ConstantKt;
import com.tuzhi.application.utils.ImageDealUtilsKt;
import com.tuzhi.application.utils.UserInfoUtils;
import com.tuzhi.application.view.ActionSheet;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PersonalInformationActivity extends MVPBaseActivity<PersonalInformationContract.View, PersonalInformationPresenter> implements PersonalInformationContract.View, ActionSheet.ActionSheetListener, TakePhoto.TakeResultListener, InvokeListener {

    private static final String PORTRAIT_NAME = "portrait.jpg";
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
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
        getTakePhoto().onCreate(savedInstanceState);
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
            binding.setData(UserInfoUtils.getUserInfo(this));
        }

    }

    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        if (index == 0) {
            takePhoto.onPickFromGallery();
        } else {
            takePhoto.onPickFromCapture(Uri.fromFile(ConstantKt.getImageCache(this, PORTRAIT_NAME)));
        }
        actionSheet.dismiss();
    }

    @Override
    public void takeSuccess(TResult result) {
        String originalPath = result.getImage().getOriginalPath();
        final Bitmap bitmap = ImageDealUtilsKt.getBitmap(originalPath, binding.riv.getWidth(), binding.riv.getHeight());
        File file = ImageDealUtilsKt.savePhotoToSDCard(this, bitmap, 100, PORTRAIT_NAME);
        mPresenter.uploadImage(httpUserBean.getNickname(), file);
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }


    public void skipBindPhone() {
        ActivitySkipUtilsKt.toActivity(this, BindingPhoneOrEmailFirstActivity.class, BindingPhoneOrEmailFirstActivity.TYPE, BindingPhoneOrEmailFirstActivity.PHONE);
    }


    public void skipBindEmail() {
        ActivitySkipUtilsKt.toActivity(this, BindingPhoneOrEmailFirstActivity.class, BindingPhoneOrEmailFirstActivity.TYPE, BindingPhoneOrEmailFirstActivity.EMAIL);
    }

    public void skipRename() {
        ActivitySkipUtilsKt.toActivity(this, RenameActivity.class);
    }


    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
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
