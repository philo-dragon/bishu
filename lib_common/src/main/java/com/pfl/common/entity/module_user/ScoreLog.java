package com.pfl.common.entity.module_user;

import com.pfl.common.base.MultiTypeAdapter;

/**
 * Created by Administrator on 2018\6\24 0024.
 */

public class ScoreLog extends MultiTypeAdapter.IItem {

    private String title;
    private String value;
    private String time;
    private int type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
