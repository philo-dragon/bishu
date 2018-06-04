package com.pfl.module_user.activity;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.BottomDialogManager;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityUploadDrivingLicenceBinding;

import me.shaohui.bottomdialog.BaseBottomDialog;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_DRIVING_LICENCE)
public class ModuleUserUploadDrivingLicenceActivity extends BaseActivity<ModuleUserActivityUploadDrivingLicenceBinding> implements View.OnClickListener {

    private BaseBottomDialog uploadDialog;

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_upload_driving_licence;
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
            showUploadDialog(i);
        } else if (i == R.id.module_user_img_upload_file_back) {
            showUploadDialog(i);
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
}
