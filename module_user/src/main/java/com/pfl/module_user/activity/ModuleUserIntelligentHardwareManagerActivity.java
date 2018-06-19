package com.pfl.module_user.activity;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.BottomDialogManager;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityIntelligentHardwareManagerBinding;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_INTELLIGENT_HARDWARE_MANAGER)
public class ModuleUserIntelligentHardwareManagerActivity extends BaseActivity<ModuleUserActivityIntelligentHardwareManagerBinding> implements View.OnClickListener {


    @Override
    public int getContentView() {
        return R.layout.module_user_activity_intelligent_hardware_manager;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
        RxClickUtil.RxClick(mBinding.moduleUserRlSoundSetting, this);
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
        if (id == R.id.module_user_rl_sound_setting) {
            BottomDialogManager.soundSettingDialog(getSupportFragmentManager(), this);
        } else if (id == R.id.lib_common_tv_male_voice) {//男声
            mBinding.moduleUserTvCurrentVoice.setText("男声");
        } else if (id == R.id.lib_common_tv_female_voice) {//女声
            mBinding.moduleUserTvCurrentVoice.setText("女声");
        }
    }
}
