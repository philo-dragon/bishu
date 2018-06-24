package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.AccessToken;
import com.pfl.common.http.RetrofitFactory;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.SettingView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by rocky on 2018/4/9.
 */

public class SettingViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private SettingView view;

    public SettingViewModel(LifecycleProvider lifecycle, RetrofitService service, SettingView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void requestData() {
        RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .getToken("client_credentials", "282307895618", "b9c6c8f954dbbf7274910585a95efce1")
                .compose(RxSchedulers.<AccessToken>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<AccessToken>() {
                    @Override
                    public void onNext(AccessToken accessToken) {
                        view.onSuccess(accessToken.getAccess_token());
                    }
                });
    }

    public void logOut(String uid) {
        RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .loginOut(uid)
                .compose(RxSchedulers.<Object>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void onNext(Object accessToken) {



                    }
                });
    }
}
