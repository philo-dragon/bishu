package com.pfl.module_user.di.module_my_trip;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.po.ModuleUserPoUser;
import com.pfl.module_user.view.MyTripView;
import com.pfl.module_user.view.RegistView;
import com.pfl.module_user.viewmodel.MyTripViewModel;
import com.pfl.module_user.viewmodel.RegistViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class MyTripModule {

    private LifecycleProvider lifecycle;
    private MyTripView view;

    public MyTripModule(LifecycleProvider lifecycle, MyTripView myTripView) {
        this.lifecycle = lifecycle;
        this.view = myTripView;
    }

    @Provides
    MyTripView provideRegistView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    MyTripViewModel provideMyTripViewModel(LifecycleProvider lifecycle, RetrofitService service, MyTripView view) {

        return new MyTripViewModel(lifecycle, service, view);
    }

}
