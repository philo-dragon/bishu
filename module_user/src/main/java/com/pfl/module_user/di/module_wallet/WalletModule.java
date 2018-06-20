package com.pfl.module_user.di.module_wallet;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.po.ModuleUserPoUser;
import com.pfl.module_user.view.SettingView;
import com.pfl.module_user.view.WalletView;
import com.pfl.module_user.viewmodel.SettingViewModel;
import com.pfl.module_user.viewmodel.WalletViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class WalletModule {

    private LifecycleProvider lifecycle;
    private WalletView view;

    public WalletModule(LifecycleProvider lifecycle, WalletView walletView) {
        this.lifecycle = lifecycle;
        this.view = walletView;
    }

    @Provides
    WalletView provideWalletView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    WalletViewModel provideWalletViewModel(LifecycleProvider lifecycle, RetrofitService service, WalletView view) {

        return new WalletViewModel(lifecycle, service, view);
    }

}
