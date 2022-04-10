package com.example.btl_ordering_food_app_2.Model;

import java.io.Serializable;

public class Food implements Serializable {
    //Attributes
    private String MaSP;
    private int GiaTien;
    private String Anh;
    private String TenSP;
    private String ThanhPhan;

    //Contructors
    public Food(){}
    public Food(String maSP, int giaTien, String anh, String tenSP,String thanhphan) {
        MaSP = maSP;
        GiaTien = giaTien;
        Anh = anh;
        TenSP = tenSP;
        ThanhPhan=thanhphan;
    }

    //Getters and Setter
    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public int getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(int giaTien) {
        GiaTien = giaTien;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getThanhPhan() {
        return ThanhPhan;
    }

    public void setThanhPhan(String thanhPhan) {
        ThanhPhan = thanhPhan;
    }


}
