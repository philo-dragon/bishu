package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.Score;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.view.HomeView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rocky on 2018/4/9.
 */

public class HomeViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private HomeView view;

    public HomeViewModel(LifecycleProvider lifecycle, RetrofitService service, HomeView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void requestData() {
        service
                .score("get")
                .compose(RxSchedulers.<HttpResponse<Score>>compose())
                .compose(lifecycle.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<Score>>() {
                    @Override
                    public void onSuccess(HttpResponse<Score> response) {
                        view.onSuccess(response.getData());
                    }
                });
    }

    public void goWallet(String score) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("score", score);
        RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_MY_WALLET, parameters);
    }
}
