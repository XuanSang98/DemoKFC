package com.example.xuans.kfc_store.Activity;

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
import com.example.xuans.kfc_store.Entity.UpdateThongTinTaiKhoan;
import com.example.xuans.kfc_store.R;

import java.util.HashMap;
import java.util.Map;

public class ThongTinTaiKhoanActivity extends AppCompatActivity {
    private Button btn_capNhat;
    private EditText txt_email,txt_matkhau,txt_nhaplaimatkhau,txt_tennguoidung,txt_sodienthoai,txt_diachi;
    int id =0;
    String urlUpdate="http://192.168.5.103:81/data_kfc_store/updatedata_dangkitaikhoan.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtintaikhoan_main);
        btn_capNhat = findViewById(R.id.btn_capnhat_thongtaikhoan);
        txt_email = findViewById(R.id.txt_mail_thongtintaikhoan);
        txt_matkhau = findViewById(R.id.txt_matkhau_thongtintaikhoan);
        txt_nhaplaimatkhau = findViewById(R.id.txt_nhaplaimatkhau_thongtintaikhoan);
        txt_tennguoidung = findViewById(R.id.txt_tennguoidung_thongtintaikhoan);
        txt_sodienthoai = findViewById(R.id.txt_sodienthoai_thongtintaikhoan);
        txt_diachi = findViewById(R.id.txt_diachi_thongtintaikhoan);
        UpdateThongTinTaiKhoan thongTinTaiKhoan = new UpdateThongTinTaiKhoan();
        id =thongTinTaiKhoan.getId();
        txt_email.setText(thongTinTaiKhoan.getEmail());
        txt_matkhau.setText(thongTinTaiKhoan.getMatKhau());
        txt_nhaplaimatkhau.setText(thongTinTaiKhoan.getNhapLaiMatKhau());
        txt_tennguoidung.setText(thongTinTaiKhoan.getTenNguoiDung());
        txt_sodienthoai.setText(thongTinTaiKhoan.getSoDienThoai());
        txt_diachi.setText(thongTinTaiKhoan.getDiaChi());
        btn_capNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapNhatThongTin(urlUpdate);
            }
        });
    }
    private void CapNhatThongTin(String url){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("Thanh Cong")){
                    Toast.makeText(ThongTinTaiKhoanActivity.this,"Update Thành Công",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ThongTinTaiKhoanActivity.this,"Loi Update",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThongTinTaiKhoanActivity.this,"Xay Ra Loi",Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>stringMap = new HashMap<>();
                stringMap.put("id",String.valueOf(id));
                stringMap.put("email",txt_email.getText().toString().trim());
                stringMap.put("matkhau",txt_matkhau.getText().toString().trim());
                stringMap.put("nhaplaimatkhau",txt_nhaplaimatkhau.getText().toString().trim());
                stringMap.put("tennguoidung",txt_tennguoidung.getText().toString().trim());
                stringMap.put("sodienthoai",txt_sodienthoai.getText().toString().trim());
                stringMap.put("diachi",txt_diachi.getText().toString().trim());

                return stringMap;

            }
        };
        requestQueue.add(stringRequest);

    }
}
