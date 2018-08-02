package com.pfl.module_user.fragment;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.LazyLoadBaseFragment;
import com.pfl.common.di.AppComponent;
import com.pfl.common.entity.module_user.User;
import com.pfl.common.entity.module_user.UserInfo;
import com.pfl.common.imageloader.ImageLoader;
import com.pfl.common.imageloader.glide.ImageConfigImpl;
import com.pfl.common.service.ModuleUserRouteService;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.constant.UserInfoManager;
import com.pfl.module_user.databinding.ModuleUserFragmentMineBinding;
import com.pfl.module_user.di.module_my_center.DaggerMyCenterComponent;
import com.pfl.module_user.di.module_my_center.MyCenterModule;
import com.pfl.module_user.view.MyCenterView;
import com.pfl.module_user.viewmodel.MyCenterViewModel;

import javax.inject.Inject;


@Route(path = RouteUtils.MODULE_USER_FRAGMENT_MINE)
public class ModuleUserMineFragment extends LazyLoadBaseFragment<ModuleUserFragmentMineBinding> implements MyCenterView, View.OnClickListener {

    @Inject
    ImageLoader imageLoader;
    @Inject
    MyCenterViewModel viewModel;

    @Override
    public int getContentView() {
        return R.layout.module_user_fragment_mine;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

        DaggerMyCenterComponent
                .builder()
                .appComponent(appComponent)
                .myCenterModule(new MyCenterModule(this, this))
                .build()
                .inject(this);

    }

    @Override
    public void initView() {
        mBinding.setViewModel(viewModel);
        setToolBar();
        RxClickUtil.RxClick(mBinding.moduleUserImgPhoto, this);
        RxClickUtil.RxClick(mBinding.moduleUserRlRealNameAuth, this);
        RxClickUtil.RxClick(mBinding.moduleUserRlDriverAuth, this);
        RxClickUtil.RxClick(mBinding.moduleUserRlCarAuth, this);
        RxClickUtil.RxClick(mBinding.moduleUserRlSetting, this);
        RxClickUtil.RxClick(mBinding.moduleUserRlHeaderView, this);
        RxClickUtil.RxClick(mBinding.moduleUserRlIntelligentHardware, this);
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
        if (checkUserLogined()) return;
        if (checkUserInfoIsNull()) return;
        int i = v.getId();
        if (i == R.id.module_user_rl_header_view) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_INITIAL_VALUE);
        } else if (i == R.id.module_user_rl_real_name_auth) {
            UserInfo userInfo = ModuleUserRouteService.getUserInfo();
            if (userInfo.getId_verified() == 0) {
                RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_IDENTITY_CARD);
            } else {
                RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_IDENTITY_CARD_RESULT);
            }
        } else if (i == R.id.module_user_rl_driver_auth) {
            UserInfo userInfo = ModuleUserRouteService.getUserInfo();
            if (userInfo.getDriver_verified() == 0) {
                RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_DRIVING_LICENCE);
            } else {
                RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_DRIVING_LICENCE_RESULT);
            }
        } else if (i == R.id.module_user_rl_car_auth) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_ADD_CAR);
        } else if (i == R.id.module_user_rl_setting) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_SETTING);
        } else if (i == R.id.module_user_rl_intelligent_hardware) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_INTELLIGENT_HARDWARE_LIST);
        }
    }

    private boolean checkUserLogined() {
        User user = ModuleUserRouteService.getUser();
        if (user == null) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_LOGIN);
            return true;
        }
        return false;
    }

    private boolean checkUserInfoIsNull() {
        UserInfo userInfo = ModuleUserRouteService.getUserInfo();
        return userInfo == null;
    }

    @Override
    public void onSuccess(UserInfo userInfo) {
        mBinding.setUserInfo(userInfo);
        if (userInfo.getDevices() != null && !userInfo.getDevices().isEmpty()) {
            mBinding.moduleUserTvConation.setText(userInfo.getDevices().get(0).getName() + " 已连接");
        }
        UserInfoManager.getInstance().setUserInfo(userInfo);
        imageLoader.loadImage(mContext, ImageConfigImpl
                .builder()
                .placeholder(R.drawable.module_user_ic_default_photo)
                .errorPic(R.drawable.module_user_ic_default_photo)
                .url(userInfo.getAvatar())
                .imageView(mBinding.moduleUserImgPhoto)
                .build());

    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
        if (ModuleUserRouteService.getUser() != null) {
            viewModel.requestData(UserInfoManager.getInstance().getUser().getUid());
        }
    }
}
