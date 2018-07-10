package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.UserLicence;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.UploadLicenceView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by rocky on 2018/4/9.
 */

public class UploadLicenceViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private UploadLicenceView view;

    public UploadLicenceViewModel(LifecycleProvider lifecycle, RetrofitService service, UploadLicenceView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void getIdentity(String uid) {
        service
                .getLicence(uid)
                .compose(RxSchedulers.<HttpResponse<UserLicence>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<UserLicence>>() {
                    @Override
                    public void onNext(HttpResponse<UserLicence> response) {
                        view.onSuccess(response.getData());
                    }
                });
    }
}
