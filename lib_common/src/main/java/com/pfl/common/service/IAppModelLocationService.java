package com.pfl.common.service;

import android.location.Location;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Created by Administrator on 2018\6\29 0029.
 */

public interface IAppModelLocationService extends IProvider {
    Location getLocation();
}
