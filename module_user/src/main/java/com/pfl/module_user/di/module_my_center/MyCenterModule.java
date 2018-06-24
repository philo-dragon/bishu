package com.pfl.module_user.di.module_my_center;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.MyCenterView;
import com.pfl.module_user.view.MyTripView;
import com.pfl.module_user.viewmodel.MyCenterViewModel;
import com.pfl.module_user.viewmodel.MyTripViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class MyCenterModule {

    private LifecycleProvider lifecycle;
    private MyCenterView view;

    public MyCenterModule(LifecycleProvider lifecycle, MyCenterView myCenterView) {
        this.lifecycle = lifecycle;
        this.view = myCenterView;
    }

    @Provides
    MyCenterView provideRegistView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    MyCenterViewModel provideMyCenterViewModel(LifecycleProvider lifecycle, RetrofitService service, MyCenterView view) {

        return new MyCenterViewModel(lifecycle, service, view);
    }

}
