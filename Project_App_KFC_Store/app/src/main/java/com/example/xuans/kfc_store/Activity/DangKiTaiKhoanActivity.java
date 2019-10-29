package com.example.xuans.kfc_store.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.xuans.kfc_store.R;

import java.util.HashMap;
import java.util.Map;

public class DangKiTaiKhoanActivity extends AppCompatActivity {
    EditText txtEmail,txtMatKhau,txtNhapLaiMatKhau,txtSoDienThoai,txtTenNguoiDung,txtDiaChi;
    Button btnDangKi;
    String urlInsert = "http://172.20.10.10:81/data_kfc_store/insertdata_dangkitaikhoan.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangkitaikhoan_main);
        txtEmail = findViewById(R.id.txt_mail_dangkitaikhoan);
        txtMatKhau = findViewById(R.id.txt_matkhau_dangkitaikhoan);
        txtNhapLaiMatKhau = findViewById(R.id.txt_nhaplaimatkhau_dangkitaikhoan);
        txtTenNguoiDung = findViewById(R.id.txt_tennguoidung_dangkitaikhoan);
        txtSoDienThoai = findViewById(R.id.txt_sodienthoai_dangkitaikhoan);
        txtDiaChi = findViewById(R.id.txt_diachi_dangkitaikhoan);
        btnDangKi = findViewById(R.id.btn_dangki_dangkitaikhoan);
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemVaoGioHang(urlInsert);
                Intent intent = new Intent(DangKiTaiKhoanActivity.this,DangNhapActivity.class);
                startActivity(intent);
            }
        });
    }
    private void ThemVaoGioHang(String url){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("Thanh Cong")){
                    Toast.makeText(DangKiTaiKhoanActivity.this,"Đăng Kí Thành Công",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(DangKiTaiKhoanActivity.this,"Loi Them",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DangKiTaiKhoanActivity.this,"Xay Ra Loi",Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>stringMap = new HashMap<>();
                stringMap.put("email",txtEmail.getText().toString().trim());
                stringMap.put("matKhau",txtMatKhau.getText().toString().trim());
                stringMap.put("nhapLaiMatKhau",txtNhapLaiMatKhau.getText().toString().trim());
                stringMap.put("tenNguoiDung",txtTenNguoiDung.getText().toString().trim());
                stringMap.put("soDienThoai",txtSoDienThoai.getText().toString().trim());
                stringMap.put("diaChi",txtDiaChi.getText().toString().trim());

                return stringMap;

            }
        };
        requestQueue.add(stringRequest);

    }
}
