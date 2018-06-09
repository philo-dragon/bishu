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
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityUploadDrivingBookBinding;
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

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_DRIVING_BOOK)
public class ModuleUserUploadDrivingBookActivity extends BaseActivity<ModuleUserActivityUploadDrivingBookBinding> implements View.OnClickListener {

    private BaseBottomDialog uploadDialog;
    private SelectPictureHelper pictureHelper;

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_upload_driving_book;
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
                }
            }
        });
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
        }
    }

    private void showUploadDialog(final int id) {
        uploadDialog = BottomDialogManager.uploadDialog(getSupportFragmentManager(), new BottomDialogManager.OnUploadDialogListener() {
            @Override
            public void onCamera() {
                BottomDialogManager.dismiss(uploadDialog);
                String fileName = "file_front";
                pictureHelper.getPicFromCamera(fileName, id);
            }

            @Override
            public void onPhotoAlbum() {
                BottomDialogManager.dismiss(uploadDialog);
                String fileName = "file_front";
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

        HiPermission.create(mContext)
                .title("比数权限")
                .permissions(permissionItems)
                .filterColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, getTheme()))//permission icon color
                .msg(String.format(getString(R.string.permission_dialog_msg), getString(R.string.app_name)))
                .style(R.style.PermissionDefaultGreenStyle)
                .checkMutiPermission(new PermissionCallback() {

                    @Override
                    public void onClose() {//用户关闭权限申请

                    }

                    @Override
                    public void onFinish() {//所有权限申请完成
                        Observable.just(1).delay(100, TimeUnit.MILLISECONDS).subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
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
                    public void onDeny(String permission, int position) {
                    }

                    @Override
                    public void onGuarantee(String permission, int position) {
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        pictureHelper.onActivityResult(requestCode, resultCode, intent);
    }

}
