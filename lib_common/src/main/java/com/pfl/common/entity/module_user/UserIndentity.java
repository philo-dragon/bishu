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
    private String verified_msg;
    private String name;
    private String id_number;
    private String issuing_authority;
    private long start_ts;
    private long end_ts;
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

    public long getStart_ts() {
        return start_ts;
    }

    public void setStart_ts(long start_ts) {
        this.start_ts = start_ts;
    }

    public long getEnd_ts() {
        return end_ts;
    }

    public void setEnd_ts(long end_ts) {
        this.end_ts = end_ts;
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
