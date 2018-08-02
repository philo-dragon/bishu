package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.BaseEntyty;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.CarLicence;
import com.pfl.common.entity.module_user.Device;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.R;
import com.pfl.module_user.view.IntelligentHardwareView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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

    public void deleteDevice(final int position, String id) {
        service
                .deleteDevice("delete", id)
                .compose(RxSchedulers.<HttpResponse<Object>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<Object>>() {
                    @Override
                    public void onSuccess(HttpResponse<Object> deviceList) {
                        view.onDeleteSuccess(position);
                    }
                });
    }

    public void getDevices(String uid) {
        service.getDevices("get", uid)
                .compose(RxSchedulers.<HttpResponse<Device>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<Device>>() {
                    @Override
                    public void onError(Throwable e) {
                        List<BaseEntyty> baseEntyties = new ArrayList<>();
                        setData(baseEntyties);
                    }

                    @Override
                    public void onSuccess(HttpResponse<Device> response) {
                        List<Device.DeviceBean> list = response.getData().getList();
                        Observable.fromIterable(list)
                                .compose(RxSchedulers.<Device.DeviceBean>compose())
                                .map(new Function<Device.DeviceBean, BaseEntyty>() {
                                    @Override
                                    public BaseEntyty apply(Device.DeviceBean deviceBean) throws Exception {
                                        deviceBean.setItemType(R.layout.module_user_item_intelligent_harddware_layout);
                                        return deviceBean;
                                    }
                                })
                                .toList()
                                .subscribe(new Consumer<List<BaseEntyty>>() {
                                    @Override
                                    public void accept(List<BaseEntyty> baseEntyties) throws Exception {
                                        setData(baseEntyties);
                                    }
                                });
                    }

                    @Override
                    public void onFail(HttpResponse<Device> response) {
                        List<BaseEntyty> baseEntyties = new ArrayList<>();
                        setData(baseEntyties);
                    }
                });
    }

    private void setData(List<BaseEntyty> baseEntyties) {
        Device.DeviceBean licenceBean = new Device.DeviceBean();
        licenceBean.setItemType(R.layout.module_user_item_add_intelligent_harddware_layout);
        baseEntyties.add(licenceBean);
        view.onSuccess(baseEntyties);
        view.onRefreshCompelete();
    }

}
