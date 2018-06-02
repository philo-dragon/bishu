package com.pfl.module_user.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseFragment;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserFragmentFindBinding;

/**
 * 发现
 */
@Route(path = RouteUtils.MODULE_USER_FRAGMENT_FIND)
public class ModuleUserFindFragment extends BaseFragment<ModuleUserFragmentFindBinding> {


    @Override
    public int getContentView() {
        return R.layout.module_user_fragment_find;
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
        setToolBarNoBack(mBinding.inToolbarLayout.titleBar, "发现");
    }

    @Override
    public void initData() {

    }
}
