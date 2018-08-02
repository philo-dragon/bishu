package com.pfl.module_user.di.module_user_add_hardware;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.AddHardwareView;
import com.pfl.module_user.view.WalletView;
import com.pfl.module_user.viewmodel.AddHardwareViewModel;
import com.pfl.module_user.viewmodel.WalletViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class AddHardwareModule {

    private LifecycleProvider lifecycle;
    private AddHardwareView view;

    public AddHardwareModule(LifecycleProvider lifecycle,AddHardwareView addHardwareView) {
        this.lifecycle = lifecycle;
        this.view = addHardwareView;
    }

    @Provides
    AddHardwareView provideAddHardwareView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    AddHardwareViewModel provideWalletViewModel(LifecycleProvider lifecycle, RetrofitService service, AddHardwareView view) {

        return new AddHardwareViewModel(lifecycle, service, view);
    }

}
