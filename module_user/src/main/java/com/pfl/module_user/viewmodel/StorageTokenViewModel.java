package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.module_user.CarLicence;
import com.pfl.common.entity.module_user.StorageToken;
import com.pfl.common.http.RetrofitFactory;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.StorageTokenView;
import com.pfl.module_user.view.UploadCarLicenceView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by Administrator on 2018\6\26 0026.
 */

public class StorageTokenViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private StorageTokenView view;

    public StorageTokenViewModel(LifecycleProvider lifecycle, RetrofitService service, StorageTokenView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }


    public void getStorageToken(String resource , String req) {
        RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .getStorageToken(resource,req)
                .compose(RxSchedulers.<StorageToken>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<StorageToken>() {
                    @Override
                    public void onNext(StorageToken licence) {
                        view.onStorageToken(licence);
                    }
                });
    }
}
