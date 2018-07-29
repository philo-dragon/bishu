package com.pfl.module_user.constant;


import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pfl.common.entity.module_user.User;
import com.pfl.common.entity.module_user.UserInfo;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class UserInfoManager {

    private static UserInfoManager INSTANCE;
    private static User user;
    private static UserInfo userInfo;

    private UserInfoManager() {
    }

    public static UserInfoManager getInstance() {

        if (null == INSTANCE) {
            synchronized (UserInfoManager.class) {
                if (null == INSTANCE) {
                    INSTANCE = new UserInfoManager();
                }
            }
        }

        return INSTANCE;
    }

    public void setUser(User user) {
        Gson gson = new Gson();
        SPUtils.getInstance("bishu").put("user", gson.toJson(user));
        UserInfoManager.user = user;
    }

    public User getUser() {

        if (UserInfoManager.user == null) {
            String strUser = SPUtils.getInstance("bishu").getString("user", "");
            if (!strUser.equals("")) {
                Gson gson = new Gson();
                User jsonUser = gson.fromJson(strUser, new TypeToken<User>() {
                }.getType());
                UserInfoManager.user = jsonUser;
            }
        }

        return UserInfoManager.user;
    }

    public void exit() {
        user = null;
        userInfo = null;
        SPUtils.getInstance("bishu").remove("user");
        SPUtils.getInstance("bishu").remove("userInfo");
    }

    public UserInfo getUserInfo() {

        if (UserInfoManager.userInfo == null) {
            String strUser = SPUtils.getInstance("bishu").getString("userInfo", "");
            if (!strUser.equals("")) {
                Gson gson = new Gson();
                UserInfo jsonUser = gson.fromJson(strUser, new TypeToken<UserInfo>() {
                }.getType());
                UserInfoManager.userInfo = jsonUser;
            }
        }

        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        Gson gson = new Gson();
        SPUtils.getInstance("bishu").put("userInfo", gson.toJson(userInfo));
        UserInfoManager.userInfo = userInfo;
    }
}
