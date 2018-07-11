package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.FeedbackView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.functions.Predicate;

/**
 * Created by rocky on 2018/4/9.
 */

public class FeedbackViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private FeedbackView view;

    public FeedbackViewModel(LifecycleProvider lifecycle, RetrofitService service, FeedbackView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void feedback(String content) {
        service.feedback("post", content)
                .compose(RxSchedulers.<HttpResponse<Object>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<Object>>() {
                    @Override
                    public void onSuccess(HttpResponse<Object> accessToken) {
                        view.feedbackSuccess();
                    }
                });
    }
}
