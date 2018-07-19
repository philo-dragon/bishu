package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.UserInfo;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.MyCenterView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

/**
 * Created by rocky on 2018/4/9.
 */

public class MyCenterViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private MyCenterView view;


    public MyCenterViewModel(LifecycleProvider lifecycle, RetrofitService service, MyCenterView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void requestData(String uid) {
        service
                .userInfo("get", uid)
                .compose(RxSchedulers.<HttpResponse<UserInfo>>compose())
                .compose(lifecycle.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<UserInfo>>() {
                    @Override
                    public void onSuccess(HttpResponse<UserInfo> response) {
                        view.onSuccess(response.getData());
                    }
                });
    }

    public String replaceMiddlePhone(String mobile) {
        if (mobile != null && mobile.length() == 11) {
            String phoneNumber = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            return phoneNumber;
        }
        return "";
    }

}
