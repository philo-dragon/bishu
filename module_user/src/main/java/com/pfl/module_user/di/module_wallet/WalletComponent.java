package com.pfl.module_user.di.module_wallet;

import com.pfl.common.di.AppComponent;
import com.pfl.common.di.scope.ActivityScope;
import com.pfl.module_user.activity.ModuleUserMyWalletActivity;
import com.pfl.module_user.activity.ModuleUserSettingActivity;

import dagger.Component;

/**
 * Created by rocky on 2018/1/2.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = WalletModule.class)
public interface WalletComponent {

    void inject(ModuleUserMyWalletActivity activity);
}
