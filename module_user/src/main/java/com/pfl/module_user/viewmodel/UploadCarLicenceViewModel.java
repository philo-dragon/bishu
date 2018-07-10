package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.CarLicence;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.UploadCarLicenceView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by rocky on 2018/4/9.
 */

public class UploadCarLicenceViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private UploadCarLicenceView view;

    public UploadCarLicenceViewModel(LifecycleProvider lifecycle, RetrofitService service, UploadCarLicenceView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void getIdentity(String uid) {
        service
                .getCarLicence(uid)
                .compose(RxSchedulers.<HttpResponse<CarLicence>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<CarLicence>>() {
                    @Override
                    public void onNext(HttpResponse<CarLicence> response) {
                        view.onSuccess(response.getData());
                    }
                });
    }
}
