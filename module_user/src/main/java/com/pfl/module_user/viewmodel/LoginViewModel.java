package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.User;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.LoginView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

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

    public void findUser(String mobile) {
        service
                .findUser(mobile)
                .compose(RxSchedulers.<HttpResponse<User>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<User>>() {
                    @Override
                    public void onNext(HttpResponse<User> response) {
                        User user = response.getData();
                        if (user == null || user.getUid() == null || user.getUid().equals("")) {
                            view.verifyMobule(false);
                        } else {
                            view.verifyMobule(true);
                        }
                    }
                });
    }

    public void requestData(String mobile, String pwd) {
        service
                .doLogin(mobile, pwd)
                .compose(RxSchedulers.<HttpResponse<User>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<User>>() {
                    @Override
                    public void onSuccess(HttpResponse<User> response) {
                        view.loginSuccess(response.getData());
                    }
                });
    }
}
