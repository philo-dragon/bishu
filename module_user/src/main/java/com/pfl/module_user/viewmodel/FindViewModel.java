package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.AccessToken;
import com.pfl.common.entity.module_user.FindBean;
import com.pfl.common.http.RetrofitFactory;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.FindView;
import com.pfl.module_user.view.MyTripView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

/**
 * Created by rocky on 2018/4/9.
 */

public class FindViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private FindView view;

    public FindViewModel(LifecycleProvider lifecycle, RetrofitService service, FindView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void requestData() {
        RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .find()
                .compose(RxSchedulers.<FindBean>compose())
                .compose(lifecycle.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new BaseObserver<FindBean>() {
                    @Override
                    public void onNext(FindBean findBean) {
                        view.onSuccess(findBean);
                    }
                });
    }
}
