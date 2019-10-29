package com.example.xuans.kfc_store.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xuans.kfc_store.Adapter.GioHangAdapter;
import com.example.xuans.kfc_store.Fragment.ThucDonFragment;
import com.example.xuans.kfc_store.Internet.CheckConnection;
import com.example.xuans.kfc_store.R;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {
    ListView listView;
    ImageView txtThongBao;
    static TextView txtTongTien;
    Button btnThanhToan,btnTiepTucMua;
    Toolbar toolbar;
    GioHangAdapter gioHangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giohang_main);
        AnhXa();
        Actionbar();
        CheckData();
        EvenUltil();
        CactchOnItemListView();
        EvenButton();
    }

    private void EvenButton() {
        btnTiepTucMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ThucDonFragment.arraygiohangs.size()>0){
                    Intent intent = new Intent(getApplicationContext(),ThongTinKhachHangActivity.class);
                    startActivity(intent);
                }else {
                    CheckConnection.ShowToas_Short(getApplicationContext(),"Giỏ Hàng Của Bạn Chưa Có Sản Phẩm Nào Để Thanh Toán");
                }
            }
        });
    }

    private void CactchOnItemListView() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                builder.setTitle("Xác Nhận Xóa Sản Phẩm");
                builder.setMessage("Bạn Có Chắc Muốn Xóa Sản Phẩm Này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(ThucDonFragment.arraygiohangs.size()<=0){
                            txtThongBao.setVisibility(View.VISIBLE);
                        }else {
                            ThucDonFragment.arraygiohangs.remove(position);
                            gioHangAdapter.notifyDataSetChanged();
                            EvenUltil();
                            if(ThucDonFragment.arraygiohangs.size()<=0){
                                txtThongBao.setVisibility(View.VISIBLE);
                            }else {
                                txtThongBao.setVisibility(View.INVISIBLE);
                                gioHangAdapter.notifyDataSetChanged();
                                EvenUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gioHangAdapter.notifyDataSetChanged();
                        EvenUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_actionbar_tongdaivien:
                android.support.v7.app.AlertDialog.Builder builder= new android.support.v7.app.AlertDialog.Builder(GioHangActivity.this);
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
                android.support.v7.app.AlertDialog alertDialog =builder.create();
                alertDialog.show();
                return true;
            case R.id.menu_actionbar_giohang:
                Intent intent = new Intent(GioHangActivity.this,GioHangActivity.class);
                startActivity(intent);
                Toast.makeText(GioHangActivity.this,"Giỏ Hàng",Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void EvenUltil() {
        long tongtien = 0;
        for (int i=0;i<ThucDonFragment.arraygiohangs.size();i++){
            tongtien+=ThucDonFragment.arraygiohangs.get(i).getGiasp();

        }
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        txtTongTien.setText(decimalFormat.format(tongtien)+ " VND");
    }

    private void CheckData() {
        if(ThucDonFragment.arraygiohangs.size()<=0){
            gioHangAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }else {
            gioHangAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
        }
    }

    private void AnhXa() {
        listView = findViewById(R.id.list_giohang);
        txtThongBao = findViewById(R.id.txt_giohang_thongbao);
        txtTongTien = findViewById(R.id.txttongtien);
        btnThanhToan = findViewById(R.id.btn_thanhtoangiohang);
        btnTiepTucMua = findViewById(R.id.btn_tieptucmuahang);
        toolbar = findViewById(R.id.tb_giohang_main);
        gioHangAdapter = new GioHangAdapter(GioHangActivity.this, ThucDonFragment.arraygiohangs);
        listView.setAdapter(gioHangAdapter);
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
