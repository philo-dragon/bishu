package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.BaseEntyty;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.CarLicence;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.R;
import com.pfl.module_user.view.AddCarView;
import com.pfl.module_user.view.AddHardwareView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2018\7\23 0023.
 */

public class AddHardwareViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private AddHardwareView view;

    public AddHardwareViewModel(LifecycleProvider lifecycle, RetrofitService service, AddHardwareView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void addDevice(String imei, String name) {
        service.addDevice("post", imei, name)
                .compose(RxSchedulers.<HttpResponse<Object>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<Object>>() {
                    @Override
                    public void onSuccess(HttpResponse<Object> response) {
                        view.onSuccess();
                    }

                    @Override
                    public void onFail(HttpResponse<Object> response) {
                        super.onFail(response);
                        view.onFail();
                    }

                    @Override
                    public void onException(ExceptionReason reason) {
                        super.onException(reason);
                        view.onFail();
                    }
                });
    }


}
