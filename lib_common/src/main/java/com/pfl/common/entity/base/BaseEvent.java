package com.pfl.common.entity.base;

/**
 * Created by Administrator on 2018\7\12 0012.
 */

public class BaseEvent<T> {
    private String from;
    private T t;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
