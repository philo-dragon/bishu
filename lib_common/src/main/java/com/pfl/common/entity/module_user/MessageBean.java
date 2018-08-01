package com.pfl.common.entity.module_user;

import java.util.List;

/**
 * Created by Administrator on 2018\8\1 0001.
 */

public class MessageBean {

    private List<Message> list;
    private int has_next;
    private int page_index;
    private int row_start;
    private int total_count;
    private int page_size;

    public List<Message> getList() {
        return list;
    }

    public void setList(List<Message> list) {
        this.list = list;
    }

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

    public static class Message {

        private String title;
        private String message;
        private boolean isRead;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isRead() {
            return isRead;
        }

        public void setRead(boolean read) {
            isRead = read;
        }
    }
}
