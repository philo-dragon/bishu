package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.FindBean;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.ForgetPasswordView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

/**
 * Created by Administrator on 2018\7\25 0025.
 */

public class ForgetPasswordViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private ForgetPasswordView view;

    public ForgetPasswordViewModel(LifecycleProvider lifecycle, RetrofitService service, ForgetPasswordView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void forgetPassword(String mobile, String pwd) {

        service
                .forgetPassword("put", mobile, pwd)
                .compose(RxSchedulers.<HttpResponse<Object>>compose())
                .compose(lifecycle.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<Object>>() {
                    @Override
                    public void onSuccess(HttpResponse<Object> response) {
                        view.onSuccess();
                    }
                });

    }
}
