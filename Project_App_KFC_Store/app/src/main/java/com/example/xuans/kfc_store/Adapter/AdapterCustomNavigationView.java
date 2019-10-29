package com.example.xuans.kfc_store.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuans.kfc_store.CustomViewAdapter.CustomViewAdapterNavigation;
import com.example.xuans.kfc_store.CustomViewAdapter.CustomViewAdapterThucDon;
import com.example.xuans.kfc_store.Entity.CustomNavigationView;
import com.example.xuans.kfc_store.R;

public class AdapterCustomNavigationView extends ArrayAdapter<CustomNavigationView> {
    private Context context;
    private int resource;
    private CustomNavigationView[] objects;

    public AdapterCustomNavigationView(@NonNull Context context, int resource, @NonNull CustomNavigationView[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = new ViewHolder();
        if(view==null){
            view = new CustomViewAdapterNavigation(context);
        }
        CustomNavigationView customNavigationView = objects[position];
        if(customNavigationView!=null){
            viewHolder.imageView = view.findViewById(R.id.img_custom_navigationview_entity);
            viewHolder.textView = view.findViewById(R.id.txt_custom_navigationview_entity);
            viewHolder.imageView.setImageResource(customNavigationView.getHinhanh());
            viewHolder.textView.setText(customNavigationView.getTen());
        }
        return view;
    }

    public class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
