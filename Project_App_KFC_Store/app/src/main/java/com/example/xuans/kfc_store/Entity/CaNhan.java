package com.example.xuans.kfc_store.Entity;

public class CaNhan {
    private int imgCaNhan;
    private String thongTinCaNhan;

    public CaNhan(int imgCaNhan, String thongTinCaNhan) {
        this.imgCaNhan = imgCaNhan;
        this.thongTinCaNhan = thongTinCaNhan;
    }

    public int getImgCaNhan() {
        return imgCaNhan;
    }

    public void setImgCaNhan(int imgCaNhan) {
        this.imgCaNhan = imgCaNhan;
    }

    public String getThongTinCaNhan() {
        return thongTinCaNhan;
    }

    public void setThongTinCaNhan(String thongTinCaNhan) {
        this.thongTinCaNhan = thongTinCaNhan;
    }
}
