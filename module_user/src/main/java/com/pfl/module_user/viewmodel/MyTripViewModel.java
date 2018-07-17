package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.MineTrip;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.MyTripView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

/**
 * Created by rocky on 2018/4/9.
 */

public class MyTripViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private MyTripView view;

    private int page = 0;
    private int pageSize = 20;

    public MyTripViewModel(LifecycleProvider lifecycle, RetrofitService service, MyTripView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void refreshData() {
        page = 0;
        requestData();
    }

    public void loadmoreData() {
        page++;
        requestData();
    }

    public void requestData() {

        service
                .myTrip("get", String.valueOf(page), String.valueOf(pageSize))
                .compose(RxSchedulers.<HttpResponse<MineTrip>>compose())
                .compose(lifecycle.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<MineTrip>>() {
                    @Override
                    public void onNext(HttpResponse<MineTrip> response) {
                        List<MineTrip.MineTripBean> data = response.getData().getList();
                        view.onSuccess(page == 0, data);
                        if (page == 0) {
                            view.onRefreshComplete(response.getData().getHas_next() != 0);
                        } else {
                            view.onLoadmoreComplete(response.getData().getHas_next() != 0);
                        }
                    }
                });
    }


}
