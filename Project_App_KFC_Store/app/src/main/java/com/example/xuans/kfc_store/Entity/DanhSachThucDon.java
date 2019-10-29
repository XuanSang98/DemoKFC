package com.example.xuans.kfc_store.Entity;

public class DanhSachThucDon {
    public int ID_ThucDon;
    public String Ten_Thuc_Don;
    public String HinhAnh_ThucDon;

    public DanhSachThucDon(int ID_ThucDon, String ten_Thuc_Don, String hinhAnh_ThucDon) {
        this.ID_ThucDon = ID_ThucDon;
        Ten_Thuc_Don = ten_Thuc_Don;
        HinhAnh_ThucDon = hinhAnh_ThucDon;
    }

    public int getID_ThucDon() {
        return ID_ThucDon;
    }

    public void setID_ThucDon(int ID_ThucDon) {
        this.ID_ThucDon = ID_ThucDon;
    }

    public String getTen_Thuc_Don() {
        return Ten_Thuc_Don;
    }

    public void setTen_Thuc_Don(String ten_Thuc_Don) {
        Ten_Thuc_Don = ten_Thuc_Don;
    }

    public String getHinhAnh_ThucDon() {
        return HinhAnh_ThucDon;
    }

    public void setHinhAnh_ThucDon(String hinhAnh_ThucDon) {
        HinhAnh_ThucDon = hinhAnh_ThucDon;
    }
}
