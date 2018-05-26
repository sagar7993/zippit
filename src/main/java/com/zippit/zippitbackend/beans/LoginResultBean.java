package com.zippit.zippitbackend.beans;

import com.zippit.zippitbackend.entities.TeacherInstitute;
import com.zippit.zippitbackend.enums.UserTypeEnum;
import com.zippit.zippitbackend.entities.User;

public class LoginResultBean extends StatusBean {

    private String userId;

    private String personName;

    private String contactNumber;

    private String email;

    private String profilePicUrl;

    private Boolean userStatus;

    private Boolean admin;

    private String instituteName;

    private StandardDivisionBean standardDivisionBean;

    private Integer userType;

    public LoginResultBean(){

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public StandardDivisionBean getStandardDivisionBean() {
        return standardDivisionBean;
    }

    public void setStandardDivisionBean(StandardDivisionBean standardDivisionBean) {
        this.standardDivisionBean = standardDivisionBean;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public void populateData(User user, TeacherInstitute teacherInstitute) {
        this.userId = user.getId();
        this.personName = user.getName();
        this.contactNumber = user.getContactNumber();
        this.email = user.getEmail();
        this.profilePicUrl = user.getProfilePicUrl();
        this.userStatus = user.getStatus();
        this.admin = user.getAdmin();
        this.userType = user.getUserType().getType();

        if (UserTypeEnum.STUDENT.getType() == user.getUserType().getType()) {
            if (user.getStandardDivision() != null) {
                instituteName = user.getStandardDivision().getInstitute().getName();
            }
        } else {
            if (teacherInstitute != null) {
                instituteName = teacherInstitute.getInstitute().getName();
            }
        }

        if (user.getStandardDivision() != null) {
            standardDivisionBean = new StandardDivisionBean(user.getStandardDivision());
        }
    }
}