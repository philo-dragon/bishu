package com.pfl.module_user.fragment;


import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ConvertUtils;
import com.pfl.common.base.LazyLoadBaseFragment;
import com.pfl.common.di.AppComponent;
import com.pfl.common.entity.base.BaseEvent;
import com.pfl.common.entity.module_user.Score;
import com.pfl.common.entity.module_user.User;
import com.pfl.common.entity.module_user.UserInfo;
import com.pfl.common.utils.AnimationManager;
import com.pfl.common.utils.App;
import com.pfl.common.utils.EventBusUtil;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.weidget.TitleBar;
import com.pfl.module_user.R;
import com.pfl.module_user.constant.UserInfoManager;
import com.pfl.module_user.databinding.ModuleUserFragmentHomeBinding;
import com.pfl.module_user.di.module_home.DaggerHomeComponent;
import com.pfl.module_user.di.module_home.HomeModule;
import com.pfl.module_user.view.HomeView;
import com.pfl.module_user.viewmodel.HomeViewModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;

import javax.inject.Inject;

/**
 * 我的行程
 */
@Route(path = RouteUtils.MODULE_USER_FRAGMENT_HOME)
public class ModuleUserHomeFragment extends LazyLoadBaseFragment<ModuleUserFragmentHomeBinding> implements HomeView {

    private boolean isInit;
    @Inject
    HomeViewModel viewModel;

    @Override
    public int getContentView() {
        return R.layout.module_user_fragment_home;
    }

    @Override
    public void componentInject(AppComponent appComponent) {
        DaggerHomeComponent
                .builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this, this))
                .build()
                .inject(this);
    }

    @Override
    public void initView() {
        EventBusUtil.regist(this);
        setToolBar();
        mBinding.setViewModule(viewModel);
    }

    @Override
    public void setToolBar() {
        setToolBarNoBack(mBinding.inToolbarLayout.titleBar, getResources().getString(R.string.module_user_bishu));
        ImageView tvMesage = (ImageView) mBinding.inToolbarLayout.titleBar.addAction(new TitleBar.ImageAction(R.drawable.module_user_ic_home_message) {
            @Override
            public void performAction(View view) {
                RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_MESSAGE);
            }
        });
        tvMesage.getLayoutParams().width = ConvertUtils.dp2px(33);
        tvMesage.getLayoutParams().height = ConvertUtils.dp2px(30);
    }

    @Override
    public void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        viewModel.requestData();
    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
        if (!isInit) {
            isInit = true;
            AnimationManager.rotateAnim(mBinding.moduleUserImgRotate, 10 * 1000);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBusUtil.unregist(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventBus(BaseEvent<User> event) {
        if (event != null) {
            viewModel.requestData();
        }
    }

    @Override
    public void onSuccess(Score score) {
        mBinding.setScore(score);
    }

    @Override
    public void onFindUserInfo(UserInfo userInfo) {
        UserInfoManager.getInstance().setUserInfo(userInfo);
    }

}
