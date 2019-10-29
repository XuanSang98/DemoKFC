package com.example.xuans.kfc_store.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xuans.kfc_store.Adapter.AdapterCustomNavigationView;
import com.example.xuans.kfc_store.Adapter.CaNhanAdapter;
import com.example.xuans.kfc_store.CovertImage.ImageConverter;
import com.example.xuans.kfc_store.Entity.CaNhan;
import com.example.xuans.kfc_store.Entity.CustomNavigationView;
import com.example.xuans.kfc_store.Fragment.CaNhanFragment;
import com.example.xuans.kfc_store.Fragment.CongDongFragment;
import com.example.xuans.kfc_store.Fragment.KhuyenMaiFragment;
import com.example.xuans.kfc_store.Fragment.ThucDonFragment;
import com.example.xuans.kfc_store.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private BottomNavigationView main_bottomnavigation;
    private FrameLayout main_frame;
    private CaNhanFragment caNhanFragment;
    private ThucDonFragment thucDonFragment;
    private CongDongFragment congDongFragment;
    private KhuyenMaiFragment khuyenMaiFragment;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private CustomNavigationView[] dataNavigationview;
    private ListView listViewNavigationView;
    private AdapterCustomNavigationView customNavigationView;
    private TextView txt_chusohuu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ket Noi Vs XML
        toolbar = findViewById(R.id.toolbar_main);
        drawerLayout =findViewById(R.id.drawerlayout);
        main_bottomnavigation = findViewById(R.id.main_bottomnavigation);
        main_frame = findViewById(R.id.main_frame);
        listViewNavigationView = findViewById(R.id.listview_custom_navigationview);
        navigationView = findViewById(R.id.navigationview);
        txt_chusohuu = findViewById(R.id.txt_chusohuu);
        Load_Data();
        //Khai Bao Doi Tuong
        caNhanFragment = new CaNhanFragment();
        khuyenMaiFragment = new KhuyenMaiFragment();
        congDongFragment= new CongDongFragment();
        thucDonFragment = new ThucDonFragment();

        //Thuc Thi Phuong Thuc
        Actionbar();
        setFragment(thucDonFragment);
        loadDuLieuLenNavigationView();

        //Thuc Thi Adapter
        customNavigationView = new AdapterCustomNavigationView(this,R.layout.custom_navigationview_entity,dataNavigationview);
        listViewNavigationView.setAdapter(customNavigationView);
        //
        //Bat Su Kien listna
        listViewNavigationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    setFragment(caNhanFragment);
                    drawerLayout.closeDrawers();
                }else if(position == 4){
                    Intent intent = new Intent(MainActivity.this,TroGiupActivity.class);
                    startActivity(intent);
                }else if(position==5){
                    Intent intent = new Intent(MainActivity.this,DangNhapActivity.class);
                    startActivity(intent);
                }
            }
        });
        //Xu Ly Su Kien OnClick
        main_bottomnavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_bottomnavigation_thucdon:
                        main_bottomnavigation.setItemBackgroundResource(R.color.MauDoKFC);
                        toolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f1022a")));
                        //toolbar.setLogo(R.drawable.logo);
                        main_bottomnavigation.setBackgroundColor(Color.parseColor("#f1022a"));
                        setFragment(thucDonFragment);
                        return true;
                    case R.id.menu_bottomnavigation_nhahang:
                        main_bottomnavigation.setBackgroundColor(Color.parseColor("#f1022a"));
                        main_bottomnavigation.setItemBackgroundResource(R.color.MauDoKFC);
                        toolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f1022a")));
                        setFragment(khuyenMaiFragment);
                        return true;
                    /*case R.id.menu_bottomnavigation_congdong:
                        main_bottomnavigation.setBackgroundColor(Color.parseColor("#2a8ad9"));
                        main_bottomnavigation.setItemBackgroundResource(R.color.MauXanhBien);
                        toolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2a8ad9")));
                        setFragment(congDongFragment);
                        return true;*/
                    case R.id.menu_bottomnavigation_canhan:
                        main_bottomnavigation.setBackgroundColor(Color.parseColor("#f1022a"));
                        main_bottomnavigation.setItemBackgroundResource(R.color.MauDoKFC);
                        toolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f1022a")));
                        setFragment(caNhanFragment);
                        return true;
                    default:
                        return false;
                }

            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });


        //Bitmap Hinh Anh
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.vietnam);
    }

    private void Load_Data() {

    }

    //Ham Nay Dung De Khong Cho Back Lai
    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }

    //Custom Actionbar
    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, fragment);
        transaction.commit();
    }


    //Ghi De 2 Phuong Thuc De Thuc Thi Menu Actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_actionbar_tongdaivien:
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
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
                Intent intent = new Intent(MainActivity.this,GioHangActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"Giỏ Hàng",Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //Khai Bao Du Lieu Do Len Navigationiew
    public void loadDuLieuLenNavigationView(){
        dataNavigationview = new CustomNavigationView[6];
        dataNavigationview[0]= new CustomNavigationView(R.drawable.user1,"Trang Cá Nhân");
        dataNavigationview[1]= new CustomNavigationView(R.drawable.hoadon,"Hóa Đơn");
        dataNavigationview[2]= new CustomNavigationView(R.drawable.caidat,"Cài Đặt");
        dataNavigationview[3]= new CustomNavigationView(R.drawable.quantri,"Quản Trị");
        dataNavigationview[4]= new CustomNavigationView(R.drawable.trogiup,"Trợ Giúp");
        dataNavigationview[5]= new CustomNavigationView(R.drawable.dangxu,"Đăng Xuất");
    }


}