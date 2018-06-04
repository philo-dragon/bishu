package com.pfl.module_user.fragment;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseFragment;
import com.pfl.common.di.AppComponent;
import com.pfl.common.service.ModuleUserRouteService;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserFragmentMineBinding;


@Route(path = RouteUtils.MODULE_USER_FRAGMENT_MINE)
public class ModuleUserMineFragment extends BaseFragment<ModuleUserFragmentMineBinding> implements View.OnClickListener {

    @Override
    public int getContentView() {
        return R.layout.module_user_fragment_mine;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        setToolBar();
        RxClickUtil.RxClick(mBinding.moduleUserImgPhoto, this);
        RxClickUtil.RxClick(mBinding.moduleUserRlRealNameAuth, this);
        RxClickUtil.RxClick(mBinding.moduleUserRlDriverAuth, this);
        RxClickUtil.RxClick(mBinding.moduleUserRlCarAuth, this);
    }

    @Override
    public void setToolBar() {
        setToolBarNoBack(mBinding.inToolbarLayout.titleBar, "个人中心");
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.module_user_img_photo) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_INITIAL_VALUE);
        } else if (i == R.id.module_user_rl_real_name_auth) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_IDENTITY_CARD);
        } else if (i == R.id.module_user_rl_driver_auth) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_DRIVING_LICENCE);
        } else if (i == R.id.module_user_rl_car_auth) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_ADD_CAR);
        }
    }
}
