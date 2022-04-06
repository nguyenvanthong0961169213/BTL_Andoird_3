package com.example.btl_ordering_food_app_2.Model;

public class Account_obj {
    //Attribuse
    private String ID;
    private String User_Name;
    private String Password;
    //Contructor
    public Account_obj(){};
    public Account_obj(String ID, String user_Name, String password) {
        this.ID = ID;
        User_Name = user_Name;
        Password = password;
    }
    //Getters and Setters

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
