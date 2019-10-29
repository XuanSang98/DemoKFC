package com.example.xuans.kfc_store.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuans.kfc_store.Entity.DanhSachThucDon;
import com.example.xuans.kfc_store.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachThucDonAdapter extends BaseAdapter {
    ArrayList<DanhSachThucDon>danhSachThucDons;
    Context context;

    public DanhSachThucDonAdapter(ArrayList<DanhSachThucDon> danhSachThucDons, Context context) {
        this.danhSachThucDons = danhSachThucDons;
        this.context = context;
    }

    @Override
    public int getCount() {
        return danhSachThucDons.size();
    }

    @Override
    public Object getItem(int position) {
        return danhSachThucDons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        TextView txt_danhsachthucdon;
        ImageView img_danhsachthucdon;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView ==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.danhsachthucdon_entity,null);
            viewHolder.txt_danhsachthucdon = convertView.findViewById(R.id.txt_danhsachthucdon);
            viewHolder.img_danhsachthucdon = convertView.findViewById(R.id.img_danhsachthucdon);
            convertView.setTag(viewHolder);
        }else {
            viewHolder =(ViewHolder)convertView.getTag();

        }
        DanhSachThucDon danhSachThucDon=(DanhSachThucDon)getItem(position);
        viewHolder.txt_danhsachthucdon.setText(danhSachThucDon.getTen_Thuc_Don());
        Picasso.with(context).load(danhSachThucDon.getHinhAnh_ThucDon()).placeholder(R.drawable.no_image).error(R.drawable.error).into(viewHolder.img_danhsachthucdon);
        return convertView;
    }
}
