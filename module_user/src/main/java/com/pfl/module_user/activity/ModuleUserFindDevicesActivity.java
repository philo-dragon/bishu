package com.pfl.module_user.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ConvertUtils;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.App;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.weidget.TitleBar;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityFindDevicesBinding;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_FIND_DEVICES)
public class ModuleUserFindDevicesActivity extends BaseActivity<ModuleUserActivityFindDevicesBinding> {

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_find_devices;
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

        ImageView tvMesage = (ImageView) mBinding.inToolbarLayout.titleBar.addAction(new TitleBar.ImageAction(R.drawable.module_user_ic_explain) {
            @Override
            public void performAction(View view) {
                Toast.makeText(App.getInstance(), "点击了说明", Toast.LENGTH_SHORT).show();
            }
        });
        tvMesage.getLayoutParams().width = ConvertUtils.dp2px(30);
        tvMesage.getLayoutParams().height = ConvertUtils.dp2px(30);

    }

    @Override
    public void initData() {

    }
}
