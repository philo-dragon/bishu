package com.pfl.common.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.ScreenUtils;

import java.util.HashMap;
import java.util.Map;

import static com.blankj.utilcode.util.PhoneUtils.getIMEI;

/**
 * Created by rocky on 2018/6/19.
 */

public class CommonParamsInterceptor extends BaseCommonParamsInterceptor {

    /**
     * 'uid':'xxxx',
     * 'sign':'xxxx',
     * 'request_id':'xxx'
     * 'req_from':'app',
     * 'req_ver':'1',
     * 'minfo':{
     * 'os':'android',
     * 'os_ver':'2.2',
     * 'app_ver':'1.0',
     * 'dist':'googleplay',
     * 'imei':'fadfasdfadsf',
     * 'idfv':'asdf',
     * 'mac_addr':'fasdfdsfadf',
     * 'network':3,
     * 'screen_width':480,
     * 'screen_height':720,
     * 'device_brand':'Huawei',
     * 'device_model':'a330',
     * },
     * 'geo':{x:'12.203872',y:'-12.231245'},
     */
    @Override
    public Map<String, String> getHeaderMap() {
        return null;
    }

    @Override
    public Map<String, String> getQueryParamMap() {

        return getCommonParams();
    }

    @Override
    public Map<String, String> getFormBodyParamMap() {
        return getCommonParams();
    }

    @NonNull
    private Map<String, String> getCommonParams() {
        //添加公共参数
        Map<String, String> commomParamsMap = new HashMap<>();
        String request_id = EncryptUtils.encryptMD5ToString(getIMEI() + "-" + DeviceUtils.getMacAddress() + "-0" + System.currentTimeMillis());
        commomParamsMap.put("uid", "");
        commomParamsMap.put("sign", "");
        commomParamsMap.put("request_id", request_id);//每个请求唯一,可用imei-mac-timestamp生成的md5作为request_id
        commomParamsMap.put("req_from", "Android");
        commomParamsMap.put("req_ver", "1");

        StringBuilder minfoBuilder = new StringBuilder();
        minfoBuilder.append("{");
        minfoBuilder.append("os").append(":").append("android").append(",");
        minfoBuilder.append("os_ver").append(":").append(android.os.Build.VERSION.RELEASE).append(",");
        minfoBuilder.append("app_ver").append(":").append(AppUtils.getAppVersionName()).append(",");
        minfoBuilder.append("dist").append(":").append("umeng").append(",");
        minfoBuilder.append("imei").append(":").append(PhoneUtils.getIMEI()).append(",");
        //minfoBuilder.append("idfv").append(":").append("android").append(",");
        minfoBuilder.append("mac_addr").append(":").append(DeviceUtils.getMacAddress()).append(",");
        minfoBuilder.append("network").append(":").append(NetworkUtils.getNetworkType()).append(",");
        minfoBuilder.append("screen_width").append(":").append(ScreenUtils.getScreenWidth()).append(",");
        minfoBuilder.append("screen_height").append(":").append(ScreenUtils.getScreenHeight()).append(",");
        minfoBuilder.append("device_brand").append(":").append(getDeviceBrand()).append(",");
        minfoBuilder.append("device_model").append(":").append(DeviceUtils.getModel());
        minfoBuilder.append("}");
        commomParamsMap.put("minfo", minfoBuilder.toString());

        StringBuilder geoBuilder = new StringBuilder();
        minfoBuilder.append("{");
        minfoBuilder.append("x").append(":").append("12.203872").append(",");
        minfoBuilder.append("y").append(":").append("-12.231245");
        minfoBuilder.append("}");
        commomParamsMap.put("geo", geoBuilder.toString());
        return commomParamsMap;
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
