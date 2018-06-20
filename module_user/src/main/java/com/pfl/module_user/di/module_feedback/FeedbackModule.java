package com.pfl.module_user.di.module_feedback;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.FeedbackView;
import com.pfl.module_user.view.LoginView;
import com.pfl.module_user.viewmodel.FeedbackViewModel;
import com.pfl.module_user.viewmodel.LoginViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class FeedbackModule {

    private LifecycleProvider lifecycle;
    private FeedbackView view;

    public FeedbackModule(LifecycleProvider lifecycle, FeedbackView feedbackView) {
        this.lifecycle = lifecycle;
        this.view = feedbackView;
    }

    @Provides
    FeedbackView provideFeedbackView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    FeedbackViewModel provideFeedbackViewModel(LifecycleProvider lifecycle, RetrofitService service, FeedbackView view) {

        return new FeedbackViewModel(lifecycle, service, view);
    }
}
