package com.kaiyi.app.viewmodel;

import com.kaiyi.app.view.AppVersionView;
import com.pfl.common.entity.module_app.AppConfiguration;
import com.pfl.common.http.RetrofitFactory;
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
        RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .configuration("", "")
                .compose(RxSchedulers.<AppConfiguration>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<AppConfiguration>() {
                    @Override
                    public void onNext(AppConfiguration configuration) {
                        view.onSuccess(configuration);
                    }
                });
    }

}
