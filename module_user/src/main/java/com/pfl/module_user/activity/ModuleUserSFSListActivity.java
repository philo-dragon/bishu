package com.pfl.module_user.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivitySfsListBinding;

/**
 * 胜负师列表
 */
@Route(path = RouteUtils.MODULE_USER_ACTIVITY_SFS_LIST)
public class ModuleUserSFSListActivity extends BaseActivity<ModuleUserActivitySfsListBinding> {


    @Override
    public int getContentView() {
        return R.layout.module_user_activity_sfs_list;
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
