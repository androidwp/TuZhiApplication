package com.tuzhi.application.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import java.io.FileOutputStream
import java.io.IOException

/**
 * Created by wangpeng on 2017/6/5.
 */


fun savePhotoToSDCard(context: Context, photoBitmap: Bitmap, quality: Int, photoName: String) {

    val dir = getImageCache(context, photoName)
    if (!dir.exists()) {
        dir.mkdirs()
    }

    val photoFile = getImageCache(context, photoName.replace("/".toRegex(), ""))
    var fileOutputStream: FileOutputStream? = null
    try {
        fileOutputStream = FileOutputStream(photoFile)

        if (photoBitmap.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream)) {
            fileOutputStream.flush()
        }

    } catch (e: IOException) {
        photoFile.delete()
        e.printStackTrace()
    } finally {
        try {
            if (fileOutputStream != null)
                fileOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

}

//对图片进行裁剪
fun getBitmap(path: String, reqWidth: Int, reqHeight: Int): Bitmap {
    val factoryOptions = BitmapFactory.Options()

    factoryOptions.inJustDecodeBounds = true
    BitmapFactory.decodeFile(path, factoryOptions)

    // Determine how much to scale down the image
    val scaleFactor = calculateInSampleSize(factoryOptions, reqWidth, reqHeight)

    // Decode the image file into a Bitmap sized to fill the
    // View
    factoryOptions.inJustDecodeBounds = false
    factoryOptions.inSampleSize = scaleFactor
    factoryOptions.inPreferredConfig = Bitmap.Config.RGB_565

    val bitmapDegree = getBitmapDegree(path)
    val bitmap = BitmapFactory.decodeFile(path, factoryOptions)
    if (bitmapDegree != 0) {
        return rotateBitmapByDegree(bitmap, bitmapDegree)
    }
    return bitmap
}


//计算图片的缩放值
fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
    val height = options.outHeight
    val width = options.outWidth
    var inSampleSize = 1

    if (height > reqHeight || width > reqWidth) {
        val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
        val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())
        //保证图片品质取小值
        inSampleSize = if (heightRatio > widthRatio) widthRatio else heightRatio
    }
    return inSampleSize
}


/**
 * 读取图片的旋转的角度

 * @param path 图片绝对路径
 * *
 * @return 图片的旋转角度
 */
fun getBitmapDegree(path: String): Int {
    var degree = 0
    try {
        // 从指定路径下读取图片，并获取其EXIF信息
        val exifInterface = ExifInterface(path)
        // 获取图片的旋转信息
        val orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL)
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90
            ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180
            ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return degree
}


/**
 * 将图片按照某个角度进行旋转

 * @param bm     需要旋转的图片
 * *
 * @param degree 旋转角度
 * *
 * @return 旋转后的图片
 */
fun rotateBitmapByDegree(bm: Bitmap, degree: Int): Bitmap {
    var returnBm: Bitmap? = null

    // 根据旋转角度，生成旋转矩阵
    val matrix = Matrix()
    matrix.postRotate(degree.toFloat())
    try {
        // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
        returnBm = Bitmap.createBitmap(bm, 0, 0, bm.width, bm.height, matrix, true)
    } catch (ignored: OutOfMemoryError) {
    }

    if (returnBm == null) {
        returnBm = bm
    }
    if (bm != returnBm) {
        bm.recycle()
    }
    return returnBm
}

