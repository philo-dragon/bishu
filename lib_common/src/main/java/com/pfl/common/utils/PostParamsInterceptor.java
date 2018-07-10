package com.pfl.common.utils;

import android.location.Location;

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
        User user = ModuleUserRouteService.getUserInfo();
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

            root.put("uid", user == null ? "" : user.getUid());
            root.put("sign", "");
            root.put("request_id", request_id);//每个请求唯一,可用imei-mac-timestamp生成的md5作为request_id
            root.put("req_from", "Android");
            root.put("req_ver", "1");

            minfoData.put("os", "android");
            minfoData.put("os_ver", android.os.Build.VERSION.RELEASE);
            minfoData.put("app_ver", AppUtils.getAppVersionName());
            minfoData.put("dist", "umeng");
            minfoData.put("imei", PhoneUtils.getIMEI());
            minfoData.put("mac_addr", DeviceUtils.getMacAddress());
            minfoData.put("network", NetworkUtils.getNetworkType());
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
}
