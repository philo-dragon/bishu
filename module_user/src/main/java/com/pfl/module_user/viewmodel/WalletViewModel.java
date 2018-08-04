package com.pfl.module_user.viewmodel;

import com.pfl.common.base.MultiTypeAdapter;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.MineTrip;
import com.pfl.common.entity.module_user.ScoreLog;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.BR;
import com.pfl.module_user.R;
import com.pfl.module_user.view.WalletView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by rocky on 2018/4/9.
 */

public class WalletViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private WalletView view;
    private int page = 0;

    public WalletViewModel(LifecycleProvider lifecycle, RetrofitService service, WalletView view) {
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

    private void requestData() {
        service
                .score_log("get")
                .compose(RxSchedulers.<HttpResponse<ScoreLog>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<ScoreLog>>() {
                    @Override
                    public void onSuccess(HttpResponse<ScoreLog> response) {
                        List<ScoreLog.Wallet> data = response.getData().getList();
                        view.onSuccess(page == 0, data);
                        if (page == 0) {
                            view.onRefreshComplete(response.getData().getHas_next() != 0);
                            if (data.isEmpty()) {
                                view.onFail(ExceptionReason.EMPTY_DATA);
                            }
                        } else {
                            view.onLoadmoreComplete(response.getData().getHas_next() != 0);
                        }

                    }

                    @Override
                    public void onFail(HttpResponse<ScoreLog> response) {
                        super.onFail(response);
                        Observable.just("")
                                .compose(RxSchedulers.<String>noCheckNetworkCompose())
                                .subscribe(new Consumer<String>() {
                                    @Override
                                    public void accept(String s) throws Exception {
                                        if (page == 0) {
                                            view.onRefreshComplete(false);
                                            view.onFail(ExceptionReason.EMPTY_DATA);
                                        } else {
                                            page--;
                                            view.onLoadmoreComplete(true);
                                        }
                                    }
                                });
                    }

                    @Override
                    public void onException(ExceptionReason reason) {
                        super.onException(reason);
                        if (page == 0) {
                            view.onRefreshComplete(false);
                            view.onFail(ExceptionReason.EMPTY_DATA);
                        } else {
                            view.onLoadmoreComplete(false);
                        }
                    }
                });
    }
}
