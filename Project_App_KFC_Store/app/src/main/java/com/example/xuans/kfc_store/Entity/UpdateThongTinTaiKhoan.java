package com.example.xuans.kfc_store.Entity;

import java.io.Serializable;

public class UpdateThongTinTaiKhoan implements Serializable{
    private int id;
    private String email;
    private String matKhau;
    private String nhapLaiMatKhau;
    private String tenNguoiDung;
    private String soDienThoai;
    private String diaChi;

    public UpdateThongTinTaiKhoan() {
    }

    public UpdateThongTinTaiKhoan(int id, String email, String matKhau, String nhapLaiMatKhau, String tenNguoiDung, String soDienThoai, String diaChi) {
        this.id = id;
        this.email = email;
        this.matKhau = matKhau;
        this.nhapLaiMatKhau = nhapLaiMatKhau;
        this.tenNguoiDung = tenNguoiDung;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getNhapLaiMatKhau() {
        return nhapLaiMatKhau;
    }

    public void setNhapLaiMatKhau(String nhapLaiMatKhau) {
        this.nhapLaiMatKhau = nhapLaiMatKhau;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
