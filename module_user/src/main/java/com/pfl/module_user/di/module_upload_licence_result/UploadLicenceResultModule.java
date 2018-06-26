package com.pfl.module_user.di.module_upload_licence_result;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.StorageTokenView;
import com.pfl.module_user.view.UploadIndentityResultView;
import com.pfl.module_user.view.UploadLicenceResultView;
import com.pfl.module_user.viewmodel.StorageTokenViewModel;
import com.pfl.module_user.viewmodel.UploadIndentityResultViewModel;
import com.pfl.module_user.viewmodel.UploadLicenceResultViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class UploadLicenceResultModule {

    private LifecycleProvider lifecycle;
    private UploadLicenceResultView view;
    private StorageTokenView tokenView;

    public UploadLicenceResultModule(LifecycleProvider lifecycle, UploadLicenceResultView feedbackView, StorageTokenView tokenView) {
        this.lifecycle = lifecycle;
        this.view = feedbackView;
        this.tokenView = tokenView;
    }

    @Provides
    UploadLicenceResultView provideFeedbackView() {
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
    UploadLicenceResultViewModel provideUploadIndentityResultViewModel(LifecycleProvider lifecycle, RetrofitService service, UploadLicenceResultView view) {

        return new UploadLicenceResultViewModel(lifecycle, service, view);
    }

    @Provides
    StorageTokenViewModel provideStorageTokenViewModel(LifecycleProvider lifecycle, RetrofitService service, StorageTokenView view) {

        return new StorageTokenViewModel(lifecycle, service, view);
    }
}
