package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.AccessToken;
import com.pfl.common.entity.module_user.ScoreLog;
import com.pfl.common.http.RetrofitFactory;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.FeedbackView;
import com.pfl.module_user.view.WalletView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.List;

/**
 * Created by rocky on 2018/4/9.
 */

public class WalletViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private WalletView view;

    public WalletViewModel(LifecycleProvider lifecycle, RetrofitService service, WalletView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void requestData() {
        RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .score_log("282307895618", "b9c6c8f954dbbf7274910585a95efce1")
                .compose(RxSchedulers.<List<ScoreLog>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<List<ScoreLog>>() {
                    @Override
                    public void onNext(List<ScoreLog> scoreLogs) {
                        view.onSuccess(scoreLogs);
                    }
                });
    }
}