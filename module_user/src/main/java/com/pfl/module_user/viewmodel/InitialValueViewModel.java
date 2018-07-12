package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.AccessToken;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.InitialValueView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by rocky on 2018/4/9.
 */

public class InitialValueViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private InitialValueView view;

    public InitialValueViewModel(LifecycleProvider lifecycle, RetrofitService service, InitialValueView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    /**
     * @param nickname
     * @param gender   取值范围[0, 1] 分别表示['女', '男']
     */
    public void requestData(String nickname, String gender) {
        service
                .updateUserNameAndGender("put", nickname, gender)
                .compose(RxSchedulers.<HttpResponse<Object>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<Object>>() {
                    @Override
                    public void onSuccess(HttpResponse<Object> accessToken) {
                        view.onSuccess();
                    }
                });
    }
}
