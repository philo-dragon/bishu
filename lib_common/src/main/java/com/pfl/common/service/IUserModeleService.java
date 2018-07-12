package com.pfl.common.service;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.pfl.common.entity.module_user.User;
import com.pfl.common.entity.module_user.UserInfo;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public interface IUserModeleService extends IProvider {
    User getUser();

    UserInfo getUserInfo();
}
