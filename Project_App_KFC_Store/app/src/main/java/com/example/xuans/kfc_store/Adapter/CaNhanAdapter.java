package com.example.xuans.kfc_store.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuans.kfc_store.CustomViewAdapter.CustomView;
import com.example.xuans.kfc_store.Entity.CaNhan;
import com.example.xuans.kfc_store.Fragment.CaNhanFragment;
import com.example.xuans.kfc_store.R;

import java.util.List;

public class CaNhanAdapter extends ArrayAdapter <CaNhan> {
    private Context context;
    private int resource;
    private List<CaNhan> objects;

    public CaNhanAdapter(@NonNull Context context, int resource, @NonNull List<CaNhan> objects) {
        super(context, resource, objects);
        this.context= context;
        this.objects =objects;
        this.resource=resource;

    }


    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        View view=convertView;
        ViewHolder viewHolder = new ViewHolder();
        if(view == null){
            view = new CustomView(context);
        }
        CaNhan caNhan = objects.get(position);
        if(caNhan!=null){
            viewHolder.imageView = view.findViewById(R.id.img_canhan_entity);
            viewHolder.textView = view.findViewById(R.id.txt_canhan_entity);
            viewHolder.imageView.setImageResource(caNhan.getImgCaNhan());
            viewHolder.textView.setText(caNhan.getThongTinCaNhan());
        }
        return view;
    }

    public class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
