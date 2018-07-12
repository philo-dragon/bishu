package com.pfl.module_user.fragment;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseLazyFragment;
import com.pfl.common.di.AppComponent;
import com.pfl.common.entity.module_user.UserInfo;
import com.pfl.common.imageloader.ImageLoader;
import com.pfl.common.imageloader.glide.ImageConfigImpl;
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
public class ModuleUserMineFragment extends BaseLazyFragment<ModuleUserFragmentMineBinding> implements MyCenterView, View.OnClickListener {

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
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.module_user_img_photo) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_INITIAL_VALUE);
        } else if (i == R.id.module_user_rl_real_name_auth) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_IDENTITY_CARD);
        } else if (i == R.id.module_user_rl_driver_auth) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_DRIVING_LICENCE);
        } else if (i == R.id.module_user_rl_car_auth) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_DRIVING_BOOK);
        } else if (i == R.id.module_user_rl_setting) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_SETTING);
        } else if (i == R.id.module_user_rl_header_view) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_LOGIN);
        } else if (i == R.id.module_user_rl_intelligent_hardware) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_FIND_DEVICES);
        }
    }

    @Override
    public void onUserVisible() {
        if (UserInfoManager.getInstance().getUser() != null) {
            viewModel.requestData(UserInfoManager.getInstance().getUser().getUid());
        }
    }

    @Override
    public void onSuccess(UserInfo userInfo) {
        UserInfoManager.getInstance().setUserInfo(userInfo);
        imageLoader.loadImage(mContext, ImageConfigImpl
                .builder().url(userInfo.getAvatar())
                .imageView(mBinding.moduleUserImgPhoto)
                .build());

    }
}
