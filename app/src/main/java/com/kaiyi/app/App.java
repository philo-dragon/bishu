package com.kaiyi.app;

import com.pfl.common.base.BaseApplication;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_app.AppConfiguration;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;

/**
 * Created by Administrator on 2018\7\7 0007.
 */

public class App extends BaseApplication {

    private String app_dev_url = "http://47.93.101.221:10156/";
    private String app_release_url = "http://47.93.101.221:10156/";
    private String app_api_sersion = "v1";

    @Override
    public void onCreate() {
        super.onCreate();
        initAppConfiguration();
    }

    @Override
    protected void initBaseUrl(String dev_url, String release_url, String api_sersion, boolean isDebug) {
        super.initBaseUrl(app_dev_url, app_release_url, app_api_sersion, BuildConfig.DEBUG);
    }

    private void initAppConfiguration() {
        RetrofitService service = getAppComponent().getRetrofitService();
        service
                .configuration("get")
                .compose(RxSchedulers.<HttpResponse<AppConfiguration>>compose())
                .subscribe(new BaseObserver<HttpResponse<AppConfiguration>>() {
                    @Override
                    public void onNext(HttpResponse<AppConfiguration> response) {
                    }
                });

    }
}
