package com.example.xuans.kfc_store.Entity;

public class CustomNavigationView {
    private int hinhanh;
    private String ten;

    public CustomNavigationView(int hinhanh, String ten) {
        this.hinhanh = hinhanh;
        this.ten = ten;
    }

    public int getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(int hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
