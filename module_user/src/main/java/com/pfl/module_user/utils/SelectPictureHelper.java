package com.pfl.module_user.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2018\6\7 0007.
 */

public class SelectPictureHelper {

    //相册请求码
    private static final int ALBUM_REQUEST_CODE = 1;
    //相机请求码
    private static final int CAMERA_REQUEST_CODE = 2;
    //剪裁请求码
    private static final int CROP_REQUEST_CODE = 3;
    //调用照相机返回图片文件
    private File tempFile;

    private String fileName = "crop";
    private int tag = -1;

    private Activity mActivity;

    public SelectPictureHelper(Activity activity) {
        this.mActivity = activity;
    }

    /**
     * 从相机获取图片
     */
    public void getPicFromCamera(int resid) {
        getPicFromCamera(fileName, resid);
    }

    /**
     * 从相机获取图片
     * <p>
     * fileName 保存图片名称
     */
    public void getPicFromCamera(String fileName, int resid) {
        this.fileName = fileName;
        this.tag = resid;
        //用于保存调用相机拍照后所生成的文件
        tempFile = new File(Environment.getExternalStorageDirectory().getPath() + "/bishu", System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mActivity, mActivity.getPackageName(), tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        mActivity.startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }


    /**
     * 从相册获取图片
     */
    public void getPicFromAlbm(int resid) {
        getPicFromAlbm(fileName, resid);
    }

    /**
     * 从相册获取图片
     * <p>
     * fileName 保存图片名称
     */
    public void getPicFromAlbm(String fileName, int resid) {
        this.fileName = fileName;
        this.tag = resid;
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        mActivity.startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
    }

    /**
     * 裁剪图片
     */
    private void cropPhoto(Uri uri) {
       Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image*//*");
        intent.putExtra(fileName, "true");
        intent.putExtra("aspectX", 800);
        intent.putExtra("aspectY", 500);
        intent.putExtra("outputX", 360);
        intent.putExtra("outputY", 225);
        intent.putExtra("return-data", true);
        mActivity.startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        switch (requestCode) {
            case CAMERA_REQUEST_CODE:   //调用相机后返回
                if (resultCode == RESULT_OK) {
                    //用相机返回的照片去调用剪裁也需要对Uri进行处理
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(mActivity, mActivity.getPackageName(), tempFile);
                        cropPhoto(contentUri);
                    } else {
                        cropPhoto(Uri.fromFile(tempFile));
                    }
                }
                break;
            case ALBUM_REQUEST_CODE:    //调用相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    cropPhoto(uri);
                }
                break;
            case CROP_REQUEST_CODE:     //调用剪裁后返回
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle.getParcelable("data");
                    //设置到ImageView上
                    //mHeader_iv.setImageBitmap(image);
                    //也可以进行一些保存、压缩等操作后上传
                    String path = saveImage(fileName, image);
                    if (mOnSelectPictureSuccess != null) {
                        mOnSelectPictureSuccess.onSelected(path, image);
                    }
                }
                break;
        }
    }

    public String saveImage(String name, Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory().getPath() + "/bishu");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = name + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private OnSelectPictureSuccess mOnSelectPictureSuccess;

    public void setOnSelectPictureSuccess(OnSelectPictureSuccess onSelectPictureSuccess) {
        this.mOnSelectPictureSuccess = onSelectPictureSuccess;
    }

    public int getTag() {
        return tag;
    }

    public interface OnSelectPictureSuccess {

        void onSelected(String path, Bitmap bitmap);

    }

}
