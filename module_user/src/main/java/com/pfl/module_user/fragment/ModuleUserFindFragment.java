package com.pfl.module_user.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.LazyLoadBaseFragment;
import com.pfl.common.di.AppComponent;
import com.pfl.common.entity.module_user.FindBean;
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserFragmentFindBinding;
import com.pfl.module_user.di.module_find.DaggerFindComponent;
import com.pfl.module_user.di.module_find.FindModule;
import com.pfl.module_user.view.FindView;
import com.pfl.module_user.viewmodel.FindViewModel;

import javax.inject.Inject;

/**
 * 发现
 */
@Route(path = RouteUtils.MODULE_USER_FRAGMENT_FIND)
public class ModuleUserFindFragment extends LazyLoadBaseFragment<ModuleUserFragmentFindBinding> implements FindView {


    @Inject
    FindViewModel viewModel;

    @Override
    public int getContentView() {
        return R.layout.module_user_fragment_find;
    }

    @Override
    public void componentInject(AppComponent appComponent) {
        DaggerFindComponent
                .builder()
                .appComponent(appComponent)
                .findModule(new FindModule(this, this))
                .build()
                .inject(this);
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
    public void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        viewModel.requestData();
    }

    @Override
    public void onSuccess(FindBean findBean) {
        mBinding.setFindBean(findBean);
    }
}
