package com.pfl.module_user.activity;

import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.StringUtils;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.entity.module_user.User;
import com.pfl.common.imageloader.ImageLoader;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.constant.UserInfoManager;
import com.pfl.module_user.databinding.ModuleUserActivityRegistBinding;
import com.pfl.module_user.di.module_regist.DaggerRegistComponent;
import com.pfl.module_user.di.module_regist.RegistModule;
import com.pfl.module_user.po.ModuleUserPoUser;
import com.pfl.module_user.view.RegistView;
import com.pfl.module_user.viewmodel.RegistViewModel;

import javax.inject.Inject;

/**
 * 用户注册
 */
@Route(path = RouteUtils.MODULE_USER_ACTIVITY_REGIST)
public class ModuleUserRegistActivity extends BaseActivity<ModuleUserActivityRegistBinding> implements RegistView, View.OnClickListener {

    @Inject
    ImageLoader imageLoader;
    @Inject
    RegistViewModel viewModel;
    @Autowired
    String mobile;

    private String checkCode;


    @Override
    protected int getBackgroundColorRes() {
        return R.color.white;
    }

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_regist;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

        DaggerRegistComponent
                .builder()
                .appComponent(appComponent)
                .registModule(new RegistModule(this, this))
                .build()
                .inject(this);

    }

    @Override
    public void initView() {

        mBinding.inRegistView1.moduleUserTvRegistedHint.setText(String.format(getString(R.string.module_user_str_get_check_code), mobile));
        mBinding.inRegistView2.moduleUserTvRegistedHint.setText(String.format(getString(R.string.module_user_str_setting_pwd), mobile));

        RxClickUtil.RxClick(mBinding.inRegistView1.moduleUserCvNext, this);
        RxClickUtil.RxClick(mBinding.inRegistView1.moduleUserBtnGetCheckCode, this);
        RxClickUtil.RxClick(mBinding.inRegistView2.moduleUserCvRegist, this);
    }

    @Override
    public void setToolBar() {
    }

    @Override
    public void initData() {
    }

    @Override
    public void onSuccess(User user) {
        UserInfoManager.getInstance().setUser(user);
        finish();
    }

    @Override
    public void smsVerifyResult(boolean result) {
        if (result) {
            mBinding.modulerUserVfFlipper.showNext();
            mBinding.inRegistView1.moduleUserTvCheckcodeErrorHint.setVisibility(View.GONE);
        } else {
            mBinding.inRegistView1.moduleUserTvCheckcodeErrorHint.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.module_user_cv_next) {
            checkCode = mBinding.inRegistView1.moduleUserEtCheckCode.getText().toString().trim();
            if (!StringUtils.isEmpty(checkCode)) {
                viewModel.verifySMS(mobile, checkCode);
            }
        } else if (id == R.id.module_user_cv_regist) {
            String password = mBinding.inRegistView2.moduleUserEtPasword.getText().toString().trim();//密码
            String invatinCode = mBinding.inRegistView2.moduleUserInvationCode.getText().toString().trim();//邀请码
            if (!StringUtils.isEmpty(password)
                    && password.length() >= 6
                    && password.length() <= 20) {
                viewModel.regist(mobile, password, invatinCode, checkCode);
            }
        } else if (id == R.id.module_user_btn_get_check_code) {
            mBinding.inRegistView1.moduleUserBtnGetCheckCode.onStart();
            viewModel.sendSMS(mobile);
        }
    }

    @Override
    protected void onDestroy() {
        mBinding.inRegistView1.moduleUserBtnGetCheckCode.onStop();
        super.onDestroy();
    }
}
