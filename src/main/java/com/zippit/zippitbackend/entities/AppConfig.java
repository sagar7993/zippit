package com.zippit.zippitbackend.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "app_config")
public class AppConfig {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = 60)
    private String id;

    @Column(nullable = false)
    private Integer androidUserAppVersion;

    @Column(nullable = false, length = 40)
    private String iosUserAppVersion;

    @Column
    private String updateMessage;

    @Column
    private String appMessage;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    public AppConfig(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}