package com.pfl.module_user.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityLoginBinding;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_LOGIN)
public class ModuleUserLoginActivity extends BaseActivity<ModuleUserActivityLoginBinding> implements View.OnClickListener {


    @Override
    public int getContentView() {
        return R.layout.module_user_activity_login;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

    }

    @Override
    public void initView() {

        RxClickUtil.RxClick(mBinding.inLoginView1.moduleUserCvNext, this);
        RxClickUtil.RxClick(mBinding.inLoginView2.moduleUserCvLogin, this);
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
            mBinding.moduleUserVfFlipper.showNext();
        } else if (id == R.id.module_user_cv_login) {
            //finish();
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_REGIST);
        }

    }
}
