package com.tuzhi.application.utils

import android.content.Context
import android.os.Environment
import java.io.File

/**
 * Created by wangpeng on 2017/6/5.
 */

val KILL_ACTIVITY_CODE = 110

val CREATE_CODE = 120

val NEED_REFRESH_CODE = 100

val Key_AllowMobileInternetDownload = "Key_AllowMobileInternetDownload"

val Key_ChangePersionalInfo = "Key_ChangePersonalInfo"

val Value_false = "0"

val Value_true = "1"

val VERSION_NAME = "VERSION_NAME"
val VERSION_CODE = "VERSION_CODE"
val UMENG_CHANNEL = "UMENG_CHANNEL"
val INSTALL_UID = "INSTALL_UID"
val IMSI = "IMSI"
val IMEI = "IMEI"
val LOGIN_INFO = "LOGIN_INFO"
val USER_TYPE = "USER_TYPE"
val USER_ID = "USER_ID"
val LOGIN_STATUS = "LOGIN_STATUS"
val IMAGE_HEAD = "IMAGE_HEAD"

//手机型号
val MODEL = android.os.Build.MODEL

//手机品牌
val BRAND = android.os.Build.BRAND

//系统型号
val OSVERSION = android.os.Build.VERSION.RELEASE

fun getImageCache(context: Context, fileName: String): File {

    return File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
}