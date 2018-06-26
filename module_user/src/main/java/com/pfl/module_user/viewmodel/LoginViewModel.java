package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.AccessToken;
import com.pfl.common.entity.module_user.User;
import com.pfl.common.http.RetrofitFactory;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.LoginView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import javax.inject.Inject;

/**
 * Created by rocky on 2018/4/9.
 */

public class LoginViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private LoginView view;

    public LoginViewModel(LifecycleProvider lifecycle, RetrofitService service, LoginView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void requestData(String mobile, String pwd) {
        RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .doLogin(mobile, pwd)
                .compose(RxSchedulers.<User>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<User>() {
                    @Override
                    public void onNext(User user) {
                        view.loginSuccess(user);
                    }
                });
    }
}
