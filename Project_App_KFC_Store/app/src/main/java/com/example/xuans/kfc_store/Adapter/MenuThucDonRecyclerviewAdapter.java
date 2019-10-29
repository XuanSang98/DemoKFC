package com.example.xuans.kfc_store.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuans.kfc_store.Entity.MenuThucDonRecyclerview;
import com.example.xuans.kfc_store.R;

import java.util.ArrayList;

public class MenuThucDonRecyclerviewAdapter extends RecyclerView.Adapter<MenuThucDonRecyclerviewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MenuThucDonRecyclerview> menuthucdon;

    public MenuThucDonRecyclerviewAdapter(Context context, ArrayList<MenuThucDonRecyclerview> menuthucdon) {
        this.context = context;
        this.menuthucdon = menuthucdon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater =LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.custom_recyclerview_entity,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.imageView.setImageResource(menuthucdon.get(i).getImg_menu_thucdon());
        viewHolder.textView.setText(menuthucdon.get(i).getTxt_menu_thucdon());
    }

    @Override
    public int getItemCount() {
        return menuthucdon.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_custom_recyclerview_entity);
            textView = itemView.findViewById(R.id.txt_custom_recyclerview_entity);
        }
    }
}
