package com.pfl.module_user.activity;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.StringUtils;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.entity.module_user.User;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.constant.UserInfoManager;
import com.pfl.module_user.databinding.ModuleUserActivityForgetPasswordBinding;
import com.pfl.module_user.di.module_forget_password.DaggerForgetPasswordComponent;
import com.pfl.module_user.di.module_forget_password.ForgetPasswordModule;
import com.pfl.module_user.view.ForgetPasswordView;
import com.pfl.module_user.viewmodel.ForgetPasswordViewModel;

import javax.inject.Inject;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_FORGET_PASSWORD)
public class ModuleUserForgetPasswordActivity extends BaseActivity<ModuleUserActivityForgetPasswordBinding> implements ForgetPasswordView, View.OnClickListener {

    @Inject
    ForgetPasswordViewModel viewModel;
    @Autowired
    String mobile;

    private String checkCode;

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_forget_password;
    }

    @Override
    protected int getBackgroundColorRes() {
        return R.color.white;
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

        mBinding.inForgetPasswordView1.moduleUserTvRegistedHint.setText(String.format(getString(R.string.module_user_str_forget_password_code), mobile));
        mBinding.inForgetPasswordView2.moduleUserTvRegistedHint.setText(String.format(getString(R.string.module_user_str_forget_password_password), mobile));

        RxClickUtil.RxClick(mBinding.inForgetPasswordView1.moduleUserCvNext, this);
        RxClickUtil.RxClick(mBinding.inForgetPasswordView1.moduleUserBtnGetCheckCode, this);
        RxClickUtil.RxClick(mBinding.inForgetPasswordView2.moduleUserCvRegist, this);
    }

    @Override
    public void setToolBar() {
    }

    @Override
    public void initData() {
    }

    @Override
    public void smsVerifyResult(boolean result) {
        if (result) {
            mBinding.modulerUserVfFlipper.showNext();
            mBinding.inForgetPasswordView1.moduleUserTvCheckcodeErrorHint.setVisibility(View.GONE);
        } else {
            mBinding.inForgetPasswordView1.moduleUserTvCheckcodeErrorHint.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.module_user_cv_next) {
            checkCode = mBinding.inForgetPasswordView1.moduleUserEtCheckCode.getText().toString().trim();
            if (!StringUtils.isEmpty(checkCode)) {
                viewModel.verifySMS(mobile, checkCode);
            }
        } else if (id == R.id.module_user_cv_regist) {
            String password = mBinding.inForgetPasswordView2.moduleUserEtPasword.getText().toString().trim();//密码
            if (!StringUtils.isEmpty(password)
                    && password.length() >= 6
                    && password.length() <= 20) {
                viewModel.forgetPassword(mobile, password);
            }
        } else if (id == R.id.module_user_btn_get_check_code) {
            mBinding.inForgetPasswordView1.moduleUserBtnGetCheckCode.onStart();
            viewModel.sendSMS(mobile);
        }
    }

    @Override
    protected void onDestroy() {
        mBinding.inForgetPasswordView1.moduleUserBtnGetCheckCode.onStop();
        super.onDestroy();
    }

    @Override
    public void onSuccess() {
        finish();
    }
}
