package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.AccessToken;
import com.pfl.common.entity.module_user.User;
import com.pfl.common.http.RetrofitFactory;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.RegistView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by rocky on 2018/4/9.
 */

public class RegistViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private RegistView view;

    public RegistViewModel(LifecycleProvider lifecycle, RetrofitService service, RegistView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void requestData(String mobile, String password,String invatinCode, String verify_code) {
        RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .doRegist(mobile, password, invatinCode,verify_code)
                .compose(RxSchedulers.<User>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<User>() {
                    @Override
                    public void onNext(User user) {
                        view.onSuccess(user);
                    }
                });
    }
}
