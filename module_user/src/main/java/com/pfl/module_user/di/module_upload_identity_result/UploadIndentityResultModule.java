package com.pfl.module_user.di.module_upload_identity_result;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.FeedbackView;
import com.pfl.module_user.view.UploadIndentityResultView;
import com.pfl.module_user.viewmodel.FeedbackViewModel;
import com.pfl.module_user.viewmodel.UploadIndentityResultViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class UploadIndentityResultModule {

    private LifecycleProvider lifecycle;
    private UploadIndentityResultView view;

    public UploadIndentityResultModule(LifecycleProvider lifecycle, UploadIndentityResultView feedbackView) {
        this.lifecycle = lifecycle;
        this.view = feedbackView;
    }

    @Provides
    UploadIndentityResultView provideFeedbackView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    UploadIndentityResultViewModel provideUploadIndentityResultViewModel(LifecycleProvider lifecycle, RetrofitService service, UploadIndentityResultView view) {

        return new UploadIndentityResultViewModel(lifecycle, service, view);
    }
}
