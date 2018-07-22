package com.pfl.common.entity.module_user;

import com.pfl.common.base.MultiTypeAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018\6\24 0024.
 */

public class ScoreLog {

    /**
     * has_next : 0
     * page_index : 0
     * row_start : 0
     * total_count : 0
     * page_count : 1
     * list : []
     * page_size : 10
     */

    private int has_next;
    private int page_index;
    private int row_start;
    private int total_count;
    private int page_count;
    private int page_size;
    private List<Wallet> list;

    public int getHas_next() {
        return has_next;
    }

    public void setHas_next(int has_next) {
        this.has_next = has_next;
    }

    public int getPage_index() {
        return page_index;
    }

    public void setPage_index(int page_index) {
        this.page_index = page_index;
    }

    public int getRow_start() {
        return row_start;
    }

    public void setRow_start(int row_start) {
        this.row_start = row_start;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public List<Wallet> getList() {
        return list;
    }

    public void setList(List<Wallet> list) {
        this.list = list;
    }

    public static class Wallet extends MultiTypeAdapter.IItem {
        private String title;
        private String value;
        private long time;
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

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

}
