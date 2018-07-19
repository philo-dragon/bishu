package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.UserLicence;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.service.ModuleUserRouteService;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.UploadLicenceResultView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rocky on 2018/4/9.
 */

public class UploadLicenceResultViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private UploadLicenceResultView view;

    public UploadLicenceResultViewModel(LifecycleProvider lifecycle, RetrofitService service, UploadLicenceResultView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void getIdentity() {
        service
                .getLicence("get", ModuleUserRouteService.getUser().getUid())
                .compose(RxSchedulers.<HttpResponse<UserLicence>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<UserLicence>>() {
                    @Override
                    public void onSuccess(HttpResponse<UserLicence> response) {
                        view.onSuccess(response.getData());
                    }
                });
    }

    public String dateFormat(String ss) {
        if (ss == null || ss.equals("")) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String time = dateFormat.format(new Date(ss));
        return time;
    }
}
