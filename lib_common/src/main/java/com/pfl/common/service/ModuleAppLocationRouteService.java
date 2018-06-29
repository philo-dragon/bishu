package com.pfl.common.service;

import android.location.Location;

import com.alibaba.android.arouter.launcher.ARouter;
import com.pfl.common.entity.module_user.User;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class ModuleAppLocationRouteService {

    public static Location getLocation() {
        IAppModelLocationService locationService = ARouter.getInstance().navigation(IAppModelLocationService.class);

        if (null != locationService) {
            return locationService.getLocation();
        }

        return null;
    }

}
