package com.example.btl_ordering_food_app_2.Model;

public class Invoice {
    String MaHD;
    int GiaTien;
    String MaKH;

   public  Invoice(){
   }
    public Invoice(String maHD, int giaTien,String maKH) {
        MaHD = maHD;
        GiaTien = giaTien;
        MaKH=maKH;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
    }

    public int getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(int giaTien) {
        GiaTien = giaTien;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }
}
