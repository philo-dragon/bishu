package com.pfl.module_user.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.dialog.ResultDialogFragment;
import com.pfl.common.entity.module_user.StorageToken;
import com.pfl.common.entity.module_user.UserLicence;
import com.pfl.common.imageloader.ImageLoader;
import com.pfl.common.imageloader.glide.ImageConfigImpl;
import com.pfl.common.utils.BottomDialogManager;
import com.pfl.common.utils.DialogManager;
import com.pfl.common.utils.PermissionUtil;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityUploadDrivingLicenceResultBinding;
import com.pfl.module_user.di.module_upload_licence_result.DaggerUploadLicenceResultComponent;
import com.pfl.module_user.di.module_upload_licence_result.UploadLicenceResultModule;
import com.pfl.module_user.utils.SelectPictureHelper;
import com.pfl.module_user.view.StorageTokenView;
import com.pfl.module_user.view.UploadLicenceResultView;
import com.pfl.module_user.viewmodel.StorageTokenViewModel;
import com.pfl.module_user.viewmodel.UploadLicenceResultViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import me.shaohui.bottomdialog.BaseBottomDialog;
import me.weyye.hipermission.PermissionItem;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_DRIVING_LICENCE_RESULT)
public class ModuleUserUploadDrivingLicenceResultActivity extends BaseActivity<ModuleUserActivityUploadDrivingLicenceResultBinding> implements UploadLicenceResultView, StorageTokenView, View.OnClickListener {

    private BaseBottomDialog uploadDialog;
    private SelectPictureHelper pictureHelper;
    private StorageToken mStorageToken;
    private ProgressDialog progressDialog;

    @Inject
    ImageLoader imageLoader;
    @Inject
    UploadLicenceResultViewModel viewModel;
    @Inject
    StorageTokenViewModel tokenViewModel;

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_upload_driving_licence_result;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

        DaggerUploadLicenceResultComponent
                .builder()
                .appComponent(appComponent)
                .uploadLicenceResultModule(new UploadLicenceResultModule(this, this, this))
                .build()
                .inject(this);
    }

    @Override
    public void initView() {
        mBinding.setViewModel(viewModel);
        pictureHelper = new SelectPictureHelper(this);
        pictureHelper.setOnSelectPictureSuccess(new SelectPictureHelper.OnSelectPictureSuccess() {
            @Override
            public void onSelected(String path, Bitmap bitmap) {
                if (mStorageToken == null) {
                    return;
                }
                showUploadDialog();
                if (pictureHelper.getTag() == R.id.module_user_img_upload_file_front) {
                    mBinding.moduleUserIcDrivingLicenceCardFrontImg.setImageBitmap(bitmap);
                    tokenViewModel.asyncPutObjectFromLocalFile(mStorageToken, "0", "driver_licence", path);
                } else if (pictureHelper.getTag() == R.id.module_user_img_upload_file_back) {
                    mBinding.moduleUserIcDrivingLicenceCardBackImg.setImageBitmap(bitmap);
                    tokenViewModel.asyncPutObjectFromLocalFile(mStorageToken, "1", "driver_licence", path);
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
        viewModel.getIdentity();
        tokenViewModel.getStorageToken();
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
                pictureHelper.setImageStyle(85, 54, 855, 510);
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
                pictureHelper.setImageStyle(85, 54, 855, 510);
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

        PermissionUtil.requestPermission(permissionItems, new PermissionUtil.SimplePermissionCallback() {

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

    @Override
    public void onSuccess(UserLicence licence) {

        mBinding.setUserLicence(licence);

        imageLoader.loadImage(mContext, ImageConfigImpl
                .builder().url(licence.getFront_img())
                .imageView(mBinding.moduleUserIcDrivingLicenceCardFrontImg)
                .build());

        imageLoader.loadImage(mContext, ImageConfigImpl
                .builder().url(licence.getFront_img())
                .imageView(mBinding.moduleUserIcDrivingLicenceCardBackImg)
                .build());

        //{0, 1, 2, 3, 4},分别表示{未认证, 认证中，认证成功，认证失败, 重复认证}|
        if (licence.getVerified_status() == 3) {
            ResultDialogFragment dialogFragment = ResultDialogFragment.newInstance(ResultDialogFragment.RESULT_FAIL, "认证失败", licence.getVerified_msg());
            dialogFragment.show(getSupportFragmentManager(), ResultDialogFragment.class.getSimpleName());
        } else if (licence.getVerified_status() == 2) {
            ResultDialogFragment dialogFragment = ResultDialogFragment.newInstance(ResultDialogFragment.RESULT_SUCCESS, "认证成功", licence.getVerified_msg());
            dialogFragment.show(getSupportFragmentManager(), ResultDialogFragment.class.getSimpleName());
        }
    }

    @Override
    public void onStorageToken(StorageToken storageToken) {
        mStorageToken = storageToken;
    }

    @Override
    public void uploadProgress(int progress) {
        if (progressDialog != null) {
            progressDialog.incrementProgressBy(progress);
        }
    }

    @Override
    public void uploadSuccess() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void uploadFail() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    private void showUploadDialog() {
        progressDialog = DialogManager.uploadFileProgressDialog(this);
        progressDialog.show();
    }
}
