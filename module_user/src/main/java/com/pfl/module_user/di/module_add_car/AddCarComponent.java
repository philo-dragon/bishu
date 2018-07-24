package com.pfl.module_user.di.module_add_car;

import com.pfl.common.di.AppComponent;
import com.pfl.common.di.scope.ActivityScope;
import com.pfl.module_user.activity.ModuleUserAddCarActivity;
import com.pfl.module_user.fragment.ModuleUserHomeFragment;

import dagger.Component;

/**
 * Created by rocky on 2018/1/2.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = AddCarModule.class)
public interface AddCarComponent {

    void inject(ModuleUserAddCarActivity activity);
}
