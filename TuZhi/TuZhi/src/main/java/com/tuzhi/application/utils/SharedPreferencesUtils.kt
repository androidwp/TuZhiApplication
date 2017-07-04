package com.tuzhi.application.utils

import android.content.Context
import android.content.Context.MODE_APPEND

/**
 * Created by wangpeng on 2017/5/19.
 */

val longCache = "longCache"
val shortCache = "shortCache"
val fileCache = "fileCache"

//不允许清除缓存的数据
fun saveLongCache(context: Context, key: String, value: String?) {
    if (value != null)
        context.getSharedPreferences(longCache, MODE_APPEND).edit().putString(key, value).apply()
}

fun getLongCache(context: Context, key: String): String {
    return context.getSharedPreferences(longCache, MODE_APPEND).getString(key, "")
}

//保存文件地址
fun saveFileCache(context: Context, key: String, value: String?) {
    if (value != null)
        context.getSharedPreferences(fileCache, MODE_APPEND).edit().putString(key, value).apply()
}

//得到文件地址
fun getFileCache(context: Context, key: String): String {
    return context.getSharedPreferences(fileCache, MODE_APPEND).getString(key, "")
}

fun deleteFileCache(context: Context) {
    context.getSharedPreferences(fileCache, MODE_APPEND).edit().clear().commit()
}

//可清除缓存的数据
fun saveShortCache(context: Context, key: String, value: String) {
    context.getSharedPreferences(shortCache, MODE_APPEND).edit().putString(key, value).apply()
}

fun getShortCache(context: Context, key: String): String {
    return context.getSharedPreferences(shortCache, MODE_APPEND).getString(key, "")
}