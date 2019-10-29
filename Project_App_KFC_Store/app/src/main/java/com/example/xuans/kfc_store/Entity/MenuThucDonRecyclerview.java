package com.example.xuans.kfc_store.Entity;

public class MenuThucDonRecyclerview {
    private int img_menu_thucdon;
    private String txt_menu_thucdon;

    public MenuThucDonRecyclerview(int img_menu_thucdon, String txt_menu_thucdon) {
        this.img_menu_thucdon = img_menu_thucdon;
        this.txt_menu_thucdon = txt_menu_thucdon;
    }

    public int getImg_menu_thucdon() {
        return img_menu_thucdon;
    }

    public void setImg_menu_thucdon(int img_menu_thucdon) {
        this.img_menu_thucdon = img_menu_thucdon;
    }

    public String getTxt_menu_thucdon() {
        return txt_menu_thucdon;
    }

    public void setTxt_menu_thucdon(String txt_menu_thucdon) {
        this.txt_menu_thucdon = txt_menu_thucdon;
    }
}
