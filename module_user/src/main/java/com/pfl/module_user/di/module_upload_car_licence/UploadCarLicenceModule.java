package com.pfl.module_user.di.module_upload_car_licence;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.UploadCarLicenceView;
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

    public UploadCarLicenceModule(LifecycleProvider lifecycle, UploadCarLicenceView feedbackView) {
        this.lifecycle = lifecycle;
        this.view = feedbackView;
    }

    @Provides
    UploadCarLicenceView provideFeedbackView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    UploadCarLicenceViewModel provideUploadIndentityViewModel(LifecycleProvider lifecycle, RetrofitService service, UploadCarLicenceView view) {

        return new UploadCarLicenceViewModel(lifecycle, service, view);
    }
}
