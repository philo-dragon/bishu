package com.pfl.module_user.di.module_upload_car_licence_resule;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.UploadCarLicenceResultView;
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

    public UploadCarLicenceResultModule(LifecycleProvider lifecycle, UploadCarLicenceResultView feedbackView) {
        this.lifecycle = lifecycle;
        this.view = feedbackView;
    }

    @Provides
    UploadCarLicenceResultView provideFeedbackView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    UploadCarLicenceResultViewModel provideUploadIndentityResultViewModel(LifecycleProvider lifecycle, RetrofitService service, UploadCarLicenceResultView view) {

        return new UploadCarLicenceResultViewModel(lifecycle, service, view);
    }
}
