package com.example.btl_ordering_food_app_2.Model;

import java.io.Serializable;

public class Order  implements Serializable {
    private int MaSP;
    private int SoLuong;
    private int GiaTien;
    private String Anh;
    private String TenSP;

    public Order(int maSP, int soLuong, int giaTien, String anh, String tenSP) {
        MaSP = maSP;
        SoLuong = soLuong;
        GiaTien = giaTien;
        Anh = anh;
        TenSP = tenSP;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
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
}
