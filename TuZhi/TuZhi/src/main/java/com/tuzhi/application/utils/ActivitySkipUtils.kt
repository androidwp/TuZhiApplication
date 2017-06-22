package com.tuzhi.application.utils

import android.content.Context
import android.content.Intent

/**
 * Created by wangpeng on 2017/5/31.
 */

fun <T> toActivity(context: Context, clazz: Class<T>) {
    context.startActivity(Intent(context, clazz))
}

fun <T> toActivity(context: Context, clazz: Class<T>, key: String, value: String) {
    context.startActivity(Intent(context, clazz).putExtra(key, value))
}
