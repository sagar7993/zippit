package com.zippit.zippitbackend.beans;

public class AdminLoginStatusBean extends StatusBean {

    private String email;

    private String name;

    public AdminLoginStatusBean(){
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}