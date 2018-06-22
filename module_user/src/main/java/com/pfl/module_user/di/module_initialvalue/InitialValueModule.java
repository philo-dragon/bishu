package com.pfl.module_user.di.module_initialvalue;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.HomeView;
import com.pfl.module_user.view.InitialValueView;
import com.pfl.module_user.viewmodel.HomeViewModel;
import com.pfl.module_user.viewmodel.InitialValueViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class InitialValueModule {

    private LifecycleProvider lifecycle;
    private InitialValueView view;

    public InitialValueModule(LifecycleProvider lifecycle, InitialValueView homeView) {
        this.lifecycle = lifecycle;
        this.view = homeView;
    }

    @Provides
    InitialValueView provideHomeView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    InitialValueViewModel provideInitialValueViewModel(LifecycleProvider lifecycle, RetrofitService service, InitialValueView view) {

        return new InitialValueViewModel(lifecycle, service, view);
    }
}
