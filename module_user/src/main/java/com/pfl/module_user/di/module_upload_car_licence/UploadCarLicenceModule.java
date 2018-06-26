package com.pfl.module_user.di.module_upload_car_licence;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.StorageTokenView;
import com.pfl.module_user.view.UploadCarLicenceView;
import com.pfl.module_user.viewmodel.StorageTokenViewModel;
import com.pfl.module_user.viewmodel.UploadCarLicenceViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class UploadCarLicenceModule {

    private LifecycleProvider lifecycle;
    private UploadCarLicenceView view;
    private StorageTokenView tokenView;

    public UploadCarLicenceModule(LifecycleProvider lifecycle, UploadCarLicenceView feedbackView, StorageTokenView tokenView) {
        this.lifecycle = lifecycle;
        this.view = feedbackView;
        this.tokenView = tokenView;
    }

    @Provides
    UploadCarLicenceView provideFeedbackView() {
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
    UploadCarLicenceViewModel provideUploadIndentityViewModel(LifecycleProvider lifecycle, RetrofitService service, UploadCarLicenceView view) {

        return new UploadCarLicenceViewModel(lifecycle, service, view);
    }

    @Provides
    StorageTokenViewModel provideStorageTokenViewModel(LifecycleProvider lifecycle, RetrofitService service, StorageTokenView view) {

        return new StorageTokenViewModel(lifecycle, service, view);
    }
}
