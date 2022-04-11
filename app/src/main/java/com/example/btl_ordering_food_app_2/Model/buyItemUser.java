package com.example.btl_ordering_food_app_2.Model;

public class buyItemUser {
    private String MaHD;
    private String TenSP;
    private int GiaTien;

    public buyItemUser() {
    }

    public buyItemUser(String maHD, String tenSP, int giaTien) {
        MaHD = maHD;
        TenSP = tenSP;
        GiaTien = giaTien;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public int getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(int giaTien) {
        GiaTien = giaTien;
    }

    @Override
    public String toString() {
        return "buyItemUser{" +
                "MaHD='" + MaHD + '\'' +
                ", TenSP='" + TenSP + '\'' +
                ", GiaTien=" + GiaTien +
                '}';
    }
}