package com.example.xuans.kfc_store.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuans.kfc_store.Activity.GioHangActivity;
import com.example.xuans.kfc_store.Entity.GioHang;
import com.example.xuans.kfc_store.Fragment.ThucDonFragment;
import com.example.xuans.kfc_store.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> arrayGioHang;

    public GioHangAdapter(Context context, ArrayList<GioHang> arrayGioHang) {
        this.context = context;
        this.arrayGioHang = arrayGioHang;
    }

    @Override
    public int getCount() {
        return arrayGioHang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayGioHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    static class ViewHolder{
        public ImageView img_giohang;
        public TextView txt_tengiohang,txt_giagiohang;
        public Button btnminus,btnplus,btnvalues;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            viewHolder= new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.giohang1_entity,null);
            viewHolder.txt_tengiohang=convertView.findViewById(R.id.txt_tenmonhang_giohang_entity);
            viewHolder.txt_giagiohang = convertView.findViewById(R.id.txt_giamonhang_giohang_entity);
            viewHolder.img_giohang = convertView.findViewById(R.id.img_tengiohang_entity);
            viewHolder.btnminus = convertView.findViewById(R.id.btn_minus);
            viewHolder.btnvalues =(Button) convertView.findViewById(R.id.btn_valus);
            viewHolder.btnplus = convertView.findViewById(R.id.btn_plus);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            GioHang gioHang = (GioHang) getItem(position);
            viewHolder.txt_tengiohang.setText(gioHang.getTensp());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            viewHolder.txt_giagiohang.setText(decimalFormat.format(gioHang.getGiasp())+" VND");
            Picasso.with(context).load(gioHang.getHinhsp()).placeholder(R.drawable.no_image).error(R.drawable.error).into(viewHolder.img_giohang);
            viewHolder.btnvalues.setText(decimalFormat.format(gioHang.getSoluongsp()));
            int soluong = Integer.parseInt(viewHolder.btnvalues.getText().toString());
            if (soluong >= 10) {
                viewHolder.btnplus.setVisibility(View.INVISIBLE);
                viewHolder.btnminus.setVisibility(View.VISIBLE);
            } else if (soluong <= 1) {
                viewHolder.btnminus.setVisibility(View.INVISIBLE);
                viewHolder.btnplus.setVisibility(View.VISIBLE);
            } else if (soluong >= 1) {
                viewHolder.btnminus.setVisibility(View.VISIBLE);
                viewHolder.btnplus.setVisibility(View.VISIBLE);
            }
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View convertView) {
                int s1moinhat =Integer.parseInt(viewHolder.btnvalues.getText().toString())+1;
                int s1ht =ThucDonFragment.arraygiohangs.get(position).getSoluongsp();
                long giaht =ThucDonFragment.arraygiohangs.get(position).getGiasp();
                ThucDonFragment.arraygiohangs.get(position).setSoluongsp(s1moinhat);
                long giamoinhat = (giaht*s1moinhat)/s1ht;
                ThucDonFragment.arraygiohangs.get(position).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.txt_giagiohang.setText(decimalFormat.format(giamoinhat));
                GioHangActivity.EvenUltil();
                if(s1moinhat>9){
                    viewHolder.btnplus.setVisibility(View.INVISIBLE);
                    viewHolder.btnminus.setVisibility(View.VISIBLE);
                    viewHolder.btnvalues.setText(String.valueOf(s1moinhat));
                }else {
                    viewHolder.btnplus.setVisibility(View.VISIBLE);
                    viewHolder.btnminus.setVisibility(View.VISIBLE);
                    viewHolder.btnvalues.setText(String.valueOf(s1moinhat));
                }
            }
        });
            viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int s1moinhat =Integer.parseInt(viewHolder.btnvalues.getText().toString())-1;
                    int s1ht =ThucDonFragment.arraygiohangs.get(position).getSoluongsp();
                    long giaht =ThucDonFragment.arraygiohangs.get(position).getGiasp();
                    ThucDonFragment.arraygiohangs.get(position).setSoluongsp(s1moinhat);
                    long giamoinhat = (giaht*s1moinhat)/s1ht;
                    ThucDonFragment.arraygiohangs.get(position).setGiasp(giamoinhat);
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    viewHolder.txt_giagiohang.setText(decimalFormat.format(giamoinhat));
                    GioHangActivity.EvenUltil();
                    if(s1moinhat<2){
                        viewHolder.btnminus.setVisibility(View.INVISIBLE);
                        viewHolder.btnplus.setVisibility(View.VISIBLE);
                        viewHolder.btnvalues.setText(String.valueOf(s1moinhat));
                    }else {
                        viewHolder.btnplus.setVisibility(View.VISIBLE);
                        viewHolder.btnminus.setVisibility(View.VISIBLE);
                        viewHolder.btnvalues.setText(String.valueOf(s1moinhat));
                    }
                }
            });
        return convertView;
    }
}
