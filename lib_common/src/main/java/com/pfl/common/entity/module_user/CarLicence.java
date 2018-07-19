package com.pfl.common.entity.module_user;

/**
 * Created by Administrator on 2018\6\25 0025.
 */

public class CarLicence {

    /* 'verified_status':2,
     'plate_number':'2321'
     'owner':'王大白'
     'id_number':'2321',
     'engine_no':'134123',
     'vehicle_type':'小型越野客车',
     'vehicle_model':'普拉多J134123TEBX#$FJ',
     'back_img':'http://xx',
     'front_img':'http://xxx'*/

    private int verified_status;
    private String verified_msg;
    private String plate_number;
    private String owner;
    private String id_number;
    private String engine_no;
    private String vehicle_type;
    private String vehicle_model;
    private String back_img;
    private String front_img;

    public String getVerified_msg() {
        return verified_msg;
    }

    public void setVerified_msg(String verified_msg) {
        this.verified_msg = verified_msg;
    }

    public int getVerified_status() {
        return verified_status;
    }

    public void setVerified_status(int verified_status) {
        this.verified_status = verified_status;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getEngine_no() {
        return engine_no;
    }

    public void setEngine_no(String engine_no) {
        this.engine_no = engine_no;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getBack_img() {
        return back_img;
    }

    public void setBack_img(String back_img) {
        this.back_img = back_img;
    }

    public String getFront_img() {
        return front_img;
    }

    public void setFront_img(String front_img) {
        this.front_img = front_img;
    }
}
