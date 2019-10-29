package com.example.xuans.kfc_store.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.xuans.kfc_store.Adapter.ThucDonAdapter;
import com.example.xuans.kfc_store.Entity.ChiTietThucDon;
import com.example.xuans.kfc_store.Internet.CheckConnection;
import com.example.xuans.kfc_store.Internet.Server;
import com.example.xuans.kfc_store.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrangMiengVaThucUongActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView gridView;
    ThucDonAdapter burgerVaComAdapter;
    ArrayList<ChiTietThucDon> chiTietThucDons;
    int ID=0;
    int page=1;
    View footerview;
    boolean limitdata =false;
    boolean isLoading = false;
    mHandler mHandler1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangmiengvathucuong_main);
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            AnhXa();
            getIdPhanAnComBo();
            getData(page);
            LoadMoreData();
            Actionbar();
        }else {
            CheckConnection.ShowToas_Short( getApplicationContext(),"Kiem Tra Ket Noi");
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_actionbar_tongdaivien:
                AlertDialog.Builder builder= new AlertDialog.Builder(TrangMiengVaThucUongActivity.this);
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
                Intent intent = new Intent(TrangMiengVaThucUongActivity.this,GioHangActivity.class);
                startActivity(intent);
                Toast.makeText(TrangMiengVaThucUongActivity.this,"Giỏ Hàng",Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void LoadMoreData() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ChiTietSanPhamActivity.class);
                intent.putExtra("thongtinsanpham",chiTietThucDons.get(position));
                startActivity(intent);
            }
        });
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstItem, int visibleItem, int totalItem) {
                if(firstItem + visibleItem ==totalItem && totalItem!=0 &&isLoading==false && limitdata == false){
                    isLoading=true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void getIdPhanAnComBo() {
        ID=getIntent().getIntExtra("ID_ThucDon",-1);
    }

    private void getData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongan= Server.Duongdanphânncombo+String.valueOf(Page);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, duongan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int 	ID_ThucDonChiTiet=0;
                int ID_ThucDon=0;
                String Ten_ChiTiet="";
                int 	Gia_Tien=0;
                String Hinh_Anh="";
                if(response!=null && response.length()!=2){
                    gridView.removeFooterView(footerview);
                    JSONArray array = null;
                    try {
                        array = new JSONArray(response);
                        for (int i=0;i<array.length();i++) {
                            JSONObject jsonObject=array.getJSONObject(i);
                            ID_ThucDonChiTiet=jsonObject.getInt("ID_ThucDonChiTiet");
                            ID_ThucDon=jsonObject.getInt("ID_ThucDon");
                            Ten_ChiTiet=jsonObject.getString("Ten_ChiTiet");
                            Gia_Tien =jsonObject.getInt("Gia_Tien");
                            Hinh_Anh=jsonObject.getString("Hinh_Anh");
                            chiTietThucDons.add(new ChiTietThucDon(ID_ThucDonChiTiet,ID_ThucDon,Ten_ChiTiet,Gia_Tien,Hinh_Anh));
                            burgerVaComAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    limitdata=true;
                    gridView.removeFooterView(footerview);
                    CheckConnection.ShowToas_Short(getApplicationContext(),"Da Het Du Lieu");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<String,String>();
                map.put("ID_ThucDon",String.valueOf(ID));
                return map;
            }
        };
        requestQueue.add(stringRequest);

    }
    private void AnhXa() {
        toolbar = findViewById(R.id.tb_trangmiengvathucuong_main);
        gridView = findViewById(R.id.list_trangmiengvathucuong_main);
        chiTietThucDons = new ArrayList<>();
        burgerVaComAdapter = new ThucDonAdapter(getApplicationContext(),chiTietThucDons);
        gridView.setAdapter(burgerVaComAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar,null);
        mHandler1 = new mHandler();
    }
    public class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    gridView.addFooterView(footerview);
                    break;
                case 1:
                    getData(++page);
                    isLoading=false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler1.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHandler1.obtainMessage(1);
            mHandler1.sendMessage(message);
            super.run();
        }
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
