package com.example.btl_ordering_food_app_2.Model;

public class Invoice_detail {
    //Attribuse
    String MaHD;
    int SoLuong;
    String MaSP;
    //Contructor
   public Invoice_detail(){};
    public Invoice_detail(String maHD, int soLuong, String maSP) {
        MaHD = maHD;
        SoLuong = soLuong;
        MaSP = maSP;
    }
    //Getters and setter

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }
}
