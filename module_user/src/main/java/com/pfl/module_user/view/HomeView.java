package com.pfl.module_user.view;

import com.pfl.common.entity.module_user.Score;
import com.pfl.common.entity.module_user.User;
import com.pfl.common.entity.module_user.UserInfo;

/**
 * Created by rocky on 2018/1/2.
 */

public interface HomeView {

    void onSuccess(Score score);

    void onFindUserInfo(UserInfo userInfo);

}
