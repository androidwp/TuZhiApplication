package com.tuzhi.application.utils

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import com.alibaba.fastjson.JSONObject.parseObject
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
import java.io.File
import java.util.*


/**
 * Created by wangpeng on 2017/5/18.
 */
private val URL_IMAGE = "http://192.168.0.132:8082/upload.htm"
//private val URL_IMAGE = "http://192.168.0.140:8081/upload.htm"

var baseUrl = "http://192.168.0.109:9001/"

val retrofit: Http by lazy {
    Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(ScalarsConverterFactory.create()).build().create(Http::class.java)
}

interface Http {
    @GET
    fun get(@Url url: String, @QueryMap parameter: WeakHashMap<String, String>): Call<String>

    @FormUrlEncoded
    @POST
    fun post(@Url url: String, @FieldMap parameter: WeakHashMap<String, String>): Call<String>

    @Multipart
    @POST
    fun updateImage(@Url url: String, @Part parts: Array<MultipartBody.Part?>, @QueryMap maps: WeakHashMap<String, String>): Call<String>
}

interface HttpCallBack<in T> {

    fun onFinish()

    fun onSuccess(t: T?, text: String)

    fun onFailure(text: String)

}

fun <T> uploadFile(context: Context, url: String, parts: Array<MultipartBody.Part?>, maps: WeakHashMap<String, String>, callBack: HttpCallBack<T>) {
    retrofit.updateImage(url, parts, maps).enqueue(object : Callback<String> {
        override fun onFailure(call: Call<String>?, t: Throwable?) {
            onFailure(context, callBack, t)
        }

        override fun onResponse(call: Call<String>?, response: Response<String>) {
            onResponse(context, null, callBack, response)
        }

    })
}

fun <T> get(context: Context, url: String, parameter: WeakHashMap<String, String>, clazz: Class<T>?, callBack: HttpCallBack<T>) {
    retrofit.get(url, parameter).enqueue(object : Callback<String> {
        override fun onFailure(call: Call<String>?, t: Throwable?) {
            onFailure(context, callBack, t)
        }

        override fun onResponse(call: Call<String>?, response: Response<String>) {
            onResponse(context, clazz, callBack, response)
        }

    })
}

fun <T> post(context: Context, url: String, parameter: WeakHashMap<String, String>, clazz: Class<T>?, callBack: HttpCallBack<T>) {
    retrofit.post(url, parameter).enqueue(object : Callback<String> {
        override fun onFailure(call: Call<String>?, t: Throwable?) {
            onFailure(context, callBack, t)
        }

        override fun onResponse(call: Call<String>?, response: Response<String>) {
            onResponse(context, clazz, callBack, response)
        }

    })
}


fun <T> onFailure(context: Context, callBack: HttpCallBack<T>, t: Throwable?) {
    val activity = context as AppCompatActivity
    if (!activity.isDestroyed) {
        callBack.onFinish()
        callBack.onFailure(t.toString())
        toast(activity, "请检查您的网络")
    }
}

fun <T> onResponse(context: Context, clazz: Class<T>?, callBack: HttpCallBack<T>, response: Response<String>) {
    val activity = context as AppCompatActivity
    if (!activity.isDestroyed) {
        callBack.onFinish()
        val result = response.body().toString()
        val jsonObject = parseObject(result)
        val resultCode = jsonObject.getString("resultCode")
        val resultMsg = jsonObject.getString("resultMsg")
        if (TextUtils.equals(resultCode, "0")) {
            val resultObject = parseObject(result, clazz)
            if (clazz != null) {
                callBack.onSuccess(resultObject, result)
            } else {
                callBack.onSuccess(null, result)
            }
        } else {
            toast(activity, resultMsg)
            callBack.onFailure(resultMsg)
        }
    }
}

//上传单张图片
fun uploadImage(context: Context, type: String, file: File, callBack: HttpCallBack<String>) {
    val files = arrayOfNulls<MultipartBody.Part>(1)
    files[0] = MultipartBody.Part.createFormData("file", file.name, RequestBody.create(MediaType.parse("multipart/form-data"), file))
    val parameter = getParameter(context)
    //app=userImage&type=json
    //头像传userImage
    //非头像传tuzhikmMobile
    parameter.put("fileFileName", file.name)
    parameter.put("app", type)
    parameter.put("type", "json")
    uploadFile(context, URL_IMAGE, files, parameter, callBack)
}


fun uploadSummaryImage(context: Context, type: String, view: View, file: File, callBack: HttpCallBack<String>) {
    Thread {
        run {
            try {
                val bitmap = getBitmap(path = file.absolutePath, reqWidth = view.width, reqHeight = view.height)
                val imageFile = savePhotoToSDCard(context = context, photoBitmap = bitmap, quality = 100, photoName = file.name)
                uploadImage(context, type, imageFile, callBack)
            } catch (e: Exception) {
                showLog("TAG", e.localizedMessage)
            }
        }
    }.start()
}

fun getParameter(context: Context): WeakHashMap<String, String> {
    val weakMap = WeakHashMap<String, String>()
    weakMap.put("type", "1")
    weakMap.put("dType", "1")
    weakMap.put("userType", getLongCache(context, USER_TYPE))
    weakMap.put("versionCode", getLongCache(context, VERSION_CODE))
    if (!TextUtils.isEmpty(getLongCache(context, USER_ID)))
        weakMap.put("userId", getLongCache(context, USER_ID))
    return weakMap
}