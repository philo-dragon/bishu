package com.pfl.module_user.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityUpdatePasswordBinding;
import com.pfl.module_user.databinding.ModuleUserActivityUploadIdentityCardBinding;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_IDENTITY_CARD)
public class ModuleUserUploadIdentityCardActivity extends BaseActivity<ModuleUserActivityUploadIdentityCardBinding> {


    @Override
    public int getContentView() {
        return R.layout.module_user_activity_upload_identity_card;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void setToolBar() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
    }

    @Override
    public void initData() {

    }
}
