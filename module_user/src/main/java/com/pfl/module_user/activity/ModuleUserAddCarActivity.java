package com.pfl.module_user.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityAddCarBinding;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_ADD_CAR)
public class ModuleUserAddCarActivity extends BaseActivity<ModuleUserActivityAddCarBinding> {

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_add_car;
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
