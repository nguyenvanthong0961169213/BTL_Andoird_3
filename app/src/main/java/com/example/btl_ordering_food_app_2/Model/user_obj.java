package com.example.btl_ordering_food_app_2.Model;

import java.io.Serializable;

public class user_obj implements Serializable {
    //Attributes
    private String name;
    private String phonenumber;
    private String address;
    private boolean sex;
    private String userpassword;
    //Constructor
    public  user_obj(){};

    public user_obj(String name, String phonenumber, String address, boolean sex, String userpassword) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.address = address;
        this.sex = sex;
        this.userpassword = userpassword;
    }

    //Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
}
