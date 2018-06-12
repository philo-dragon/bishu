package com.pfl.module_user.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
    private Uri uritempFile;

    // aspectX aspectY 是宽高的比例
    private int aspectX = 1;
    private int aspectY = 1;
    // outputX,outputY 是剪裁图片的宽高
    private int outputX = 300;
    private int outputY = 300;

    public SelectPictureHelper(Activity activity) {
        this.mActivity = activity;
    }


    /**
     * 设置图片宽高比，输出图片宽高
     *
     * @param aspectX
     * @param aspectY
     * @param outputX
     * @param outputY
     */
    public void setImageStyle(int aspectX, int aspectY, int outputX, int outputY) {
        this.aspectX = aspectX;
        this.aspectY = aspectY;
        this.outputX = outputX;
        this.outputY = outputY;
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
        File parentFile = getParentFile();
        //用于保存调用相机拍照后所生成的文件
        tempFile = new File(parentFile, System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mActivity, mActivity.getPackageName() + ".fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        mActivity.startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    @NonNull
    private File getParentFile() {
        File parentFile = new File(Environment.getExternalStorageDirectory().getPath() + "/bishu");
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        return parentFile;
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
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        //裁剪后的图片Uri路径，uritempFile为Uri类变量
        File parentFile = getParentFile();
        uritempFile = Uri.parse("file://" + "/" + parentFile.getAbsolutePath() + "/" + fileName + ".jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        mActivity.startActivityForResult(intent, CROP_REQUEST_CODE);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        switch (requestCode) {
            case CAMERA_REQUEST_CODE:   //调用相机后返回
                if (resultCode == RESULT_OK) {
                    //用相机返回的照片去调用剪裁也需要对Uri进行处理
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(mActivity, mActivity.getPackageName() + ".fileProvider", tempFile);
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

                //将Uri图片转换为Bitmap
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(mActivity.getContentResolver().openInputStream(uritempFile));
                    //也可以进行一些保存、压缩等操作后上传
                    String path = saveImage(fileName, bitmap);
                    if (mOnSelectPictureSuccess != null) {
                        mOnSelectPictureSuccess.onSelected(path, bitmap);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    public String saveImage(String name, Bitmap bmp) {
        File parentFile = getParentFile();
        String fileName = name + ".jpg";
        File file = new File(parentFile, fileName);
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
