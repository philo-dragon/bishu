package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.UserIndentity;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.service.ModuleUserRouteService;
import com.pfl.common.utils.BaseObserver;
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.view.UploadIndentityResultView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rocky on 2018/4/9.
 */

public class UploadIndentityResultViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private UploadIndentityResultView view;

    public UploadIndentityResultViewModel(LifecycleProvider lifecycle, RetrofitService service, UploadIndentityResultView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void getIdentity() {
        service
                .getIdentity("get", ModuleUserRouteService.getUser().getUid())
                .compose(RxSchedulers.<HttpResponse<UserIndentity>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<UserIndentity>>() {
                    @Override
                    public void onSuccess(HttpResponse<UserIndentity> response) {
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
