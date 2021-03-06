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
import com.pfl.common.entity.module_user.UserIndentity;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.imageloader.ImageLoader;
import com.pfl.common.imageloader.glide.ImageConfigImpl;
import com.pfl.common.utils.BottomDialogManager;
import com.pfl.common.utils.DialogManager;
import com.pfl.common.utils.PermissionUtil;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
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
import me.weyye.hipermission.PermissionItem;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_IDENTITY_CARD_RESULT)
public class ModuleUserUploadIdentityCardResultActivity extends BaseActivity<ModuleUserActivityUploadIdentityCardResultBinding> implements UploadIndentityResultView, StorageTokenView, View.OnClickListener {

    private SelectPictureHelper pictureHelper;
    private StorageToken mStorageToken;
    private BaseBottomDialog uploadDialog;
    private ProgressDialog progressDialog;

    @Inject
    ImageLoader imageLoader;
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
                    mBinding.moduleUserImgUploadFileFrontImg.setImageBitmap(bitmap);
                    tokenViewModel.asyncPutObjectFromLocalFile(mStorageToken, "0", "id_card", path);
                } else if (pictureHelper.getTag() == R.id.module_user_img_upload_file_back) {
                    mBinding.moduleUserImgUploadFileBackImg.setImageBitmap(bitmap);
                    tokenViewModel.asyncPutObjectFromLocalFile(mStorageToken, "1", "id_card", path);
                }
            }
        });

        RxClickUtil.RxClick(mBinding.moduleUserImgUploadFileFront, this);
        RxClickUtil.RxClick(mBinding.moduleUserImgUploadFileBack, this);
        RxClickUtil.RxClick(mBinding.moduleUserCvCommit, this);
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
        } else if (i == R.id.module_user_cv_commit) {
            finish();
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
    public void onSuccess(final UserIndentity indentity) {

        mBinding.setUserIndentity(indentity);

        imageLoader.loadImage(mContext, ImageConfigImpl
                .builder()
                .url(indentity.getFront_img())
                .placeholder(R.drawable.module_user_ic_identity_card_front)
                .imageView(mBinding.moduleUserImgUploadFileFrontImg)
                .build());

        imageLoader.loadImage(mContext, ImageConfigImpl
                .builder()
                .url(indentity.getBack_img())
                .placeholder(R.drawable.module_user_ic_identity_card_back)
                .imageView(mBinding.moduleUserImgUploadFileBackImg)
                .build());

        Observable.just("1")
                .delay(250, TimeUnit.MILLISECONDS)
                .compose(RxSchedulers.<String>noCheckNetworkCompose())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        showAuthResultDialog(indentity);
                    }
                });

    }

    private void showAuthResultDialog(UserIndentity indentity) {
        //{0, 1, 2, 3, 4},分别表示{未认证, 认证中，认证成功，认证失败, 重复认证}|
        if (indentity.getVerified_status() == 3) {
            ResultDialogFragment dialogFragment = ResultDialogFragment.newInstance(ResultDialogFragment.RESULT_FAIL, "认证失败", indentity.getVerified_msg());
            dialogFragment.show(getSupportFragmentManager(), ResultDialogFragment.class.getSimpleName());
        } else if (indentity.getVerified_status() == 2) {
            ResultDialogFragment dialogFragment = ResultDialogFragment.newInstance(ResultDialogFragment.RESULT_SUCCESS, "认证成功", indentity.getVerified_msg());
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
