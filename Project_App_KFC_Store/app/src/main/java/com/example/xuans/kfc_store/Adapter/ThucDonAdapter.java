package com.example.xuans.kfc_store.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.xuans.kfc_store.R;
import com.example.xuans.kfc_store.Entity.ChiTietThucDon;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ThucDonAdapter extends BaseAdapter {
    Context context;
    ArrayList<ChiTietThucDon> arraythucdon;

    public ThucDonAdapter(Context context, ArrayList<ChiTietThucDon> arraythucdon) {
        this.context = context;
        this.arraythucdon = arraythucdon;
    }

    @Override
    public int getCount() {
        return arraythucdon.size();
    }

    @Override
    public Object getItem(int position) {
        return arraythucdon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public TextView txt_tenthucdonchitiet,txt_giatienthucdonchitiet;
        public ImageView img_thucdonchitiet;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder= null;
        if (convertView==null){
            viewHolder= new ViewHolder();
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.thucdonchitiet_entity,null);
            viewHolder.txt_tenthucdonchitiet=convertView.findViewById(R.id.txt_tenthucdonchitiet_entity);
            viewHolder.txt_giatienthucdonchitiet =convertView.findViewById(R.id.txt_giatienthucdonchitiet_entity);
            viewHolder.img_thucdonchitiet = convertView.findViewById(R.id.img_thucdonchitiet_entity);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        ChiTietThucDon chiTietThucDon =(ChiTietThucDon)getItem(position);
        viewHolder.txt_tenthucdonchitiet.setText(chiTietThucDon.getTen_ChiTiet());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txt_giatienthucdonchitiet.setText(decimalFormat.format(chiTietThucDon.getGia_Tien()));
        Picasso.with(context).load(chiTietThucDon.getHinh_Anh()).placeholder(R.drawable.no_image).error(R.drawable.error).into(viewHolder.img_thucdonchitiet);
        return convertView;
    }
}
