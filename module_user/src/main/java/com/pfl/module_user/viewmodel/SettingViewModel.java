package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.AccessToken;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.User;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.service.ModuleUserRouteService;
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

    public void logOut(String uid) {
        service
                .loginOut("delete", uid)
                .compose(RxSchedulers.<HttpResponse<Object>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<Object>>() {
                    @Override
                    public void onSuccess(HttpResponse<Object> response) {
                        view.onSuccess();
                    }
                });
    }
}
