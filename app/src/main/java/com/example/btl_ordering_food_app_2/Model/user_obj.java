package com.example.btl_ordering_food_app_2.Model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class user_obj implements Serializable {
    //Attributes
    private String id;
    private String name;
    private String phonenumber;
    private String address;
    private boolean sex;
    private String userpassword;

    private String avatar;
    //Constructor
    public  user_obj(){};

    public user_obj(String id, String name, String phonenumber, String address, boolean sex, String userpassword,String avatar) {
        this.id=id;
        this.name = name;
        this.phonenumber = phonenumber;
        this.address = address;
        this.sex = sex;
        this.userpassword = userpassword;
        this.avatar=avatar;
    }

    //Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "user_obj{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", address='" + address + '\'' +
                ", sex=" + sex +
                ", userpassword='" + userpassword + '\'' +
                '}';
    }

}
