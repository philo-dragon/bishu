package com.pfl.module_user.di.module_regist;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.po.ModuleUserPoUser;
import com.pfl.module_user.view.RegistView;
import com.pfl.module_user.viewmodel.RegistViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class RegistModule {

    private LifecycleProvider lifecycle;
    private RegistView view;

    public RegistModule(LifecycleProvider lifecycle, RegistView registView) {
        this.lifecycle = lifecycle;
        this.view = registView;
    }

    @Provides
    RegistView provideRegistView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    RegistViewModel provideRegistViewModel(LifecycleProvider lifecycle, RetrofitService service, RegistView view) {

        return new RegistViewModel(lifecycle, service, view);
    }

    @Provides
    ModuleUserPoUser provideUser() {
        return new ModuleUserPoUser();
    }

}
