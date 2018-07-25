package com.pfl.module_user.activity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityForgetPasswordBinding;
import com.pfl.module_user.di.module_forget_password.DaggerForgetPasswordComponent;
import com.pfl.module_user.di.module_forget_password.ForgetPasswordModule;
import com.pfl.module_user.view.ForgetPasswordView;
import com.pfl.module_user.viewmodel.ForgetPasswordViewModel;

import javax.inject.Inject;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_FORGET_PASSWORD)
public class ModuleUserForgetPasswordActivity extends BaseActivity<ModuleUserActivityForgetPasswordBinding> implements ForgetPasswordView {

    @Inject
    ForgetPasswordViewModel viewModel;
    @Autowired
    String mobile;

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_forget_password;
    }

    @Override
    public void componentInject(AppComponent appComponent) {
        DaggerForgetPasswordComponent
                .builder()
                .appComponent(appComponent)
                .forgetPasswordModule(new ForgetPasswordModule(this, this))
                .build()
                .inject(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void setToolBar() {
    }

    @Override
    public void initData() {

    }

    @Override
    public void onSuccess() {
        finish();
    }
}
