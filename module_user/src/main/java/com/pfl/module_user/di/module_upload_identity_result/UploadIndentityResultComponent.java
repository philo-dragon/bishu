package com.pfl.module_user.di.module_upload_identity_result;

import com.pfl.common.di.AppComponent;
import com.pfl.common.di.scope.ActivityScope;
import com.pfl.module_user.activity.ModuleUserFeedBackActivity;
import com.pfl.module_user.activity.ModuleUserUploadIdentityCardResultActivity;

import dagger.Component;

/**
 * Created by rocky on 2018/1/2.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = UploadIndentityResultModule.class)
public interface UploadIndentityResultComponent {

    void inject(ModuleUserUploadIdentityCardResultActivity activity);
}
