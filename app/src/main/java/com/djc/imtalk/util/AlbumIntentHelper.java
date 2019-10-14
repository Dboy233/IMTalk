package com.djc.imtalk.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;

import static android.app.Activity.RESULT_OK;

public class AlbumIntentHelper {

    private final int RESULT_CODE = 1010;
    private Activity mActivity;
    private Intent mIntent;
    private OnAlbumResultListener mOnAlbumResultListener;


    public AlbumIntentHelper(Activity activity) {
        mActivity = activity;
        mIntent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mIntent.setType("image/*");
    }

    public void startActivityForResult() {
        mActivity.startActivityForResult(mIntent, RESULT_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case RESULT_CODE:
                if (resultCode == RESULT_OK) {
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        //4.4及以上系统使用该方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        //4.4以下系统使用该方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }

                break;
        }
    }

    public void onActivityResult(Intent intent) {
        //判断手机系统版本号
        if (Build.VERSION.SDK_INT >= 19) {
            //4.4及以上系统使用该方法处理图片
            handleImageOnKitKat(intent);
        } else {
            //4.4以下系统使用该方法处理图片
            handleImageBeforeKitKat(intent);
        }
    }

    /**
     * 获取图片返回 api>19
     */
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        Log.d("DDD", "相册选择5" + imagePath);
        requestToListener(imagePath);
    }

    private void requestToListener(String imagePath) {
        if (mOnAlbumResultListener != null) {
            if (imagePath != null) {
                mOnAlbumResultListener.onSuccess(imagePath);
            } else {
                mOnAlbumResultListener.onFiled();
            }
        }
    }

    /**
     * 获取跳转返回的图片
     *
     * @param data
     */
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(mActivity, uri)) {
            //如果是document类型的Uri,则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.document".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        selection);
                Log.d("DDD", "相册选择1" + imagePath);
            } else if ("com.android.providers.downloads.documents".equals
                    (uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse(
                        "content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
                Log.d("DDD", "相册选择2" + imagePath);

            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的Uri，则使用普通方法处理
            imagePath = getImagePath(uri, null);
            Log.d("DDD", "相册选择3" + imagePath);

        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
            Log.d("DDD", "相册选择4" + imagePath);

        }
        requestToListener(imagePath);
    }

    /**
     * 得到从相册选择的image的路径
     */
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //通过uri和selection来获取真实的图片路径
        Cursor cursor = mActivity.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.
                        Media.DATA));
            }
            cursor.close();
        }
        Log.d("DDD", "相册选择 path " + path);
        return path;
    }

    public void setOnAlbumResultListener(OnAlbumResultListener displayImageListener) {
        this.mOnAlbumResultListener = displayImageListener;
    }

    public interface OnAlbumResultListener {
        void onSuccess(String path);

        void onFiled();
    }

}
