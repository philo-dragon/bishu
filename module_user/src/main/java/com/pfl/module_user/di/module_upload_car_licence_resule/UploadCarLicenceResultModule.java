package com.pfl.module_user.di.module_upload_car_licence_resule;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.StorageTokenView;
import com.pfl.module_user.view.UploadCarLicenceResultView;
import com.pfl.module_user.viewmodel.StorageTokenViewModel;
import com.pfl.module_user.viewmodel.UploadCarLicenceResultViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class UploadCarLicenceResultModule {

    private LifecycleProvider lifecycle;
    private UploadCarLicenceResultView view;
    private StorageTokenView tokenView;

    public UploadCarLicenceResultModule(LifecycleProvider lifecycle, UploadCarLicenceResultView feedbackView, StorageTokenView tokenView) {
        this.lifecycle = lifecycle;
        this.view = feedbackView;
        this.tokenView = tokenView;
    }

    @Provides
    UploadCarLicenceResultView provideFeedbackView() {
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
    UploadCarLicenceResultViewModel provideUploadIndentityResultViewModel(LifecycleProvider lifecycle, RetrofitService service, UploadCarLicenceResultView view) {

        return new UploadCarLicenceResultViewModel(lifecycle, service, view);
    }

    @Provides
    StorageTokenViewModel provideStorageTokenViewModel(LifecycleProvider lifecycle, RetrofitService service, StorageTokenView view) {

        return new StorageTokenViewModel(lifecycle, service, view);
    }
}
