package com.tuzhi.application.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import com.tuzhi.application.dialog.ProgressBarDialog;
import com.tuzhi.application.inter.ProgressListener;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wangpeng on 2017/6/23.
 */

public class FileUtils {
    public static final String DOWNLOAD_FINISH = "DOWNLOAD_FINISH";

    public static void downloadFile(final Context context, final String id, final String fileName, String url) {

        final ProgressBarDialog progressBarDialog = new ProgressBarDialog(context);
        progressBarDialog.setTitle("正在下载");

        HttpUtilsKt.downloadFile(url, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                writeFileToDisk(context, id, fileName, response.body());
                EventBus.getDefault().post(DOWNLOAD_FINISH);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        }, new ProgressListener() {
            @Override
            public void onProgress(long progress, long total, boolean done) {
                if (!done) {
                    progressBarDialog.changeProgress((int) progress, (int) total);
                } else {
                    progressBarDialog.dismiss();
                }
            }
        });
    }

    private static File writeFileToDisk(Context context, String id, String name, ResponseBody body) {
        try {
            File futureStudioIconFile = ConstantKt.getFileCache(name);
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                }
                outputStream.flush();
                //存储文件id和地址
                SharedPreferencesUtilsKt.saveLongCache(context, id, futureStudioIconFile.getAbsolutePath());
                return futureStudioIconFile;
            } catch (IOException e) {
                return null;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return null;
        }
    }

    public static boolean fileExist(Context context, String id) {
        String filePath = SharedPreferencesUtilsKt.getLongCache(context, id);
        return !TextUtils.isEmpty(filePath);
    }

    public static String getFile(Context context, String id) {
       return SharedPreferencesUtilsKt.getLongCache(context,id);
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
