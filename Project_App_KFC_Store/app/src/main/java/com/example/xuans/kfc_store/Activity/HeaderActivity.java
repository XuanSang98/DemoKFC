package com.example.xuans.kfc_store.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.xuans.kfc_store.R;
public class HeaderActivity extends AppCompatActivity {
    private TextView txt_chusohuu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header_main);
        txt_chusohuu = findViewById(R.id.txt_chusohu);
        Intent intent = this.getIntent();
        String tenNguoiDung = intent.getStringExtra("Ten_Nguoi_Dung");
        txt_chusohuu.setText("Hell");
    }
}
