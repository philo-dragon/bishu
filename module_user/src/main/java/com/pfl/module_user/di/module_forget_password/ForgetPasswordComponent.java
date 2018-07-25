package com.pfl.module_user.di.module_forget_password;

import com.pfl.common.di.AppComponent;
import com.pfl.common.di.scope.ActivityScope;
import com.pfl.module_user.activity.ModuleUserForgetPasswordActivity;
import com.pfl.module_user.fragment.ModuleUserFindFragment;

import dagger.Component;

/**
 * Created by rocky on 2018/1/2.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ForgetPasswordModule.class)
public interface ForgetPasswordComponent {

    void inject(ModuleUserForgetPasswordActivity activity);
}
