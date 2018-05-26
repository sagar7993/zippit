package com.zippit.zippitbackend.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akash.mercer on 20-Jun-17.
 */
public class SignUpBean {

    private String name;

    private String contactNumber;

    private String address;

    private String email;

    private String accountType;

    private String deviceType;

    private String appVersion;

    private String fcmToken;

    private String instituteId;

    private String standardDivisionId;

    private Integer userType;

    private UserAddressBean userAddressBean;

    private List<StandardBean> standardBeans = new ArrayList<>();

    public SignUpBean() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }

    public String getStandardDivisionId() {
        return standardDivisionId;
    }

    public void setStandardDivisionId(String standardDivisionId) {
        this.standardDivisionId = standardDivisionId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public UserAddressBean getUserAddressBean() {
        return userAddressBean;
    }

    public void setUserAddressBean(UserAddressBean userAddressBean) {
        this.userAddressBean = userAddressBean;
    }

    public List<StandardBean> getStandardBeans() {
        return standardBeans;
    }

    public void setStandardBeans(List<StandardBean> standardBeans) {
        this.standardBeans = standardBeans;
    }
}
