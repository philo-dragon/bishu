package com.pfl.module_user.di.module_intelligent_hardware;

import com.pfl.common.di.AppComponent;
import com.pfl.common.di.scope.ActivityScope;
import com.pfl.module_user.activity.ModuleUserIntelligentHardwareListActivity;
import com.pfl.module_user.activity.ModuleUserLoginActivity;

import dagger.Component;

/**
 * Created by rocky on 2018/1/2.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = IntelligentHardwareModule.class)
public interface IntelligentHardwareComponent {

    void inject(ModuleUserIntelligentHardwareListActivity activity);
}
