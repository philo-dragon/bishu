package com.pfl.module_user.di.module_feedback;

import com.pfl.common.di.AppComponent;
import com.pfl.common.di.scope.ActivityScope;
import com.pfl.module_user.activity.ModuleUserFeedBackActivity;
import com.pfl.module_user.activity.ModuleUserLoginActivity;

import dagger.Component;

/**
 * Created by rocky on 2018/1/2.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = FeedbackModule.class)
public interface FeedbackComponent {

    void inject(ModuleUserFeedBackActivity activity);
}
