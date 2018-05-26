package com.zippit.zippitbackend.beans;

import com.zippit.zippitbackend.entities.AppConfig;

/**
 * Created by akash.mercer on 23-Jul-17.
 */
public class AppConfigBean {

    private Integer androidUserAppVersion;

    private String iosUserAppVersion;

    private String updateMessage;

    private String appMessage;

    public AppConfigBean() {

    }

    public AppConfigBean(AppConfig appConfig) {
        androidUserAppVersion = appConfig.getAndroidUserAppVersion();
        iosUserAppVersion = appConfig.getIosUserAppVersion();
        updateMessage = appConfig.getUpdateMessage();
        appMessage = appConfig.getAppMessage();
    }

    public Integer getAndroidUserAppVersion() {
        return androidUserAppVersion;
    }

    public void setAndroidUserAppVersion(Integer androidUserAppVersion) {
        this.androidUserAppVersion = androidUserAppVersion;
    }

    public String getIosUserAppVersion() {
        return iosUserAppVersion;
    }

    public void setIosUserAppVersion(String iosUserAppVersion) {
        this.iosUserAppVersion = iosUserAppVersion;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }

    public String getAppMessage() {
        return appMessage;
    }

    public void setAppMessage(String appMessage) {
        this.appMessage = appMessage;
    }
}
