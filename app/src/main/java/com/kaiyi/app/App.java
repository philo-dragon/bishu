package com.kaiyi.app;

import com.pfl.common.base.BaseApplication;

/**
 * Created by Administrator on 2018\7\7 0007.
 */

public class App extends BaseApplication {

    private String app_dev_url = "http://47.93.101.221:10156/app/api/";
    private String app_release_url = "http://47.93.101.221:10156/app/api/";
    private String app_api_sersion = "http://47.93.101.221:10156/app/api/";

    @Override
    protected void initBaseUrl(String dev_url, String release_url, String api_sersion, boolean isDebug) {
        super.initBaseUrl(app_dev_url, app_release_url, app_api_sersion, BuildConfig.DEBUG);
    }
}
