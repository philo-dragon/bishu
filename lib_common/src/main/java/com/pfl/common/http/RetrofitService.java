package com.pfl.common.http;

import com.pfl.common.entity.base.AccessToken;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_app.AppConfiguration;
import com.pfl.common.entity.module_user.CarLicence;
import com.pfl.common.entity.module_user.Device;
import com.pfl.common.entity.module_user.FindBean;
import com.pfl.common.entity.module_user.MessageBean;
import com.pfl.common.entity.module_user.MineTrip;
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
import retrofit2.http.HTTP;
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
    @POST("app/api/v1/user/token")
    Observable<HttpResponse<AccessToken>> getToken(@Field("action") String action,
                                                   @Field("grant_type") String grant_type,
                                                   @Field("client_id") String client_id,
                                                   @Field("client_secret") String client_secret
    );

    /**
     * 注册
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user")
    Observable<HttpResponse<User>> doRegist(@Field("action") String action,
                                            @Field("mobile") String mobile,
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
    @POST("app/api/v1/user/token")
    Observable<HttpResponse<User>> doLogin(@Field("action") String action,
                                           @Field("mobile") String mobile,
                                           @Field("pwd") String pwd);

    /**
     * 退出
     *
     * @param uid 用户id
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/token")
    Observable<HttpResponse<Object>> loginOut(@Field("action") String action,
                                              @Field("uid") String uid);

    /**
     * 用户查找
     *
     * @param mobile 手机号码
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user")
    Observable<HttpResponse<User>> findUser(@Field("action") String action,
                                            @Field("mobile") String mobile);


    /**
     * 获取app的一些配置信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/configuration")
    Observable<HttpResponse<AppConfiguration>> configuration(@Field("action") String action);

    /**
     * 发送手机验证码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/sms_code")
    Observable<HttpResponse<Object>> sendSMSCode(@Field("action") String action,
                                                 @Field("mobile") String mobile);

    /**
     * 发送手机验证码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/sms_code")
    Observable<HttpResponse<VerifySMSResult>> checkSMSCode(@Field("action") String action,
                                                           @Field("mobile") String mobile,
                                                           @Field("verify_code") String verify_code);


    /**
     * 我的积分
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/score")
    Observable<HttpResponse<Score>> score(@Field("action") String action);


    /**
     * 钱包 -- 积分记录
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/score_log")
    Observable<HttpResponse<ScoreLog>> score_log(@Field("action") String action,
                                                 @Field("page") int page,
                                                 @Field("page_size") int page_size);


    /**
     * 我的行程
     *
     * @param page      页数
     * @param page_size 每页条数
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/routes")
    Observable<HttpResponse<MineTrip>> myTrip(@Field("action") String action,
                                              @Field("page") int page,
                                              @Field("page_size") int page_size);

    /**
     * 获取用户信息(个人中心)
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/info")
    Observable<HttpResponse<UserInfo>> userInfo(@Field("action") String action,
                                                @Field("uid") String uid);

    /**
     * 获取用户信息(个人中心)
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/info")
    Observable<HttpResponse<Object>> updateUserNameAndGender(@Field("action") String action,
                                                             @Field("nickname") String nickname,
                                                             @Field("gender") String gender);

    /**
     * 用户反馈
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/feedback")
    Observable<HttpResponse<Object>> feedback(@Field("action") String action,
                                              @Field("content") String content);


    /**
     * 获取用户智能硬件
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/devices")
    Observable<HttpResponse<Device>> getDevices(@Field("action") String action,
                                                @Field("uid") String uid);

    /**
     * 添加户智能硬件
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/device")
    Observable<HttpResponse<Object>> addDevice(@Field("action") String action,
                                               @Field("imei") String imei,
                                               @Field("name") String name);


    /**
     * 解绑用户智能硬件
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/device")
    Observable<HttpResponse<Object>> deleteDevice(@Field("action") String action,
                                                  @Field("id") String id);

    /**
     * 发现
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/discovery")
    Observable<HttpResponse<FindBean>> find(@Field("action") String action);

    /**
     * 获取身份证信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/identity")
    Observable<HttpResponse<UserIndentity>> getIdentity(@Field("action") String action,
                                                        @Field("uid") String uid);

    /**
     * 获取驾照信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/driver_licence")
    Observable<HttpResponse<UserLicence>> getLicence(@Field("action") String action,
                                                     @Field("uid") String uid);

    /**
     * 获取行驶证信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/car_licence")
    Observable<HttpResponse<CarLicence.CarLicenceBean>> getCarLicence(@Field("action") String action,
                                                                      @Field("id") String uid);

    /**
     * 获取车辆行驶证列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/car_licences")
    Observable<HttpResponse<CarLicence>> getCarLicenceList(@Field("action") String action);

    /**
     * 获取oss配置及token
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/storage_token")
    Observable<HttpResponse<StorageToken>> getStorageToken(@Field("action") String action);

    /**
     * 忘记密码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/pwd")
    Observable<HttpResponse<Object>> forgetPassword(@Field("action") String action,
                                                    @Field("mobile") String mobile,
                                                    @Field("pwd") String pwd);

    /**
     * 消息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/messages")
    Observable<HttpResponse<MessageBean>> getMessageList(@Field("action") String action,
                                                         @Field("page") int page,
                                                         @Field("page_size") int page_size);


    /**
     * 签到
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/sign")
    Observable<HttpResponse<Object>> userSign(@Field("action") String action);

    /**
     * 签到
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/api/v1/user/message")
    Observable<HttpResponse<Object>> updateMessageState(@Field("action") String action,
                                              @Field("id") String messageId,
                                              @Field("read_status") int read_status);


}
