package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.AccessToken;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.InitialValueView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by rocky on 2018/4/9.
 */

public class InitialValueViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private InitialValueView view;

    public InitialValueViewModel(LifecycleProvider lifecycle, RetrofitService service, InitialValueView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void requestData() {
        service
                .getToken("post", "client_credentials", "282307895618", "b9c6c8f954dbbf7274910585a95efce1")
                .compose(RxSchedulers.<HttpResponse<AccessToken>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<AccessToken>>() {
                    @Override
                    public void onSuccess(HttpResponse<AccessToken> accessToken) {
                        view.onSuccess();
                    }
                });
    }
}
