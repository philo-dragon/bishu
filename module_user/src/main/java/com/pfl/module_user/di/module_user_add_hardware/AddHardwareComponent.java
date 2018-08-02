package com.pfl.module_user.di.module_user_add_hardware;

import com.pfl.common.di.AppComponent;
import com.pfl.common.di.scope.ActivityScope;
import com.pfl.module_user.activity.ModuleUserAddHardwareActivity;
import com.pfl.module_user.activity.ModuleUserMyWalletActivity;

import dagger.Component;

/**
 * Created by rocky on 2018/1/2.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = AddHardwareModule.class)
public interface AddHardwareComponent {

    void inject(ModuleUserAddHardwareActivity activity);
}
