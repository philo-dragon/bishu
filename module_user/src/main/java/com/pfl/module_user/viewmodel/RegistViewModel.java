package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.AccessToken;
import com.pfl.common.entity.module_user.User;
import com.pfl.common.entity.module_user.VerifySMSResult;
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

    public void sendSMS(String mobile) {
        RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .sendSMSCode(mobile)
                .compose(RxSchedulers.<Object>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void onNext(Object user) {

                    }
                });
    }

    public void verifySMS(String mobile, String verify_code) {
        RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .checkSMSCode(mobile, verify_code)
                .compose(RxSchedulers.<VerifySMSResult>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<VerifySMSResult>() {
                    @Override
                    public void onNext(VerifySMSResult result) {



                    }
                });
    }

    public void requestData(String mobile, String password, String invatinCode, String verify_code) {
        RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .doRegist(mobile, password, invatinCode, verify_code)
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
