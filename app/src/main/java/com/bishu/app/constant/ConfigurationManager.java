package com.bishu.app.constant;

import com.pfl.common.entity.module_app.AppConfiguration;
import com.pfl.module_user.constant.UserInfoManager;

/**
 * app的一些配置信息
 */
public class ConfigurationManager {

    private static ConfigurationManager INSTANCE;
    private static AppConfiguration configuration;

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {

        if (null == INSTANCE) {
            synchronized (UserInfoManager.class) {
                if (null == INSTANCE) {
                    INSTANCE = new ConfigurationManager();
                }
            }
        }

        return INSTANCE;
    }

    public AppConfiguration getAppConfiguration() {
        return configuration;
    }

    public void setAppConfiguration(AppConfiguration configuration) {
        ConfigurationManager.configuration = configuration;
    }
}
