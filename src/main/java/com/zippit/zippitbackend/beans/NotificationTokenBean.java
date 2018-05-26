package com.zippit.zippitbackend.beans;

/**
 * Created by akash.mercer on 01-Oct-16.
 */
public class NotificationTokenBean {

    private String userId;

    private String token;

    public NotificationTokenBean(){

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
