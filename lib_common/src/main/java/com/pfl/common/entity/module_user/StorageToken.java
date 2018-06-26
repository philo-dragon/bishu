package com.pfl.common.entity.module_user;

/**
 * Created by Administrator on 2018\6\26 0026.
 */

public class StorageToken {

    /* "endPoint":"oss-cn-shanghai.aliyuncs.com",
     "bucketName":"bishu",
     "callbackUrl":"http://106.14.224.126/storage_callback",
     'AccessKeyId':'STS.3pYjsdgdgagdasdg',
     'AccessKeySecret':'rpnwO9kvEgetGdrddgsR2YrTtI',
     'Security':'CAES+wMIARKAxxxxxx',
     'Expiration':234134,*/

    private String endPoint;
    private String bucketName;
    private String callbackUrl;
    private String AccessKeyId;
    private String AccessKeySecret;
    private String Security;
    private int Expiration;

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getAccessKeyId() {
        return AccessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        AccessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return AccessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        AccessKeySecret = accessKeySecret;
    }

    public String getSecurity() {
        return Security;
    }

    public void setSecurity(String security) {
        Security = security;
    }

    public int getExpiration() {
        return Expiration;
    }

    public void setExpiration(int expiration) {
        Expiration = expiration;
    }
}
