package com.pfl.module_user.di.module_upload_identity;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.StorageTokenView;
import com.pfl.module_user.view.UploadIndentityView;
import com.pfl.module_user.viewmodel.StorageTokenViewModel;
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
    private StorageTokenView tokenView;

    public UploadIndentityModule(LifecycleProvider lifecycle, UploadIndentityView feedbackView, StorageTokenView tokenView) {
        this.lifecycle = lifecycle;
        this.view = feedbackView;
        this.tokenView = tokenView;
    }

    @Provides
    UploadIndentityView provideFeedbackView() {
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
    UploadIndentityViewModel provideUploadIndentityViewModel(LifecycleProvider lifecycle, RetrofitService service, UploadIndentityView view) {

        return new UploadIndentityViewModel(lifecycle, service, view);
    }

    @Provides
    StorageTokenViewModel provideStorageTokenViewModel(LifecycleProvider lifecycle, RetrofitService service, StorageTokenView view) {

        return new StorageTokenViewModel(lifecycle, service, view);
    }
}
