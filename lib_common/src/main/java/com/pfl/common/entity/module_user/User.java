package com.pfl.common.entity.module_user;

import com.pfl.common.entity.base.BaseEntyty;


/**
 * Created by Administrator on 2017/12/18 0018.
 */

public class User extends BaseEntyty {

    private String uid;
    private String token;
    private long expired_time;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpired_time() {
        return expired_time;
    }

    public void setExpired_time(long expired_time) {
        this.expired_time = expired_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", token='" + token + '\'' +
                ", expired_time=" + expired_time +
                '}';
    }
}
