package com.pfl.module_user.di.module_home;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.FeedbackView;
import com.pfl.module_user.view.HomeView;
import com.pfl.module_user.viewmodel.FeedbackViewModel;
import com.pfl.module_user.viewmodel.HomeViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class HomeModule {

    private LifecycleProvider lifecycle;
    private HomeView view;

    public HomeModule(LifecycleProvider lifecycle, HomeView homeView) {
        this.lifecycle = lifecycle;
        this.view = homeView;
    }

    @Provides
    HomeView provideHomeView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    HomeViewModel provideHomeViewModel(LifecycleProvider lifecycle, RetrofitService service, HomeView view) {

        return new HomeViewModel(lifecycle, service, view);
    }
}
