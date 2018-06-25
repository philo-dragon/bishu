package com.pfl.module_user.di.module_upload_licence_result;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.UploadIndentityResultView;
import com.pfl.module_user.view.UploadLicenceResultView;
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

    public UploadLicenceResultModule(LifecycleProvider lifecycle, UploadLicenceResultView feedbackView) {
        this.lifecycle = lifecycle;
        this.view = feedbackView;
    }

    @Provides
    UploadLicenceResultView provideFeedbackView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    UploadLicenceResultViewModel provideUploadIndentityResultViewModel(LifecycleProvider lifecycle, RetrofitService service, UploadLicenceResultView view) {

        return new UploadLicenceResultViewModel(lifecycle, service, view);
    }
}
