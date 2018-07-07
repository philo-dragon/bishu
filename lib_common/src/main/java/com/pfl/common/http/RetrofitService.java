package com.pfl.common.http;

import android.service.carrier.CarrierMessagingService;

import com.pfl.common.entity.module_app.AppConfiguration;
import com.pfl.common.entity.base.AccessToken;
import com.pfl.common.entity.module_user.CarLicence;
import com.pfl.common.entity.module_user.Device;
import com.pfl.common.entity.module_user.FindBean;
import com.pfl.common.entity.module_user.MineTripBean;
import com.pfl.common.entity.module_user.Score;
import com.pfl.common.entity.module_user.ScoreLog;
import com.pfl.common.entity.module_user.StorageToken;
import com.pfl.common.entity.module_user.User;
import com.pfl.common.entity.module_user.UserIndentity;
import com.pfl.common.entity.module_user.UserInfo;
import com.pfl.common.entity.module_user.UserLicence;
import com.pfl.common.entity.module_user.VerifySMSResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {

    /**
     * 获取请求令牌
     *
     * @param grant_type
     * @param client_id
     * @param client_secret
     * @return
     */
    @FormUrlEncoded
    @POST("/user/token")
    Observable<AccessToken> getToken(@Field("grant_type") String grant_type,
                                     @Field("client_id") String client_id,
                                     @Field("client_secret") String client_secret
    );

    /**
     * 注册
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/user")
    Observable<User> doRegist(@Field("mobile") String mobile,
                              @Field("pwd") String pwd,
                              @Field("invatinCode") String invatinCode,
                              @Field("verify_code") String verify_code);

    /**
     * 登录
     *
     * @param mobile 手机号码
     * @param pwd    密码
     * @return
     */
    @FormUrlEncoded
    @POST("/user/login")
    Observable<User> doLogin(@Field("mobile") String mobile, @Field("pwd") String pwd);

    /**
     * 退出
     *
     * @param uid 用户id
     * @return
     */
    @FormUrlEncoded
    @POST("/user/login")
    Observable<Object> loginOut(@Field("uid") String uid);

    /**
     * 用户查找
     *
     * @param mobile 手机号码
     * @return
     */
    @FormUrlEncoded
    @POST("/user")
    Observable<User> findUser(@Field("mobile") String mobile);


    /**
     * 获取app的一些配置信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/configuration")
    Observable<AppConfiguration> configuration(@Field("action") String action);

    /**
     * 发送手机验证码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/sms_code")
    Observable<Object> sendSMSCode(@Field("mobile") String mobile);

    /**
     * 发送手机验证码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/sms_code")
    Observable<VerifySMSResult> checkSMSCode(@Field("mobile") String mobile, @Field("verify_code") String verify_code);


    /**
     * 我的积分
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/user/score")
    Observable<Score> score();


    /**
     * 钱包 -- 积分记录
     *
     * @param mobile 手机号码
     * @param pwd    密码
     * @return
     */
    @FormUrlEncoded
    @POST("/user/score_log")
    Observable<List<ScoreLog>> score_log(@Field("mobile") String mobile, @Field("pwd") String pwd);


    /**
     * 我的行程
     *
     * @param page      页数
     * @param page_size 每页条数
     * @return
     */
    @FormUrlEncoded
    @POST("/user/routes")
    Observable<List<MineTripBean>> myTrip(@Field("page") String page, @Field("page_size") String page_size);

    /**
     * 获取用户信息(个人中心)
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/user/info")
    Observable<UserInfo> userInfo();

    /**
     * 用户反馈
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/user/feedback")
    Observable<Object> feedback(@Field("content") String content);


    /**
     * 获取用户智能硬件
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/user/devices")
    Observable<List<Device>> getDevices(@Field("uid") String uid);

    /**
     * 添加户智能硬件
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/user/device")
    Observable<Object> addDevice(@Field("imei") String imei, @Field("type") String type);

    /**
     * 发现
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/user/discovery")
    Observable<FindBean> find();

    /**
     * 获取身份证信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/user/identity")
    Observable<UserIndentity> getIdentity(@Field("uid") String uid);

    /**
     * 获取驾照信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/user/driver_licence")
    Observable<UserLicence> getLicence(@Field("uid") String uid);

    /**
     * 获取行驶证信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/user/car_licence")
    Observable<CarLicence> getCarLicence(@Field("uid") String uid);

    /**
     * 获取oss配置及token
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/storage_token")
    Observable<StorageToken> getStorageToken(@Field("resource") String resource, @Field("seq") String seq);


}
