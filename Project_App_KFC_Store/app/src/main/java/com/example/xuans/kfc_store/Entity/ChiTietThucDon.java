package com.example.xuans.kfc_store.Entity;

import java.io.Serializable;

public class ChiTietThucDon implements Serializable {
    public int 	ID_ThucDonChiTiet;
    public int ID_ThucDon;
    public String Ten_ChiTiet;
    public Integer 	Gia_Tien;
    public String Hinh_Anh;

    public ChiTietThucDon(int ID_ThucDonChiTiet, int ID_ThucDon, String ten_ChiTiet, Integer gia_Tien, String hinh_Anh) {
        this.ID_ThucDonChiTiet = ID_ThucDonChiTiet;
        this.ID_ThucDon = ID_ThucDon;
        Ten_ChiTiet = ten_ChiTiet;
        Gia_Tien = gia_Tien;
        Hinh_Anh = hinh_Anh;
    }

    public int getID_ThucDonChiTiet() {
        return ID_ThucDonChiTiet;
    }

    public void setID_ThucDonChiTiet(int ID_ThucDonChiTiet) {
        this.ID_ThucDonChiTiet = ID_ThucDonChiTiet;
    }

    public int getID_ThucDon() {
        return ID_ThucDon;
    }

    public void setID_ThucDon(int ID_ThucDon) {
        this.ID_ThucDon = ID_ThucDon;
    }

    public String getTen_ChiTiet() {
        return Ten_ChiTiet;
    }

    public void setTen_ChiTiet(String ten_ChiTiet) {
        Ten_ChiTiet = ten_ChiTiet;
    }

    public Integer getGia_Tien() {
        return Gia_Tien;
    }

    public void setGia_Tien(Integer gia_Tien) {
        Gia_Tien = gia_Tien;
    }

    public String getHinh_Anh() {
        return Hinh_Anh;
    }

    public void setHinh_Anh(String hinh_Anh) {
        Hinh_Anh = hinh_Anh;
    }
}
