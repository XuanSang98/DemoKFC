package com.example.xuans.kfc_store.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xuans.kfc_store.Entity.ChiTietThucDon;
import com.example.xuans.kfc_store.Entity.GioHang;
import com.example.xuans.kfc_store.Fragment.ThucDonFragment;
import com.example.xuans.kfc_store.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView img_anhsanpham;
    TextView txt_tensanpham,txt_giasanpham;
    Spinner spinner;
    Button btn_themvaogiohang;
    int 	ID_ThucDonChiTiet=0;
    int ID_ThucDon=0;
    String Ten_ChiTiet="";
    int 	Gia_Tien=0;
    String Hinh_Anh="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsanpham_main);
        AnhXa();
        Actionbar();
        GetInFormation();
        CatchEventSpinner();
        EventButton();
    }

    private void EventButton() {
        btn_themvaogiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ThucDonFragment.arraygiohangs.size()>0){
                    int s1=Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exite =false;
                    for(int i=0;i<ThucDonFragment.arraygiohangs.size();i++){
                        if(ThucDonFragment.arraygiohangs.get(i).getIdsp() == ID_ThucDonChiTiet){
                            ThucDonFragment.arraygiohangs.get(i).setSoluongsp(ThucDonFragment.arraygiohangs.get(i).getSoluongsp()+s1);
                            if(ThucDonFragment.arraygiohangs.get(i).getSoluongsp()>=10){
                                ThucDonFragment.arraygiohangs.get(i).setSoluongsp(10);
                            }
                            ThucDonFragment.arraygiohangs.get(i).setGiasp(Gia_Tien*ThucDonFragment.arraygiohangs.get(i).getSoluongsp());
                            exite=true;
                        }
                    }
                    if(exite==false){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long giamoi = soluong*Gia_Tien;
                        ThucDonFragment.arraygiohangs.add(new GioHang(ID_ThucDonChiTiet,Ten_ChiTiet,giamoi,Hinh_Anh,soluong));
                    }
                }else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long giamoi = soluong*Gia_Tien;
                    ThucDonFragment.arraygiohangs.add(new GioHang(ID_ThucDonChiTiet,Ten_ChiTiet,giamoi,Hinh_Anh,soluong));
                }
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[]soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer>arrayAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void GetInFormation() {
        ChiTietThucDon chiTietThucDon = (ChiTietThucDon) getIntent().getSerializableExtra("thongtinsanpham");
        ID_ThucDonChiTiet= chiTietThucDon.getID_ThucDonChiTiet();
        ID_ThucDon =chiTietThucDon.getID_ThucDon();
        Ten_ChiTiet = chiTietThucDon.getTen_ChiTiet();
        Gia_Tien = chiTietThucDon.getGia_Tien();
        Hinh_Anh = chiTietThucDon.getHinh_Anh();
        txt_tensanpham.setText(Ten_ChiTiet);
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        txt_giasanpham.setText("Giá    "+decimalFormat.format(Gia_Tien)+" VND");
        Picasso.with(getApplicationContext()).load(chiTietThucDon.getHinh_Anh()).placeholder(R.drawable.no_image).error(R.drawable.error).into(img_anhsanpham);



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_actionbar_tongdaivien:
                AlertDialog.Builder builder= new AlertDialog.Builder(ChiTietSanPhamActivity.this);
                builder.setTitle("Kết Nối Trực Tiếp Tới Tổng Đài Viên");
                builder.setMessage("Gọi Ngay Hoàn Toàn Miễn Phí");
                builder.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Hủy Bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog =builder.create();
                alertDialog.show();
                return true;
            case R.id.menu_actionbar_giohang:
                Intent intent = new Intent(ChiTietSanPhamActivity.this,GioHangActivity.class);
                startActivity(intent);
                Toast.makeText(ChiTietSanPhamActivity.this,"Giỏ Hàng",Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.tb_chitietsanpham_main);
        img_anhsanpham = findViewById(R.id.img_anhsanpham_chitietsanpham);
        txt_tensanpham = findViewById(R.id.txt_tensanpham_chitietsanpham);
        txt_giasanpham = findViewById(R.id.txt_giasanpham_chitietsanpham);
        spinner = findViewById(R.id.spinner_chitietsanpham);
        btn_themvaogiohang = findViewById(R.id.btn_themvaogiohang);
    }
    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
