package com.tuzhi.application.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.tuzhi.application.dialog.ProgressBarDialog;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

/**
 * Created by wangpeng on 2017/6/23.
 */

public class FileUtils {
    public static final String DOWNLOAD_FINISH = "DOWNLOAD_FINISH";

    public static void downloadFile(final Context context, final String id, final String fileName, String url) {

        final ProgressBarDialog progressBarDialog = new ProgressBarDialog(context);
        progressBarDialog.setTitle("正在下载");
        progressBarDialog.show();
        FileDownloader.setup(context);
        final String path = ConstantKt.getFileCache(fileName).getAbsolutePath();
        FileDownloader.getImpl().create(url).setPath(path).setListener(new FileDownloadListener() {
            @Override
            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

            }

            @Override
            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                progressBarDialog.changeProgress(soFarBytes, totalBytes);
            }

            @Override
            protected void completed(BaseDownloadTask task) {
                progressBarDialog.dismiss();
                SharedPreferencesUtilsKt.saveLongCache(context, id, path);
                EventBus.getDefault().post(DOWNLOAD_FINISH);
            }

            @Override
            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

            }

            @Override
            protected void error(BaseDownloadTask task, Throwable e) {

            }

            @Override
            protected void warn(BaseDownloadTask task) {

            }
        }).start();
    }


    public static boolean fileExist(Context context, String id) {
        String filePath = SharedPreferencesUtilsKt.getLongCache(context, id);
        return !TextUtils.isEmpty(filePath);
    }

    public static String getFile(Context context, String id) {
        return SharedPreferencesUtilsKt.getLongCache(context, id);
    }

    public static void openFile(Context context, File file) {
        try {
            String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase();
            String fileType = getFileType(end);
            if (TextUtils.isEmpty(fileType))
                return;
            Uri uri;
            if (Build.VERSION.SDK_INT >= 24) {
                uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
            } else {
                uri = Uri.fromFile(file);
            }
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(uri, fileType);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getFileType(String fileType) {
        if ("gif/jpg/png/jpeg".contains(fileType)) {
            return "image/*";
        } else if ("doc/docx".contains(fileType)) {
            return "application/msword";
        } else if ("ppttx/ppt".contains(fileType)) {
            return "application/vnd.ms-powerpoint";
        } else if ("xls/xlsx".contains(fileType)) {
            return "application/*";
        } else if ("pdf".contains(fileType)) {
            return "application/pdf";
        } else if ("html/htm".contains(fileType)) {
            return "text/html";
        } else if ("txt".contains(fileType)) {
            return "text/plain";
        } else if ("mp4/avi/rmvb/mkv".contains(fileType)) {
            return "video/*";
        } else if ("zip/rar/7z/cab/jar/iso/tar".contains(fileType)) {
            return "application/*";
        } else {
            return null;
        }
    }


}
