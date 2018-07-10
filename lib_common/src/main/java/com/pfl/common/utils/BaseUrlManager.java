package com.pfl.common.utils;

/**
 * Created by rocky on 2017/12/28.
 */

public class BaseUrlManager {

    public static String DEV_URL;
    public static String RELEASE_URL;
    public static String API_VERSION;
    public static boolean isDebug;

    public static void init(String dev_url, String release_url, String apiVersion, boolean is_debug) {
        DEV_URL = dev_url;
        RELEASE_URL = release_url;
        API_VERSION = apiVersion;
        isDebug = is_debug;
    }

    public static String getBaseUrl() {
        return isDebug ? DEV_URL : RELEASE_URL;
    }
}
