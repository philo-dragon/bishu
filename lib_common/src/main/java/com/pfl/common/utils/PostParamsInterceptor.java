package com.pfl.common.utils;

import android.location.Location;
import android.util.Log;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.pfl.common.entity.module_user.User;
import com.pfl.common.service.ModuleAppLocationRouteService;
import com.pfl.common.service.ModuleUserRouteService;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.blankj.utilcode.util.PhoneUtils.getIMEI;

/**
 * Created by Administrator on 2018\7\10 0010.
 */

public class PostParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originRequest = chain.request();
        Request.Builder newRequest = originRequest.newBuilder();
        newRequest.addHeader("Content-Type", "application/json;charset=UTF-8");

        //添加公共参数
        String request_id = EncryptUtils.encryptMD5ToString(getIMEI() + "-" + DeviceUtils.getMacAddress() + "-" + System.currentTimeMillis());
        User user = ModuleUserRouteService.getUser();
        Location location = ModuleAppLocationRouteService.getLocation();

        JSONObject root = new JSONObject();
        JSONObject minfoData = new JSONObject();
        JSONObject geoData = new JSONObject();

        try {

            FormBody formBody = (FormBody) originRequest.body();
            int bodySize = formBody.size();
            for (int i = 0; i < bodySize; i++) {
                root.put(formBody.name(i), formBody.value(i));
            }

            HttpUrl url = newRequest.build().url();

            String sign = "";

            if (ModuleUserRouteService.getUser() != null) {
                sign = EncryptUtils.encryptMD5ToString(ModuleUserRouteService.getUser().getToken() + "_" +
                        url.toString().replace(BaseUrlManager.getBaseUrl() + "app/api/" + BaseUrlManager.API_VERSION, "")).toLowerCase();
            }

            root.put("uid", user == null ? "" : user.getUid());
            root.put("sign", sign);//sign=md5("token_/configuraion")
            root.put("request_id", request_id);//每个请求唯一,可用imei-mac-timestamp生成的md5作为request_id
            root.put("req_from", "Android");
            root.put("req_ver", "1");

            minfoData.put("os", "android");
            minfoData.put("os_ver", android.os.Build.VERSION.RELEASE);
            minfoData.put("app_ver", AppUtils.getAppVersionName());
            minfoData.put("dist", "umeng");
            minfoData.put("imei", PhoneUtils.getIMEI());
            minfoData.put("mac_addr", DeviceUtils.getMacAddress());
            minfoData.put("network", getNetWork());
            minfoData.put("screen_width", ScreenUtils.getScreenWidth());
            minfoData.put("screen_height", ScreenUtils.getScreenHeight());
            minfoData.put("device_brand", getDeviceBrand());
            minfoData.put("device_model", DeviceUtils.getModel());
            root.put("minfo", minfoData);

            geoData.put("x", location == null ? "" : location.getLatitude());
            geoData.put("y", location == null ? "" : location.getLongitude());
            root.put("geo", geoData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        newRequest.post(RequestBody.create(MediaType.parse("application/json"), root.toString()));
        return chain.proceed(newRequest.build());
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 取值{-1,0,2,3,4} 分别表示{无法取到网络状态，wifi,2G,3G,4G} |
     * <p>
     * <li>{@link NetworkUtils.NetworkType#NETWORK_WIFI   } </li>
     * <li>{@link NetworkUtils.NetworkType#NETWORK_4G     } </li>
     * <li>{@link NetworkUtils.NetworkType#NETWORK_3G     } </li>
     * <li>{@link NetworkUtils.NetworkType#NETWORK_2G     } </li>
     * <li>{@link NetworkUtils.NetworkType#NETWORK_UNKNOWN} </li>
     * <li>{@link NetworkUtils.NetworkType#NETWORK_NO     } </li>
     *
     * @return
     */
    public static String getNetWork() {
        if (NetworkUtils.getNetworkType() == NetworkUtils.NetworkType.NETWORK_WIFI) {
            return "4";
        } else if (NetworkUtils.getNetworkType() == NetworkUtils.NetworkType.NETWORK_4G) {
            return "3";
        } else if (NetworkUtils.getNetworkType() == NetworkUtils.NetworkType.NETWORK_3G) {
            return "2";
        } else if (NetworkUtils.getNetworkType() == NetworkUtils.NetworkType.NETWORK_2G) {
            return "0";
        } else {
            return "-1";
        }
    }
}
