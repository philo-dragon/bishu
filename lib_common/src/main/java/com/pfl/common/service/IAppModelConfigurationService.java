package com.pfl.common.service;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.pfl.common.entity.module_app.AppConfiguration;

/**
 * Created by Administrator on 2018\6\29 0029.
 */

public interface IAppModelConfigurationService extends IProvider {
    AppConfiguration getConfiguration();
}
