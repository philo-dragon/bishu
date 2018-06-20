package com.pfl.module_user.fragment;


import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ConvertUtils;
import com.pfl.common.base.BaseFragment;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.AnimationManager;
import com.pfl.common.utils.App;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.common.weidget.TitleBar;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserFragmentHomeBinding;
import com.pfl.module_user.di.module_home.DaggerHomeComponent;
import com.pfl.module_user.di.module_home.HomeModule;
import com.pfl.module_user.view.HomeView;

/**
 * 我的行程
 */
@Route(path = RouteUtils.MODULE_USER_FRAGMENT_HOME)
public class ModuleUserHomeFragment extends BaseFragment<ModuleUserFragmentHomeBinding> implements HomeView, View.OnClickListener {

    private boolean isInit;

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
        setToolBar();

        RxClickUtil.RxClick(mBinding.moduleUserTvMyWallet, this);
    }

    @Override
    public void setToolBar() {
        setToolBarNoBack(mBinding.inToolbarLayout.titleBar, getResources().getString(R.string.module_user_bishu));
        ImageView tvMesage = (ImageView) mBinding.inToolbarLayout.titleBar.addAction(new TitleBar.ImageAction(R.drawable.module_user_ic_home_message) {
            @Override
            public void performAction(View view) {
                Toast.makeText(App.getInstance(), "点击了消息", Toast.LENGTH_SHORT).show();
            }
        });
        tvMesage.getLayoutParams().width = ConvertUtils.dp2px(33);
        tvMesage.getLayoutParams().height = ConvertUtils.dp2px(30);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.module_user_tv_my_wallet) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_MY_WALLET);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (!isInit) {
            isInit = true;
            AnimationManager.setAnimText(mBinding.moduleUserTvIntegral, 10000000);
            AnimationManager.rotateAnim(mBinding.moduleUserImgRotate, 10 * 1000);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        //AnimationManager.cancelAnim(mBinding.moduleUserImgRotate);
    }

    @Override
    public void onSuccess() {

    }
}
