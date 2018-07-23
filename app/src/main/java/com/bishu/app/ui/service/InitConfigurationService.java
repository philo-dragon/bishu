package com.bishu.app.ui.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bishu.app.BiShuApp;
import com.bishu.app.constant.ConfigurationManager;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_app.AppConfiguration;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.App;
import com.pfl.common.utils.BaseObserver;
import com.pfl.common.utils.RouteUtils;

/**
 * Created by Administrator on 2018\7\22 0022.
 */

@Route(path = RouteUtils.APP_SERVICE_INIT_CONFIGURATION)
public class InitConfigurationService extends IntentService {

    public InitConfigurationService() {
        super(InitConfigurationService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        initAppConfiguration();
    }

    private void initAppConfiguration() {

        RetrofitService service = App.getInstance(BiShuApp.class).getAppComponent().getRetrofitService();
        service
                .configuration("get")
                .compose(RxSchedulers.<HttpResponse<AppConfiguration>>compose())
                .subscribe(new BaseObserver<HttpResponse<AppConfiguration>>() {
                    @Override
                    public void onSuccess(HttpResponse<AppConfiguration> response) {
                        ConfigurationManager.getInstance().setAppConfiguration(response.getData());
                    }
                });

    }

    public static void actionStart(Context context) {
        context.startService(new Intent(context, InitConfigurationService.class));
    }


}
