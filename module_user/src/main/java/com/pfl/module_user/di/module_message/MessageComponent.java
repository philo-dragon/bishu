package com.pfl.module_user.di.module_message;

import com.pfl.common.di.AppComponent;
import com.pfl.common.di.scope.ActivityScope;
import com.pfl.module_user.activity.ModuleUserMessageActivity;
import com.pfl.module_user.fragment.ModuleUserHomeFragment;

import dagger.Component;

/**
 * Created by rocky on 2018/1/2.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MessageModule.class)
public interface MessageComponent {

    void inject(ModuleUserMessageActivity activity);
}
