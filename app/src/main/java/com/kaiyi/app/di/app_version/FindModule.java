package com.kaiyi.app.di.app_version;

import com.kaiyi.app.view.AppVersionView;
import com.kaiyi.app.viewmodel.AppVersionViewModel;
import com.pfl.common.http.RetrofitService;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class FindModule {

    private LifecycleProvider lifecycle;
    private AppVersionView view;

    public FindModule(LifecycleProvider lifecycle, AppVersionView findView) {
        this.lifecycle = lifecycle;
        this.view = findView;
    }

    @Provides
    AppVersionView provideHomeView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    AppVersionViewModel provideFindViewModel(LifecycleProvider lifecycle, RetrofitService service, AppVersionView view) {

        return new AppVersionViewModel(lifecycle, service, view);
    }
}
