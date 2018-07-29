package com.pfl.common.entity.module_user;

import java.util.List;

/**
 * Created by Administrator on 2018\6\24 0024.
 */

public class UserInfo {

    private String avatar;
    private String nickname;
    private String mobile;
    private int gender;
    private int id_verified;
    private int driver_verified;
    private int car_verified;
    private String referral_code;
    private List<Device> devices;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getId_verified() {
        return id_verified;
    }

    public void setId_verified(int id_verified) {
        this.id_verified = id_verified;
    }

    public int getCar_verified() {
        return car_verified;
    }

    public void setCar_verified(int car_verified) {
        this.car_verified = car_verified;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public int getDriver_verified() {
        return driver_verified;
    }

    public void setDriver_verified(int driver_verified) {
        this.driver_verified = driver_verified;
    }

    public String getReferral_code() {
        return referral_code;
    }

    public void setReferral_code(String referral_code) {
        this.referral_code = referral_code;
    }

    public static class Device {

        private String name;
        private String type;
        private String imei;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }
    }

}
