package com.bishu.app.di.app_version;

import com.pfl.common.di.AppComponent;
import com.pfl.common.di.scope.ActivityScope;

import com.bishu.app.ui.MainActivity;
import dagger.Component;

/**
 * Created by rocky on 2018/1/2.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = FindModule.class)
public interface FindComponent {

    void inject(MainActivity activity);
}
