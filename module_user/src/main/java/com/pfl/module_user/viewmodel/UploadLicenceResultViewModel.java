package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.module_user.UserIndentity;
import com.pfl.common.entity.module_user.UserLicence;
import com.pfl.common.http.RetrofitFactory;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.UploadIndentityResultView;
import com.pfl.module_user.view.UploadLicenceResultView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by rocky on 2018/4/9.
 */

public class UploadLicenceResultViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private UploadLicenceResultView view;

    public UploadLicenceResultViewModel(LifecycleProvider lifecycle, RetrofitService service, UploadLicenceResultView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void getIdentity(String uid) {
        RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .getLicence(uid)
                .compose(RxSchedulers.<UserLicence>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<UserLicence>() {
                    @Override
                    public void onNext(UserLicence licence) {
                        view.onSuccess(licence);
                    }
                });
    }
}
