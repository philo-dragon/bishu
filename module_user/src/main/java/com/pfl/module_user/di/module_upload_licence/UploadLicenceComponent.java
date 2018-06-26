package com.pfl.module_user.di.module_upload_licence;

import com.pfl.common.di.AppComponent;
import com.pfl.common.di.scope.ActivityScope;
import com.pfl.module_user.activity.ModuleUserUploadDrivingLicenceActivity;
import com.pfl.module_user.activity.ModuleUserUploadDrivingLicenceResultActivity;

import dagger.Component;

/**
 * Created by rocky on 2018/1/2.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = UploadLicenceModule.class)
public interface UploadLicenceComponent {

    void inject(ModuleUserUploadDrivingLicenceActivity activity);
}
