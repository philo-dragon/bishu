package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.Device;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.IntelligentHardwareView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.List;

/**
 * Created by Administrator on 2018\6\24 0024.
 */

public class IntelligentHardwareViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private IntelligentHardwareView view;

    public IntelligentHardwareViewModel(LifecycleProvider lifecycle, RetrofitService service, IntelligentHardwareView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void getDevices(String uid) {
        service
                .getDevices(uid)
                .compose(RxSchedulers.<HttpResponse<List<Device>>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<List<Device>>>() {
                    @Override
                    public void onNext(HttpResponse<List<Device>> deviceList) {
                        view.onSuccess(deviceList.getData());
                    }
                });
    }

    public void addDevice(String imei, String type) {
        service
                .addDevice(imei, type)
                .compose(RxSchedulers.<HttpResponse<Object>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<Object>>() {
                    @Override
                    public void onNext(HttpResponse<Object> deviceList) {
                        view.onAddSuccess();
                    }
                });
    }

}
