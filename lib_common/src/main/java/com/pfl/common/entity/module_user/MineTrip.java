package com.pfl.common.entity.module_user;

import com.pfl.common.base.MultiTypeAdapter;

import java.util.List;

public class MineTrip {

    public List<MineTripBean> list;
    private int has_next;
    private int page_index;
    private int row_start;
    private int total_count;
    private int page_size;

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

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public List<MineTripBean> getList() {
        return list;
    }

    public void setList(List<MineTripBean> list) {
        this.list = list;
    }

    public static class MineTripBean extends MultiTypeAdapter.IItem {
        private String id;
        private String start_ts;
        private String end_ts;
        private int sale_status;
        private int score_add;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStart_ts() {
            return start_ts;
        }

        public void setStart_ts(String start_ts) {
            this.start_ts = start_ts;
        }

        public String getEnd_ts() {
            return end_ts;
        }

        public void setEnd_ts(String end_ts) {
            this.end_ts = end_ts;
        }

        public int getSale_status() {
            return sale_status;
        }

        public void setSale_status(int sale_status) {
            this.sale_status = sale_status;
        }

        public int getScore_add() {
            return score_add;
        }

        public void setScore_add(int score_add) {
            this.score_add = score_add;
        }
    }
}