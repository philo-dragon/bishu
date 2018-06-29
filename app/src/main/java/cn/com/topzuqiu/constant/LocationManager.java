package cn.com.topzuqiu.constant;

import android.location.Location;

import com.pfl.module_user.constant.UserInfoManager;

/**
 * 位置信息管理
 */
public class LocationManager {

    private static LocationManager INSTANCE;
    private static Location location;

    private LocationManager() {
    }

    public static LocationManager getInstance() {

        if (null == INSTANCE) {
            synchronized (UserInfoManager.class) {
                if (null == INSTANCE) {
                    INSTANCE = new LocationManager();
                }
            }
        }

        return INSTANCE;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        LocationManager.location = location;
    }
}
