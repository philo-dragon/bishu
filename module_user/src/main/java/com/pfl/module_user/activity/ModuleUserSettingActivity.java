package com.pfl.module_user.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.App;
import com.pfl.common.utils.DialogManager;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivitySettingBinding;
import com.pfl.module_user.di.module_setting.DaggerSettingComponent;
import com.pfl.module_user.di.module_setting.SettingModule;
import com.pfl.module_user.view.SettingView;
import com.pfl.module_user.viewmodel.SettingViewModel;

import javax.inject.Inject;

/**
 * 设置界面
 */
@Route(path = RouteUtils.MODULE_USER_ACTIVITY_SETTING)
public class ModuleUserSettingActivity extends BaseActivity<ModuleUserActivitySettingBinding> implements SettingView, View.OnClickListener {

    @Inject
    SettingViewModel settingViewModel;


    @Override
    public int getContentView() {
        return R.layout.module_user_activity_setting;
    }

    @Override
    public void componentInject(AppComponent appComponent) {
        DaggerSettingComponent.builder()
                .appComponent(appComponent)
                .settingModule(new SettingModule(this, this))
                .build()
                .inject(this);
    }

    @Override
    public void initView() {
        RxClickUtil.RxClick(mBinding.moduleUserCvAboutUs, this);
        RxClickUtil.RxClick(mBinding.moduleUserCvClearMoney, this);
        RxClickUtil.RxClick(mBinding.moduleUserCvHelpFeedback, this);
        RxClickUtil.RxClick(mBinding.moduleUserCvExitLogin, this);
        RxClickUtil.RxClick(mBinding.moduleUserCvMessage, this);
    }

    @Override
    public void setToolBar() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
    }

    @Override
    public void initData() {
        settingViewModel.requestData();
    }

    @Override
    public void onSuccess(String token) {

        Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        int i = v.getId();
        if (i == R.id.module_user_cv_message) {
            mBinding.moduleUserTextSwitch.setChecked(!mBinding.moduleUserTextSwitch.isChecked());
        } else if (i == R.id.module_user_cv_help_feedback) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_FEED_BACK);
        } else if (i == R.id.module_user_cv_about_us) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_ABOU_US);
        } else if (i == R.id.module_user_cv_clear_money) {
            DialogManager.showTwoBtnDialog(mContext, "确定要清空缓存吗？");
        } else if (i == R.id.module_user_cv_exit_login) {
            DialogManager.showTwoBtnDialog(mContext, "确定要退出登录吗？");
        }

    }

    /**
     * 跳转到应用市场app详情界面
     */
    public void launchAppDetail() {
        try {
            try {
                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(App.getInstance(), "您的手机没有安装Android应用市场", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
