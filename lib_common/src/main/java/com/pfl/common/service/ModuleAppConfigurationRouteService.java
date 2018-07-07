package com.pfl.common.service;

import com.alibaba.android.arouter.launcher.ARouter;
import com.pfl.common.entity.module_app.AppConfiguration;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class ModuleAppConfigurationRouteService {

    public static AppConfiguration getConfiguration() {
        IAppModelConfigurationService configurationService = ARouter.getInstance().navigation(IAppModelConfigurationService.class);

        if (null != configurationService) {
            return configurationService.getConfiguration();
        }

        return null;
    }

}
