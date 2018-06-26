package com.pfl.module_user.di.module_upload_licence;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.UploadLicenceView;
import com.pfl.module_user.viewmodel.UploadLicenceViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class UploadLicenceModule {

    private LifecycleProvider lifecycle;
    private UploadLicenceView view;

    public UploadLicenceModule(LifecycleProvider lifecycle, UploadLicenceView feedbackView) {
        this.lifecycle = lifecycle;
        this.view = feedbackView;
    }

    @Provides
    UploadLicenceView provideFeedbackView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    UploadLicenceViewModel provideUploadIndentityViewModel(LifecycleProvider lifecycle, RetrofitService service, UploadLicenceView view) {

        return new UploadLicenceViewModel(lifecycle, service, view);
    }
}
