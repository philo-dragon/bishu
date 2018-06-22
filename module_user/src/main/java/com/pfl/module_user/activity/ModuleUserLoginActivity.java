package com.pfl.module_user.activity;

import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.RegexUtils;
import com.knifestone.hyena.currency.TextWatcherAdapter;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityLoginBinding;
import com.pfl.module_user.di.module_login.DaggerLoginComponent;
import com.pfl.module_user.di.module_login.LoginModule;
import com.pfl.module_user.view.LoginView;
import com.pfl.module_user.viewmodel.LoginViewModel;

import java.util.HashMap;

import javax.inject.Inject;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_LOGIN)
public class ModuleUserLoginActivity extends BaseActivity<ModuleUserActivityLoginBinding> implements LoginView, View.OnClickListener {

    @Inject
    LoginViewModel loginViewModel;

    private String mobile;

    @Override
    protected int getBackgroundColorRes() {
        return R.color.white;
    }

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_login;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this, this))
                .build()
                .inject(this);
    }

    @Override
    public void initView() {

        RxClickUtil.RxClick(mBinding.inLoginView1.moduleUserCvNext, this);
        RxClickUtil.RxClick(mBinding.inLoginView2.moduleUserCvLogin, this);


        mBinding.inLoginView1.moduleUserEtMobileNo.addTextChangedListener(new TextWatcherAdapter() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (TextUtils.isEmpty(s)) {
                    mBinding.inLoginView1.moduleUserTvCheckHint.setVisibility(View.GONE);
                } else {
                    if (RegexUtils.isMobileExact(s)) {
                        mBinding.inLoginView1.moduleUserTvCheckHint.setVisibility(View.GONE);
                    } else {
                        mBinding.inLoginView1.moduleUserTvCheckHint.setVisibility(View.VISIBLE);
                    }
                }

            }
        });

    }

    @Override
    public void setToolBar() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.module_user_cv_next) {
            if (checkMobile()) {
                loginViewModel.requestData();
            }
        } else if (id == R.id.module_user_cv_login) {

        }

    }

    public boolean checkMobile() {
        boolean flag = false;
        mobile = mBinding.inLoginView1.moduleUserEtMobileNo.getText().toString().trim();
        if (RegexUtils.isMobileExact(mobile)) {
            mBinding.inLoginView1.moduleUserTvCheckHint.setVisibility(View.GONE);
            return true;
        }
        mBinding.inLoginView1.moduleUserTvCheckHint.setVisibility(View.VISIBLE);
        return flag;
    }

    @Override
    public void verifyMobule(boolean isExist) {

        if (isExist) {
            mBinding.inLoginView2.moduleUserTvRegistedHint.setText(String.format(getString(R.string.module_user_str_registed_hint), mobile));
            mBinding.moduleUserVfFlipper.showNext();
        } else {
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("mobile", mobile);
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_REGIST, parameters);
        }
    }

    @Override
    public void loginSuccess() {
        mBinding.inLoginView2.moduleUserTvPasswordErrorHint.setVisibility(View.INVISIBLE);
    }

    @Override
    public void loginFailed() {
        mBinding.inLoginView2.moduleUserTvPasswordErrorHint.setVisibility(View.VISIBLE);

    }

    @Override
    public void onFail(int errorCode) {

    }
}
