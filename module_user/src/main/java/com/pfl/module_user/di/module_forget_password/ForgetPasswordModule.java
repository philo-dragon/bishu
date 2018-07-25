package com.pfl.module_user.di.module_forget_password;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.FindView;
import com.pfl.module_user.view.ForgetPasswordView;
import com.pfl.module_user.viewmodel.FindViewModel;
import com.pfl.module_user.viewmodel.ForgetPasswordViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class ForgetPasswordModule {

    private LifecycleProvider lifecycle;
    private ForgetPasswordView view;

    public ForgetPasswordModule(LifecycleProvider lifecycle, ForgetPasswordView findView) {
        this.lifecycle = lifecycle;
        this.view = findView;
    }

    @Provides
    ForgetPasswordView provideForgetPasswordView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    ForgetPasswordViewModel provideForgetPasswordViewModel(LifecycleProvider lifecycle, RetrofitService service, ForgetPasswordView view) {

        return new ForgetPasswordViewModel(lifecycle, service, view);
    }
}
