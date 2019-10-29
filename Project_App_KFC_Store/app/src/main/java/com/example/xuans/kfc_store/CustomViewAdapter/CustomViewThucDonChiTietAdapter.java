package com.example.xuans.kfc_store.CustomViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xuans.kfc_store.R;


public class CustomViewThucDonChiTietAdapter extends LinearLayout {

    ImageView hinhAnhChiTiet;
    TextView tenThucDonChiTiet;
    TextView giaThucDonChiTiet;
    public CustomViewThucDonChiTietAdapter(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.thucdonchitiet_entity,this,true);
        hinhAnhChiTiet = findViewById(R.id.img_thucdonchitiet_entity);
        tenThucDonChiTiet = findViewById(R.id.txt_tenthucdonchitiet_entity);
        giaThucDonChiTiet = findViewById(R.id.txt_giatienthucdonchitiet_entity);
    }
}
