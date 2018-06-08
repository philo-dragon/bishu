package com.pfl.module_user.activity;

import android.Manifest;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.BottomDialogManager;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityUploadIdentityCardBinding;
import com.pfl.module_user.databinding.ModuleUserActivityUploadIdentityCardResultBinding;

import java.util.ArrayList;
import java.util.List;

import me.shaohui.bottomdialog.BaseBottomDialog;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_IDENTITY_CARD_RESULT)
public class ModuleUserUploadIdentityCardResultActivity extends BaseActivity<ModuleUserActivityUploadIdentityCardResultBinding> implements View.OnClickListener {


    private BaseBottomDialog uploadDialog;

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_upload_identity_card_result;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

    }

    @Override
    public void initView() {
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

    private void showUploadDialog(int id) {
        uploadDialog = BottomDialogManager.uploadDialog(getSupportFragmentManager(), new BottomDialogManager.OnUploadDialogListener() {
            @Override
            public void onCamera() {
                BottomDialogManager.dismiss(uploadDialog);
            }

            @Override
            public void onPhotoAlbum() {
                BottomDialogManager.dismiss(uploadDialog);
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
                        showUploadDialog(id);
                    }

                    @Override
                    public void onDeny(String permission, int position) {
                    }

                    @Override
                    public void onGuarantee(String permission, int position) {
                    }
                });

    }
}