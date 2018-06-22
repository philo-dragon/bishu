package com.pfl.common.http;

import com.pfl.common.entity.module_app.AppConfiguration;
import com.pfl.common.entity.base.AccessToken;
import com.pfl.common.entity.module_user.Score;

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
     * 登录接口
     *
     * @param mobile 手机号码
     * @param pwd    密码
     * @return
     */
    @FormUrlEncoded
    @POST("/user/login")
    Observable<AccessToken> doLogin(@Field("mobile") String mobile, @Field("pwd") String pwd);


    /**
     * 版本更新
     *
     * @param mobile 手机号码
     * @param pwd    密码
     * @return
     */
    @FormUrlEncoded
    @POST("/configuration")
    Observable<AppConfiguration> configuration(@Field("mobile") String mobile, @Field("pwd") String pwd);


    /**
     * 我的积分
     *
     * @param mobile 手机号码
     * @param pwd    密码
     * @return
     */
    @FormUrlEncoded
    @POST("/user/score")
    Observable<Score> score(@Field("mobile") String mobile, @Field("pwd") String pwd);


}
