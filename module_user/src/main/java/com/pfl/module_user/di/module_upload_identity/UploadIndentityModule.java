package com.pfl.module_user.di.module_upload_identity;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.UploadIndentityView;
import com.pfl.module_user.viewmodel.UploadIndentityViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class UploadIndentityModule {

    private LifecycleProvider lifecycle;
    private UploadIndentityView view;

    public UploadIndentityModule(LifecycleProvider lifecycle, UploadIndentityView feedbackView) {
        this.lifecycle = lifecycle;
        this.view = feedbackView;
    }

    @Provides
    UploadIndentityView provideFeedbackView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    UploadIndentityViewModel provideUploadIndentityViewModel(LifecycleProvider lifecycle, RetrofitService service, UploadIndentityView view) {

        return new UploadIndentityViewModel(lifecycle, service, view);
    }
}
