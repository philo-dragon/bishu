package com.pfl.common.utils;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class RouteUtils {

    /**
     * app 模块
     */
    public static final String APP_MAIN_ACTIVITY = "/app/main";


    public static final String APP_FRAGMENT_FAST_NEWS = "/app/fast_news";

    /**
     * module_user 模块
     */
    public static final String MODULE_USER_ACTIVITY_LOGIN = "/module_user/activity_login";
    public static final String MODULE_USER_ACTIVITY_REGIST = "/module_user/activity_retist";
    public static final String MODULE_USER_ACTIVITY_SETTING = "/module_user/activity_setting";
    public static final String MODULE_USER_ACTIVITY_ACCOUNT_INFO = "/module_user/activity_account_info";
    public static final String MODULE_USER_ACTIVITY_UPDATE_PASSWORD = "/module_user/activity_update_password";
    public static final String MODULE_USER_ACTIVITY_FOCUS_AND_FANS = "/module_user/activity_focus_and_fans";
    public static final String MODULE_USER_ACTIVITY_BUY_GOLD_COINS = "/module_user/activity_buy_gold_coins";
    public static final String MODULE_USER_ACTIVITY_GOLD_DETAIL = "/module_user/activity_gold_detail";
    public static final String MODULE_USER_ACTIVITY_HAS_BUY_RECOMMEND = "/module_user/activity_has_buy_recommend";
    public static final String MODULE_USER_ACTIVITY_MY_FAVORITE = "/module_user/activity_my_favorite";
    public static final String MODULE_USER_ACTIVITY_MY_MESSAGE = "/module_user/activity_my_message";
    public static final String MODULE_USER_ACTIVITY_APPLY_SFS = "/module_user/activity_apply_sfs";
    public static final String MODULE_USER_ACTIVITY_FEED_BACK = "/module_user/activity_feed_back";
    public static final String MODULE_USER_ACTIVITY_HOME_PAGE = "/module_user/activity_home_page";
    public static final String MODULE_USER_ACTIVITY_SFS_LIST = "/module_user/activity_sfs_list";
    public static final String MODULE_USER_ACTIVITY_MY_WALLET = "/module_user/activity_my_wallet";
    public static final String MODULE_USER_ACTIVITY_H5 = "/module_user/activity_h5";
    public static final String MODULE_USER_ACTIVITY_CHANNEL_MANAGER = "/module_user/activity_channel_manager";
    public static final String MODULE_USER_ACTIVITY_INITIAL_VALUE  = "/module_user/activity_initial_value";
    public static final String MODULE_USER_ACTIVITY_INPUT_NICK_NAME  = "/module_user/activity_input_nick_name";


    public static final String MODULE_USER_FRAGMENT_MINE = "/module_user/fragment_mine";
    public static final String MODULE_USER_FRAGMENT_MINE_TRIP = "/module_user/fragment_mine_trip";
    public static final String MODULE_USER_FRAGMENT_HOME = "/module_user/fragment_home";
    public static final String MODULE_USER_FRAGMENT_FIND = "/module_user/fragment_find";
    public static final String MODULE_USER_FRAGMENT_CHANNEL = "/module_user/fragment_channel";
    public static final String MODULE_USER_SCALE_RECYCLER_VIEW_FRAGMENT = "/module_user/fragment_scale_recycler_view";

    public static final String MODULE_USER_LISTENER_USER_INFO = "/module_user/listener_user_info";


    /** ===================================================================================================================================  **/


    /** ====================== 启动Activity不带动画 ============================= **/
    /**
     * 启动Activity
     *
     * @param path
     */
    public static void actionStart(String path) {
        actionStart(path, new HashMap<String, String>());
    }


    /**
     * 启动Activity
     * parameters 携带参数
     *
     * @param path
     */
    public static void actionStart(String path, Map<String, String> parameters) {
        Postcard build = ARouter.getInstance().build(path);

        for (Map.Entry<String, String> stringStringEntry : parameters.entrySet()) {
            build.withString(stringStringEntry.getKey(), stringStringEntry.getValue());
        }

        build.navigation();
    }


    /** ====================== 启动Activity带有动画 ============================= **/

    /**
     * 启动Activity
     * enterId 进入动画
     * exitId 退出动画
     *
     * @param path
     */
    public static void actionStart(String path, int enterId, int exitId) {
        actionStart(path, new HashMap<String, String>(), enterId, exitId);
    }

    /**
     * 启动Activity
     * parameters 携带参数
     * enterId 进入动画
     * exitId 退出动画
     *
     * @param path
     */
    public static void actionStart(String path, Map<String, String> parameters, int enterId, int exitId) {
        Postcard build = ARouter.getInstance().build(path);

        for (Map.Entry<String, String> stringStringEntry : parameters.entrySet()) {
            build.withString(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
        build.withTransition(enterId, exitId);
        build.navigation();
    }


    /** ====================== 获取Fragment ============================= **/

    /**
     * 获取Fragment
     *
     * @param path
     */
    public static Fragment newFragment(String path) {
        return newFragment(path, new HashMap<String, String>());
    }


    /**
     * 获取Fragment
     * parameters 携带参数
     *
     * @param path
     */
    public static Fragment newFragment(String path, Map<String, String> parameters) {
        Postcard build = ARouter.getInstance().build(path);

        for (Map.Entry<String, String> stringStringEntry : parameters.entrySet()) {
            build.withString(stringStringEntry.getKey(), stringStringEntry.getValue());
        }

        Object navigation = build.navigation();
        if (!(navigation instanceof Fragment)) {
            throw new RuntimeException("path is not Fragment");
        }

        return (Fragment) navigation;
    }
}
