package com.pfl.module_user.di.module_intelligent_hardware;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.IntelligentHardwareView;
import com.pfl.module_user.view.LoginView;
import com.pfl.module_user.viewmodel.IntelligentHardwareViewModel;
import com.pfl.module_user.viewmodel.LoginViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class IntelligentHardwareModule {

    private LifecycleProvider lifecycle;
    private IntelligentHardwareView view;

    public IntelligentHardwareModule(LifecycleProvider lifecycle, IntelligentHardwareView loginView) {
        this.lifecycle = lifecycle;
        this.view = loginView;
    }

    @Provides
    IntelligentHardwareView provideLoginView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    IntelligentHardwareViewModel provideLoginViewModel(LifecycleProvider lifecycle, RetrofitService service, IntelligentHardwareView view) {

        return new IntelligentHardwareViewModel(lifecycle, service, view);
    }
}
