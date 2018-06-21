package com.pfl.module_user.activity;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.BottomDialogManager;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityInitialValueBinding;
import com.pfl.module_user.di.module_initialvalue.DaggerInitialValueComponent;
import com.pfl.module_user.di.module_initialvalue.InitialValueModule;
import com.pfl.module_user.view.InitialValueView;
import com.pfl.module_user.viewmodel.InitialValueViewModel;

import javax.inject.Inject;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_INITIAL_VALUE)
public class ModuleUserInitialValueActivity extends BaseActivity<ModuleUserActivityInitialValueBinding> implements InitialValueView, View.OnClickListener {

    @Inject
    InitialValueViewModel valueModule;

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_initial_value;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

        DaggerInitialValueComponent
                .builder()
                .appComponent(appComponent)
                .initialValueModule(new InitialValueModule(this, this))
                .build()
                .inject(this);

    }

    @Override
    public void initView() {
        RxClickUtil.RxClick(mBinding.moduleUserRlNickName, this);
        RxClickUtil.RxClick(mBinding.moduleUserRlGender, this);
        RxClickUtil.RxClick(mBinding.moduleUserCvCommit, this);
    }

    @Override
    public void setToolBar() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.module_user_rl_nick_name) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_INPUT_NICK_NAME);
        } else if (id == R.id.module_user_rl_gender) {
            BottomDialogManager.showGenderDialog(getSupportFragmentManager(), this);
        } else if (id == R.id.lib_common_tv_male) {
            mBinding.moduleUserTvGender.setText("男");
        } else if (id == R.id.lib_common_tv_female) {
            mBinding.moduleUserTvGender.setText("女");
        } else if (id == R.id.module_user_cv_commit) {
            valueModule.requestData();
        }
    }

    @Override
    public void onSuccess() {
        ToastUtils.showShort("提交成功");
        finish();
    }
}
