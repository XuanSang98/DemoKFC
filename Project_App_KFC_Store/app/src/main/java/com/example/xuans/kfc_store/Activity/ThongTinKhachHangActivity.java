package com.example.xuans.kfc_store.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.xuans.kfc_store.Entity.GioHang;
import com.example.xuans.kfc_store.Fragment.ThucDonFragment;
import com.example.xuans.kfc_store.Internet.CheckConnection;
import com.example.xuans.kfc_store.Internet.Server;
import com.example.xuans.kfc_store.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinKhachHangActivity extends AppCompatActivity {
    EditText edtTenKhachHang,edtSoDienThoai,edtEmail;
    Button btnXacNhan,btnTroVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtinkhachhang_main);
        AnhXa();
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            EventButton();
        }
        else {
            CheckConnection.ShowToas_Short(getApplicationContext(),"Bạn Hãy Kiểm Tra Lại Kết Nối");
        }
    }

    private void EventButton() {
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten = edtTenKhachHang.getText().toString().trim();
                final String sodienthoai = edtSoDienThoai.getText().toString().trim();
                final String email = edtEmail.getText().toString().trim();
                if(ten.length()>0 && sodienthoai.length()>0 && email.length()>0){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.donhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Log.d("madonhang",madonhang);
                            if(Integer.parseInt(madonhang)>0){
                                RequestQueue request = Volley.newRequestQueue(getApplicationContext());
                                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Server.chitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.equals("1")){
                                            ThucDonFragment.arraygiohangs.clear();
                                            CheckConnection.ShowToas_Short(getApplicationContext(),"Ban Da Them Du Lieu Gio Hang Thanh Cong");
                                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);
                                            CheckConnection.ShowToas_Short(getApplicationContext(),"Moi Ban Tiep Tuc Mua Hang");
                                        }else {
                                            CheckConnection.ShowToas_Short(getApplicationContext(),"Du Lieu Gio Hang Da Bi Loi");
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray array = new JSONArray();
                                        for(int i=0;i<ThucDonFragment.arraygiohangs.size();i++){
                                            JSONObject jsonObject= new JSONObject();
                                            try {
                                                jsonObject.put("MaDonHang",madonhang);
                                                jsonObject.put("MaSanPham",ThucDonFragment.arraygiohangs.get(i).getIdsp());
                                                jsonObject.put("TenSanPham",ThucDonFragment.arraygiohangs.get(i).getTensp());
                                                jsonObject.put("GiaSanPham",ThucDonFragment.arraygiohangs.get(i).getGiasp());
                                                jsonObject.put("SoLuongSanPham",ThucDonFragment.arraygiohangs.get(i).getSoluongsp());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            array.put(jsonObject);
                                        }
                                        HashMap<String,String> hashMap= new HashMap<String, String>();
                                        hashMap.put("json",array.toString());

                                        return hashMap;
                                    }
                                };
                                request.add(stringRequest1);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String>hashMap = new HashMap<String, String>();
                            hashMap.put("tenkhachhang",ten);
                            hashMap.put("sodienthoai",sodienthoai);
                            hashMap.put("email",email);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else {
                    CheckConnection.ShowToas_Short(getApplicationContext(),"Hãy Kiểm Tra Lại Dữ Liệu");
                }
            }
        });

    }

    private void AnhXa() {
        edtTenKhachHang = findViewById(R.id.txt_tenkhachhang_thongtinkhachhang);
        edtSoDienThoai = findViewById(R.id.txt_sodienthoaikhachhang_thongtinkhachhang);
        edtEmail = findViewById(R.id.txt_email_thongtinkhachhang);
        btnTroVe = findViewById(R.id.btn_trove_thongtinkhachhang);
        btnXacNhan = findViewById(R.id.btn_xacnhan_thongtinkhachhang);
    }
}
