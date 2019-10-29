package com.example.xuans.kfc_store.CustomViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xuans.kfc_store.R;

public class CustomView extends LinearLayout {
    public ImageView imageView;
    public TextView textView;
    public CustomView(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.canhan_entity,this,true);

        //gan layout ve doi tuong ViewHolder <=> gan java voi layout
        imageView = (ImageView) findViewById(R.id.img_canhan_entity);
        textView= (TextView) findViewById(R.id.txt_canhan_entity);
    }

}
