package com.pfl.module_user.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.BottomDialogManager;
import com.pfl.common.utils.PermissionUtil;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityUploadDrivingLicenceBinding;
import com.pfl.module_user.databinding.ModuleUserActivityUploadDrivingLicenceResultBinding;
import com.pfl.module_user.utils.SelectPictureHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import me.shaohui.bottomdialog.BaseBottomDialog;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_DRIVING_LICENCE_RESULT)
public class ModuleUserUploadDrivingLicenceResultActivity extends BaseActivity<ModuleUserActivityUploadDrivingLicenceResultBinding> implements View.OnClickListener {

    private BaseBottomDialog uploadDialog;
    private SelectPictureHelper pictureHelper;

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_upload_driving_licence_result;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        pictureHelper = new SelectPictureHelper(this);
        pictureHelper.setOnSelectPictureSuccess(new SelectPictureHelper.OnSelectPictureSuccess() {
            @Override
            public void onSelected(String path, Bitmap bitmap) {

                if (pictureHelper.getTag() == R.id.module_user_img_upload_file_front) {
                    mBinding.moduleUserImgUploadFileFront.setImageBitmap(bitmap);
                } else if (pictureHelper.getTag() == R.id.module_user_img_upload_file_back) {
                    mBinding.moduleUserImgUploadFileBack.setImageBitmap(bitmap);
                }
            }
        });
        RxClickUtil.RxClick(mBinding.moduleUserImgUploadFileFront, this);
        RxClickUtil.RxClick(mBinding.moduleUserImgUploadFileBack, this);
    }

    @Override
    public void setToolBar() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.module_user_img_upload_file_front) {
            requestPermission(i);
        } else if (i == R.id.module_user_img_upload_file_back) {
            requestPermission(i);
        }
    }

    private void showUploadDialog(final int id) {
        uploadDialog = BottomDialogManager.uploadDialog(getSupportFragmentManager(), new BottomDialogManager.OnUploadDialogListener() {
            @Override
            public void onCamera() {
                BottomDialogManager.dismiss(uploadDialog);

                String fileName = "file_front";
                if (id == R.id.module_user_img_upload_file_front) {
                    fileName = "file_front";
                } else if (id == R.id.module_user_img_upload_file_back) {
                    fileName = "file_back";
                }

                pictureHelper.getPicFromCamera(fileName, id);
            }

            @Override
            public void onPhotoAlbum() {
                BottomDialogManager.dismiss(uploadDialog);

                String fileName = "file_front";
                if (id == R.id.module_user_img_upload_file_front) {
                    fileName = "file_front";
                } else if (id == R.id.module_user_img_upload_file_back) {
                    fileName = "file_back";
                }
                pictureHelper.getPicFromAlbm(fileName, id);
            }

            @Override
            public void onCancel() {
                BottomDialogManager.dismiss(uploadDialog);
            }
        });
    }

    private void requestPermission(final int id) {

        List<PermissionItem> permissionItems = new ArrayList<>();
        permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "拍照权限", R.drawable.permission_ic_camera));
        permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储权限", R.drawable.permission_ic_storage));

        PermissionUtil.requestPermission(permissionItems, new PermissionUtil.SimplePermissionCallback(){

            @Override
            public void onFinish() {
                Observable.just(1).delay(100, TimeUnit.MILLISECONDS).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        showUploadDialog(id);
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        pictureHelper.onActivityResult(requestCode, resultCode, intent);
    }
}
