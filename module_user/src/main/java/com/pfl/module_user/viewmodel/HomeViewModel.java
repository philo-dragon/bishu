package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.AccessToken;
import com.pfl.common.entity.module_user.Score;
import com.pfl.common.http.RetrofitFactory;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.FeedbackView;
import com.pfl.module_user.view.HomeView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

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
        Score score = new Score();
        score.setScore("11110000");
        score.setScore_add_yesterday("5000");
        view.onSuccess(score);
       /* RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .score()
                .compose(RxSchedulers.<Score>compose())
                .compose(lifecycle.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new BaseObserver<Score>() {
                    @Override
                    public void onNext(Score score) {
                        view.onSuccess(score);
                    }
                });*/
    }
}
