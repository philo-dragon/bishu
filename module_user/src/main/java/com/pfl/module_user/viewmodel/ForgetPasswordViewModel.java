package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.FindBean;
import com.pfl.common.entity.module_user.VerifySMSResult;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.ForgetPasswordView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
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

    public void sendSMS(String mobile) {
        service
                .sendSMSCode("post", mobile)
                .compose(RxSchedulers.<HttpResponse<Object>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<Object>>() {
                    @Override
                    public void onSuccess(HttpResponse<Object> response) {

                    }
                });
    }

    public void verifySMS(String mobile, String verify_code) {
        service
                .checkSMSCode("get", mobile, verify_code)
                .compose(RxSchedulers.<HttpResponse<VerifySMSResult>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<VerifySMSResult>>() {
                    @Override
                    public void onSuccess(HttpResponse<VerifySMSResult> result) {
                        view.smsVerifyResult(true);
                    }

                    @Override
                    public void onFail(HttpResponse<VerifySMSResult> response) {
                        super.onFail(response);
                        view.smsVerifyResult(false);
                    }
                });
    }

    public void forgetPassword(String mobile, String pwd) {

        service
                .forgetPassword("put", mobile, pwd)
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
