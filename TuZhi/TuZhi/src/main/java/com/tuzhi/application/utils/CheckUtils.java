package com.tuzhi.application.utils;

import android.content.Context;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangpeng on 2017/7/10.
 */

public class CheckUtils {

    //判断email格式是否正确
    public static boolean emailCheck(Context context, String email) {
        if (TextUtils.isEmpty(email)) {
            ToastUtilsKt.toast(context, "邮箱不能为空");
            return false;
        }
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            ToastUtilsKt.toast(context, "邮箱格式不正确，请输入正确邮箱");
            return false;
        }
        return true;
    }

    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     *
     * @param mobile 移动、联通、电信运营商的号码段
     *               <p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     *               、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
     *               <p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
     *               <p>电信的号段：133、153、180（未启用）、189</p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean mobileCheck(Context context, String mobile) {
        if (TextUtils.isEmpty(mobile)) {
            ToastUtilsKt.toast(context, "手机号不能为空");
            return false;
        }
        String regex = "(\\+\\d+)?1[34578]\\d{9}$";
        if (!mobile.matches(regex)) {
            ToastUtilsKt.toast(context, "手机号格式不正确，请输入正确手机号");
            return false;
        }
        return true;
    }
}
