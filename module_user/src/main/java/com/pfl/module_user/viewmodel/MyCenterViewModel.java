package com.pfl.module_user.viewmodel;

import com.pfl.common.base.MultiTypeAdapter;
import com.pfl.common.entity.module_user.UserInfo;
import com.pfl.common.http.RetrofitFactory;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.entity.MineTripBean;
import com.pfl.module_user.view.MyCenterView;
import com.pfl.module_user.view.MyTripView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;

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

    public void requestData() {

        RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .userInfo()
                .compose(RxSchedulers.<UserInfo>compose())
                .compose(lifecycle.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new BaseObserver<UserInfo>() {
                    @Override
                    public void onNext(UserInfo userInfo) {
                        view.onSuccess(userInfo);
                    }
                });
    }

}
