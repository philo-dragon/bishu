package com.kaiyi.app.service.impl;

import android.content.Context;
import android.location.Location;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kaiyi.app.constant.ConfigurationManager;
import com.pfl.common.entity.module_app.AppConfiguration;
import com.pfl.common.service.IAppModelConfigurationService;
import com.pfl.common.service.IAppModelLocationService;
import com.pfl.common.utils.RouteUtils;

/**
 * ARouter跨模块的服务调用
 * 提供其它模块获取位置信息
 */
@Route(path = RouteUtils.APP_LISTENER_CONFIGURATION)
public class AppModelConfigurationService implements IAppModelConfigurationService {

    @Override
    public void init(Context context) {

    }

    @Override
    public AppConfiguration getConfiguration() {
        return ConfigurationManager.getInstance().getAppConfiguration();
    }
}
