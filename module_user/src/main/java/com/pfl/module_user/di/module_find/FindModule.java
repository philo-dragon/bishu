package com.pfl.module_user.di.module_find;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.FindView;
import com.pfl.module_user.view.HomeView;
import com.pfl.module_user.viewmodel.FindViewModel;
import com.pfl.module_user.viewmodel.HomeViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class FindModule {

    private LifecycleProvider lifecycle;
    private FindView view;

    public FindModule(LifecycleProvider lifecycle, FindView findView) {
        this.lifecycle = lifecycle;
        this.view = findView;
    }

    @Provides
    FindView provideHomeView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    FindViewModel provideFindViewModel(LifecycleProvider lifecycle, RetrofitService service, FindView view) {

        return new FindViewModel(lifecycle, service, view);
    }
}
