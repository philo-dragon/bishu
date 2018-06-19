package com.pfl.module_user.view;

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

    void loginSuccess();

    void loginFailed();

    void onFail(int errorCode);
}
