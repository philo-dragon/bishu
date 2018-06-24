package com.pfl.module_user.di.module_my_center;

import com.pfl.common.di.AppComponent;
import com.pfl.common.di.scope.ActivityScope;
import com.pfl.module_user.fragment.ModuleUserMineFragment;
import com.pfl.module_user.fragment.ModuleUserMineTripFragment;

import dagger.Component;

/**
 * Created by rocky on 2018/1/2.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MyCenterModule.class)
public interface MyCenterComponent {

    void inject(ModuleUserMineFragment fragment);
}
