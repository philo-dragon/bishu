package com.pfl.module_user.di.module_upload_identity_result;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.FeedbackView;
import com.pfl.module_user.view.StorageTokenView;
import com.pfl.module_user.view.UploadIndentityResultView;
import com.pfl.module_user.viewmodel.FeedbackViewModel;
import com.pfl.module_user.viewmodel.StorageTokenViewModel;
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
    private StorageTokenView tokenView;

    public UploadIndentityResultModule(LifecycleProvider lifecycle, UploadIndentityResultView feedbackView, StorageTokenView tokenView) {
        this.lifecycle = lifecycle;
        this.view = feedbackView;
        this.tokenView = tokenView;
    }

    @Provides
    UploadIndentityResultView provideFeedbackView() {
        return view;
    }

    @Provides
    StorageTokenView provideStorageTokenView() {
        return tokenView;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    UploadIndentityResultViewModel provideUploadIndentityResultViewModel(LifecycleProvider lifecycle, RetrofitService service, UploadIndentityResultView view) {

        return new UploadIndentityResultViewModel(lifecycle, service, view);
    }

    @Provides
    StorageTokenViewModel provideStorageTokenViewModel(LifecycleProvider lifecycle, RetrofitService service, StorageTokenView view) {

        return new StorageTokenViewModel(lifecycle, service, view);
    }
}
