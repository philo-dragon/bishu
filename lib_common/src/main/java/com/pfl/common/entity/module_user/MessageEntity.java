package com.pfl.common.entity.module_user;

/**
 * Created by Administrator on 2018\7\22 0022.
 */

public class MessageEntity {

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
