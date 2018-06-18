package com.pfl.module_user.activity;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.BottomDialogManager;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityIntelligentHardwareListBinding;
import com.pfl.module_user.databinding.ModuleUserActivityIntelligentHardwareManagerBinding;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_INTELLIGENT_HARDWARE_LIST)
public class ModuleUserIntelligentHardwareListActivity extends BaseActivity<ModuleUserActivityIntelligentHardwareListBinding> implements View.OnClickListener {


    @Override
    public int getContentView() {
        return R.layout.module_user_activity_intelligent_hardware_list;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
        RxClickUtil.RxClick(mBinding.moduleUserCvAdasInfo, this);
        RxClickUtil.RxClick(mBinding.moduleUserCvJingInfo, this);
        RxClickUtil.RxClick(mBinding.moduleUserCvAddDevice, this);
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
        if (id == R.id.module_user_cv_adas_info) {
            BottomDialogManager.unBindDialog(getSupportFragmentManager(), this);
        } else if (id == R.id.module_user_cv_jing_info) {
            BottomDialogManager.unBindDialog(getSupportFragmentManager(), this);
        } else if (id == R.id.module_user_cv_add_device) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_FIND_DEVICES);
        }
    }
}
