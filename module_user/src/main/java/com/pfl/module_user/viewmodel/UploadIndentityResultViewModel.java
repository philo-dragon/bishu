package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.module_user.UserIndentity;
import com.pfl.common.http.RetrofitFactory;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.FeedbackView;
import com.pfl.module_user.view.UploadIndentityResultView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by rocky on 2018/4/9.
 */

public class UploadIndentityResultViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private UploadIndentityResultView view;

    public UploadIndentityResultViewModel(LifecycleProvider lifecycle, RetrofitService service, UploadIndentityResultView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void getIdentity(String uid) {
        RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .getIdentity(uid)
                .compose(RxSchedulers.<UserIndentity>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<UserIndentity>() {
                    @Override
                    public void onNext(UserIndentity indentity) {
                        view.onSuccess(indentity);
                    }
                });
    }
}
