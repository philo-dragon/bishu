package com.pfl.common.entity.module_user;

/**
 * 身份证信息
 */
public class UserIndentity {

    /*
      'verified_status':2,
      'name':'2321'
      'id_number':'2321',
      'issuing_authority':'北京市公安局',
      'start_day':'2018-05-01',
      'end_day':'2018-05-02',
      'back_img':'http://xx',
      'front_img':'http://xxx'*/

    private int verified_status;
    private String name;
    private String id_number;
    private String issuing_authority;
    private String start_day;
    private String end_day;
    private String back_img;
    private String front_img;

    public int getVerified_status() {
        return verified_status;
    }

    public void setVerified_status(int verified_status) {
        this.verified_status = verified_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getIssuing_authority() {
        return issuing_authority;
    }

    public void setIssuing_authority(String issuing_authority) {
        this.issuing_authority = issuing_authority;
    }

    public String getStart_day() {
        return start_day;
    }

    public void setStart_day(String start_day) {
        this.start_day = start_day;
    }

    public String getEnd_day() {
        return end_day;
    }

    public void setEnd_day(String end_day) {
        this.end_day = end_day;
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
