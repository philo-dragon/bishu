package com.bishu.app.service.impl;

import android.content.Context;
import android.location.Location;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bishu.app.constant.LocationManager;
import com.pfl.common.service.IAppModelLocationService;
import com.pfl.common.utils.RouteUtils;

/**
 * ARouter跨模块的服务调用
 * 提供其它模块获取位置信息
 */
@Route(path = RouteUtils.APP_LISTENER_LOCATION)
public class AppModelLocatinService implements IAppModelLocationService {

    @Override
    public Location getLocation() {
        return LocationManager.getInstance().getLocation();
    }

    @Override
    public void init(Context context) {

    }
}
