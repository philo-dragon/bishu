package com.pfl.module_user.view;

import com.pfl.common.entity.module_user.User;

/**
 * Created by rocky on 2018/1/2.
 */

public interface LoginView {

    /**
     * 验证手机号是否注册
     *
     * @param isRegist
     */
    void verifyMobule(boolean isRegist);

    void loginSuccess(User user);

    void loginFailed();

    void onFail(int errorCode);
}
