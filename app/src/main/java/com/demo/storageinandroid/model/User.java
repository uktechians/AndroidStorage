package com.demo.storageinandroid.model;

import java.io.Serializable;

public class User implements Serializable {
    private String ID;
    private String u_name = "";
    private String u_phone = "";
    private String u_age = "";
    private String u_address = "";
    private String u_email = "";

    public User() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_age() {
        return  u_age;
    }

    public  void setU_age(String u_age) {
        this.u_age = u_age;
    }

    public String getU_address() {
        return u_address;
    }

    public void setU_address(String u_address) {
        this.u_address = u_address;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }
}
