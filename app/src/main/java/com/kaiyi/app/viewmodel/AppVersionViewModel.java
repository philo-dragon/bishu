package com.kaiyi.app.viewmodel;

import com.kaiyi.app.view.AppVersionView;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_app.AppConfiguration;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by Administrator on 2018\6\22 0022.
 */

public class AppVersionViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private AppVersionView view;

    public AppVersionViewModel(LifecycleProvider lifecycle, RetrofitService service, AppVersionView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void requestData() {
        service
                .configuration("get")
                .compose(RxSchedulers.<HttpResponse<AppConfiguration>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<AppConfiguration>>() {
                    @Override
                    public void onNext(HttpResponse<AppConfiguration> response) {
                        view.onSuccess(response.getData());
                    }
                });
    }

}
