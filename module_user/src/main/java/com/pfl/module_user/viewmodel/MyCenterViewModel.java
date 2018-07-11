package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.UserInfo;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.MyCenterView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

/**
 * Created by rocky on 2018/4/9.
 */

public class MyCenterViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private MyCenterView view;


    public MyCenterViewModel(LifecycleProvider lifecycle, RetrofitService service, MyCenterView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void requestData(String uid) {
        service
                .userInfo("put", uid)
                .compose(RxSchedulers.<HttpResponse<UserInfo>>compose())
                .compose(lifecycle.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<UserInfo>>() {
                    @Override
                    public void onSuccess(HttpResponse<UserInfo> response) {
                        view.onSuccess(response.getData());
                    }
                });
    }

}
