package com.pfl.module_user.activity;

import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.imageloader.ImageLoader;
import com.pfl.common.utils.App;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.weidget.TitleBar;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityMyWalletBinding;

/**
 * 我的钱包
 */
@Route(path = RouteUtils.MODULE_USER_ACTIVITY_MY_WALLET)
public class ModuleUserMyWalletActivity extends BaseActivity<ModuleUserActivityMyWalletBinding> implements View.OnClickListener {

    private ImageLoader imageLoader;

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_my_wallet;
    }

    @Override
    public void componentInject(AppComponent appComponent) {
        imageLoader = appComponent.getImageLoader();
    }

    @Override
    public void initView() {

    }

    @Override
    public void setToolBar() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);

        mBinding.inToolbarLayout.titleBar.addAction(new TitleBar.TextAction("积分商城") {
            @Override
            public void performAction(View view) {
                Toast.makeText(App.getInstance(), "积分商城", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }

}
