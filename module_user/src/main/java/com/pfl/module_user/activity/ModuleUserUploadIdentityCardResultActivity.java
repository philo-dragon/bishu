package com.pfl.module_user.activity;

import android.Manifest;
import android.graphics.Bitmap;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.entity.module_user.StorageToken;
import com.pfl.common.entity.module_user.UserIndentity;
import com.pfl.common.utils.BottomDialogManager;
import com.pfl.common.utils.PermissionUtil;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityUploadIdentityCardBinding;
import com.pfl.module_user.databinding.ModuleUserActivityUploadIdentityCardResultBinding;
import com.pfl.module_user.di.module_upload_identity_result.DaggerUploadIndentityResultComponent;
import com.pfl.module_user.di.module_upload_identity_result.UploadIndentityResultModule;
import com.pfl.module_user.utils.SelectPictureHelper;
import com.pfl.module_user.view.StorageTokenView;
import com.pfl.module_user.view.UploadIndentityResultView;
import com.pfl.module_user.viewmodel.StorageTokenViewModel;
import com.pfl.module_user.viewmodel.UploadIndentityResultViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import me.shaohui.bottomdialog.BaseBottomDialog;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_IDENTITY_CARD_RESULT)
public class ModuleUserUploadIdentityCardResultActivity extends BaseActivity<ModuleUserActivityUploadIdentityCardResultBinding> implements UploadIndentityResultView, StorageTokenView, View.OnClickListener {

    private SelectPictureHelper pictureHelper;
    private StorageToken mStorageToken;
    private BaseBottomDialog uploadDialog;

    @Inject
    UploadIndentityResultViewModel viewModel;
    @Inject
    StorageTokenViewModel tokenViewModel;


    @Override
    public int getContentView() {
        return R.layout.module_user_activity_upload_identity_card_result;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

        DaggerUploadIndentityResultComponent
                .builder()
                .appComponent(appComponent)
                .uploadIndentityResultModule(new UploadIndentityResultModule(this, this, this))
                .build()
                .inject(this);

    }

    @Override
    public void initView() {

        pictureHelper = new SelectPictureHelper(this);
        pictureHelper.setOnSelectPictureSuccess(new SelectPictureHelper.OnSelectPictureSuccess() {
            @Override
            public void onSelected(String path, Bitmap bitmap) {

                if (pictureHelper.getTag() == R.id.module_user_img_upload_file_front) {
                    //mBinding.moduleUserImgUploadFileFrontImg.setImageBitmap(bitmap);
                    tokenViewModel.asyncPutObjectFromLocalFile(mStorageToken, "0", "id_card", path);
                } else if (pictureHelper.getTag() == R.id.module_user_img_upload_file_back) {
                    //mBinding.moduleUserImgUploadFileBackImg.setImageBitmap(bitmap);
                    tokenViewModel.asyncPutObjectFromLocalFile(mStorageToken, "1", "id_card", path);
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
    public void onSuccess(UserIndentity indentity) {

    }


    @Override
    public void onStorageToken(StorageToken storageToken) {
        mStorageToken = storageToken;
    }
}
