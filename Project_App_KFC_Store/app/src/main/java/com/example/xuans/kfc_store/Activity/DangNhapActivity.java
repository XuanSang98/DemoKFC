package com.example.xuans.kfc_store.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.xuans.kfc_store.Entity.DangKiTaiKhoan;
import com.example.xuans.kfc_store.Internet.CheckConnection;
import com.example.xuans.kfc_store.Internet.Server;
import com.example.xuans.kfc_store.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DangNhapActivity extends AppCompatActivity {
    private TextView txtDangKi;
    private Button btnDangNhap;
    private EditText txt_email,txt_matkhau;
    ArrayList<DangKiTaiKhoan> obj = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap_main);
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        new data_dangkitaikhoan().execute();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            AnhXa();
            suLySuKien();
        }else {
            CheckConnection.ShowToas_Short( getApplicationContext(),"Kiem Tra Ket Noi");
        }
    }
    private void suLySuKien() {
        txtDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(DangNhapActivity.this,DangKiTaiKhoanActivity.class);
                startActivity(inten);
            }
        });
    }


    private void AnhXa() {
        txtDangKi = findViewById(R.id.txt_dangki_dangnhap);
        btnDangNhap = findViewById(R.id.btn_dangnhap_dangnhap);
        txt_email = findViewById(R.id.txt_email_dangnhap);
        txt_matkhau = findViewById(R.id.txt_matkhau_dangnhap);
    }

    class data_dangkitaikhoan extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            String con = Connection(Server.Duongdandangnhap);
            return ReadData(con);
        }

        @Override
        protected void onPostExecute(String s) {
            btnDangNhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int j=0;
                    for (int i=0;i<obj.size();i++){
                        if(txt_email.getText().toString().equals(obj.get(i).getEmail()) && txt_matkhau.getText().toString().equals(obj.get(i).getMatKhau())){
                            Intent intent = new Intent(DangNhapActivity.this,MainActivity.class);
                            intent.putExtra("Ten_Nguoi_Dung",obj.get(i).getEmail());
                            //Intent intent1 = new Intent(DangNhapActivity.this,HeaderActivity.class);
                            //intent1.putExtra("Ten",obj.get(i).getEmail());
                           // startActivity(intent1);
                            startActivity(intent);
                        }else{
                            j=j+i;
                        }
                    }
                    if(j==obj.size()){
                        Toast.makeText(DangNhapActivity.this,"Email Hoặc Mật Khẩu Không Chính Xác Vui Lòng Kiểm Tra Lại",Toast.LENGTH_LONG).show();
                    }

                }
            });
        }

        //Kiem Tra Ket Noi
        private String Connection(String urls) {
            StringBuilder sb = new StringBuilder();
            URL url = null;
            try {
                url = new URL(urls);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                httpURLConnection.disconnect();
                inputStream.close();
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }



        //Doc Du Lieu Json
        private String ReadData(String data) {
            String str = "";
            try {
                JSONArray array = new JSONArray(data);
                for (int i = 1; i < array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    String email = jsonObject.getString("Email");
                    String matKhau = jsonObject.getString("Mat_Khau");
                    obj.add(new DangKiTaiKhoan(email,matKhau));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

}
