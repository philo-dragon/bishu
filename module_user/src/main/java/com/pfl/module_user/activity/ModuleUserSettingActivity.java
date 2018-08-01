package com.pfl.module_user.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.UserManager;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.service.ModuleUserRouteService;
import com.pfl.common.utils.App;
import com.pfl.common.utils.DataCleanManager;
import com.pfl.common.utils.DialogManager;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.constant.UserInfoManager;
import com.pfl.module_user.databinding.ModuleUserActivitySettingBinding;
import com.pfl.module_user.di.module_setting.DaggerSettingComponent;
import com.pfl.module_user.di.module_setting.SettingModule;
import com.pfl.module_user.utils.SelectPictureHelper;
import com.pfl.module_user.view.SettingView;
import com.pfl.module_user.viewmodel.SettingViewModel;

import java.io.File;
import java.sql.DatabaseMetaData;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 设置界面
 */
@Route(path = RouteUtils.MODULE_USER_ACTIVITY_SETTING)
public class ModuleUserSettingActivity extends BaseActivity<ModuleUserActivitySettingBinding> implements SettingView, View.OnClickListener {

    @Inject
    SettingViewModel viewModel;


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
        getCacheSize();
    }

    @Override
    public void initData() {
    }

    @Override
    public void onSuccess() {
        UserInfoManager.getInstance().exit();
        RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_LOGIN);
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
            DialogManager.showTwoBtnDialog(mContext, "确定要清空缓存吗？", new DialogManager.SimpleDialogClickListener() {
                @Override
                public void onPositive() {
                    clearCache();
                }
            });
        } else if (i == R.id.module_user_cv_exit_login) {
            DialogManager.showTwoBtnDialog(mContext, "确定要退出登录吗？", new DialogManager.SimpleDialogClickListener() {
                @Override
                public void onPositive() {
                    viewModel.logOut(ModuleUserRouteService.getUser().getUid());
                }
            });
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

    public void getCacheSize() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                long cacheSize = DataCleanManager.getFolderSize(getExternalFilesDir(null)) +
                        DataCleanManager.getFolderSize(new File("/data/data/" + getPackageName() + "/shared_prefs")) +
                        DataCleanManager.getFolderSize(getExternalCacheDir()) +
                        DataCleanManager.getFolderSize(Glide.getPhotoCacheDir(mContext)) +
                        DataCleanManager.getFolderSize(SelectPictureHelper.getParentFile());
                e.onNext(DataCleanManager.getFormatSize(cacheSize));

            }
        }).compose(RxSchedulers.<String>noCheckNetworkCompose())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String cacheSize) throws Exception {
                        mBinding.moduleUserTvCacheSize.setText(cacheSize);
                    }
                });
    }

    public void clearCache() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                SPUtils.getInstance("bishu").clear(true);
                DataCleanManager.cleanCustomCache("/data/data/" + getPackageName() + "/shared_prefs");
                DataCleanManager.cleanCustomCache(getExternalFilesDir(null).getAbsolutePath());
                DataCleanManager.cleanCustomCache(getExternalCacheDir().getAbsolutePath());
                DataCleanManager.cleanCustomCache(Glide.getPhotoCacheDir(mContext).getAbsolutePath());
                DataCleanManager.cleanCustomCache(SelectPictureHelper.getParentFile().getAbsolutePath());

                e.onNext("0.0B");

            }
        }).compose(RxSchedulers.<String>noCheckNetworkCompose())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String cacheSize) throws Exception {
                        mBinding.moduleUserTvCacheSize.setText(cacheSize);
                    }
                });
    }

}
