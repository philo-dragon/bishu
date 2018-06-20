package com.pfl.module_user.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.AppUtils;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityAbouUsBinding;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_ABOU_US)
public class ModuleUserAbouUsActivity extends BaseActivity<ModuleUserActivityAbouUsBinding> {

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_abou_us;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

    }

    @Override
    public void initView() {

        mBinding.moduleUserTvVersion.setText("v" + AppUtils.getAppVersionName());

    }

    @Override
    public void setToolBar() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
    }

    @Override
    public void initData() {

    }
}
