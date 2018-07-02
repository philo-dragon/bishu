package com.pfl.module_user.viewmodel;

import com.pfl.common.base.MultiTypeAdapter;
import com.pfl.common.entity.base.AccessToken;
import com.pfl.common.http.RetrofitFactory;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.BR;
import com.pfl.module_user.R;
import com.pfl.module_user.entity.MineTripBean;
import com.pfl.module_user.view.MyTripView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rocky on 2018/4/9.
 */

public class MyTripViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private MyTripView view;

    private int page = 1;
    private int pageSize = 20;

    public MyTripViewModel(LifecycleProvider lifecycle, RetrofitService service, MyTripView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void requestData() {

        view.onSuccess(getData());

        RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .myTrip(String.valueOf(page), String.valueOf(pageSize))
                .compose(RxSchedulers.<List<com.pfl.common.entity.module_user.MineTripBean>>compose())
                .compose(lifecycle.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new BaseObserver<List<com.pfl.common.entity.module_user.MineTripBean>>() {
                    @Override
                    public void onNext(List<com.pfl.common.entity.module_user.MineTripBean> accessToken) {
                        //view.onSuccess(getData());
                    }
                });
    }

    public List<MultiTypeAdapter.IItem> getData() {

        List<MultiTypeAdapter.IItem> tripBeans = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            MineTripBean tripBean = new MineTripBean();
            tripBean.setStartTime("2018-05-25 16:12");
            tripBean.setEndTime("2018-05-26 13:54");
            tripBean.setMoney(i % 2 == 0 ? "+5" : "0");
            tripBean.setName(i % 2 == 0 ? "已售行程" : "待售行程");
            tripBean.setType(i % 2 == 0 ? 1 : 0);
            tripBean.setLayout(R.layout.module_user_item_my_trip);
            tripBean.setVariableId(BR.item);
            tripBeans.add(tripBean);
        }

        return tripBeans;
    }
}
