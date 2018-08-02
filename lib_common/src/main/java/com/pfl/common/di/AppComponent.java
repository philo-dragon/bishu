package com.pfl.common.di;

import android.app.Application;

import com.google.gson.Gson;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.imageloader.ImageLoader;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mertsimsek on 13/01/17.
 */

@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent {

    Application getApplication();

    RetrofitService getRetrofitService();

    ImageLoader getImageLoader();

    Gson getGson();

}