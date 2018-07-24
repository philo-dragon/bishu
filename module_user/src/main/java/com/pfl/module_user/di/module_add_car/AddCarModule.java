package com.pfl.module_user.di.module_add_car;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.AddCarView;
import com.pfl.module_user.view.HomeView;
import com.pfl.module_user.viewmodel.AddCarViewModel;
import com.pfl.module_user.viewmodel.HomeViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class AddCarModule {

    private LifecycleProvider lifecycle;
    private AddCarView view;

    public AddCarModule(LifecycleProvider lifecycle, AddCarView homeView) {
        this.lifecycle = lifecycle;
        this.view = homeView;
    }

    @Provides
    AddCarView provideAddCarView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    AddCarViewModel provideAddCarViewModel(LifecycleProvider lifecycle, RetrofitService service, AddCarView view) {

        return new AddCarViewModel(lifecycle, service, view);
    }
}
