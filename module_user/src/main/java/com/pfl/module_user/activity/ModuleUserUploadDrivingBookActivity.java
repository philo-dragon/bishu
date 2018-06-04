package com.pfl.module_user.activity;

import android.os.Bundle;

import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityUploadDrivingBookBinding;

public class ModuleUserUploadDrivingBookActivity extends BaseActivity<ModuleUserActivityUploadDrivingBookBinding> {

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_upload_driving_book;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        setToolBar();
    }

    @Override
    public void setToolBar() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
    }

    @Override
    public void initData() {

    }
}
