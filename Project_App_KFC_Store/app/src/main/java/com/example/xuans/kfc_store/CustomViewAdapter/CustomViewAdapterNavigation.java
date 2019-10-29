package com.example.xuans.kfc_store.CustomViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xuans.kfc_store.R;

public class CustomViewAdapterNavigation extends LinearLayout {
    public ImageView imageView;
    public TextView textView;
    public CustomViewAdapterNavigation(Context context) {
        super(context);
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_navigationview_entity,this,true);
        imageView = findViewById(R.id.img_custom_navigationview_entity);
        textView = findViewById(R.id.txt_custom_navigationview_entity);
    }
}
