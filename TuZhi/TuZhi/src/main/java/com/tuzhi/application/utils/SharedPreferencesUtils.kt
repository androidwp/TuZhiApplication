package com.tuzhi.application.utils

import android.content.Context
import android.content.Context.MODE_APPEND
import android.text.TextUtils

/**
 * Created by wangpeng on 2017/5/19.
 */

val longCache = "longCache"
val shortCache = "shortCache"
val fileCache = "fileCache"
val searchHistory = "searchHistory"

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
    context.getSharedPreferences(fileCache, MODE_APPEND).edit().clear().apply()
}

//存储历史记录
fun saveSearchHistoryCache(context: Context, historyValue: String) {
    if (!TextUtils.isEmpty(historyValue)) {
        val trim = historyValue.trim()
        val sharedPreferences = context.getSharedPreferences(searchHistory, MODE_APPEND)
        val historyString = sharedPreferences.getString(searchHistory, null)
        if (historyString != null) {
            //将相同的搜索历史去掉。
            val result = historyString.replace((trim + ","), "").replace(" ", "")
            //将最新的搜索内容放到历史记录最上方
            sharedPreferences.edit().putString(searchHistory, trim + "," + result).apply()
        } else {
            //如果没有历史就将内容写入
            sharedPreferences.edit().putString(searchHistory, trim + ",").apply()
        }
    }
}

//获取历史记录
fun getSearchHistoryCache(context: Context): List<String> {
    val sharedPreferences = context.getSharedPreferences(searchHistory, MODE_APPEND)
    val string = sharedPreferences.getString(searchHistory, null)
    if (string == null) {
        return ArrayList()
    } else {
        return string.split(",")
    }
}

//删除历史记录
fun deleteSearchHistoryCache(context: Context) {
    context.getSharedPreferences(searchHistory, MODE_APPEND).edit().clear().apply()
}

//可清除缓存的数据
fun saveShortCache(context: Context, key: String, value: String) {
    context.getSharedPreferences(shortCache, MODE_APPEND).edit().putString(key, value).apply()
}

fun getShortCache(context: Context, key: String): String {
    return context.getSharedPreferences(shortCache, MODE_APPEND).getString(key, "")
}