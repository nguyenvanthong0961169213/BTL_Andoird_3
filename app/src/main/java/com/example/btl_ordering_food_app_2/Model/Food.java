package com.example.btl_ordering_food_app_2.Model;

public class Food {
    //Attributes
    private int MaSP;
    private int GiaTien;
    private int Anh;
    private String TenSP;

    //Contructors
    public Food(){}
    public Food(int maSP, int giaTien, int anh, String tenSP) {
        MaSP = maSP;
        GiaTien = giaTien;
        Anh = anh;
        TenSP = tenSP;
    }

    //Getters and Setter
    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public int getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(int giaTien) {
        GiaTien = giaTien;
    }

    public int getAnh() {
        return Anh;
    }

    public void setAnh(int anh) {
        Anh = anh;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }


}
