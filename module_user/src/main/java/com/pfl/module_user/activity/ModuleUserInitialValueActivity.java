package com.pfl.module_user.activity;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityInitialValueBinding;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_INITIAL_VALUE)
public class ModuleUserInitialValueActivity extends BaseActivity<ModuleUserActivityInitialValueBinding> implements View.OnClickListener {


    @Override
    public int getContentView() {
        return R.layout.module_user_activity_initial_value;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        setToolBar();
        RxClickUtil.RxClick(mBinding.moduleUserRlNickName, this);
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
        RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_INPUT_NICK_NAME);
    }
}
