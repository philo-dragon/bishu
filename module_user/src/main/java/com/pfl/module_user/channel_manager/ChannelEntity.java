package com.pfl.module_user.channel_manager;

/**
 * 频道实体类
 * Created by YoKeyword on 15/12/29.
 */
public class ChannelEntity {

    private long id;
    private String name;
    private boolean fixed = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }
}
