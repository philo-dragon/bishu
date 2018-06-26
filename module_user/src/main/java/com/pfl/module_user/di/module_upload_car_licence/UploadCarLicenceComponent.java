package com.pfl.module_user.di.module_upload_car_licence;

import com.pfl.common.di.AppComponent;
import com.pfl.common.di.scope.ActivityScope;
import com.pfl.module_user.activity.ModuleUserUploadDrivingBookActivity;
import com.pfl.module_user.activity.ModuleUserUploadDrivingBookResultActivity;

import dagger.Component;

/**
 * Created by rocky on 2018/1/2.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = UploadCarLicenceModule.class)
public interface UploadCarLicenceComponent {

    void inject(ModuleUserUploadDrivingBookActivity activity);
}
