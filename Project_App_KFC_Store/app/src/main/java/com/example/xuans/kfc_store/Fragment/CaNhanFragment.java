package com.example.xuans.kfc_store.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xuans.kfc_store.Activity.DangNhapActivity;
import com.example.xuans.kfc_store.Activity.HeaderActivity;
import com.example.xuans.kfc_store.Activity.ThongTinTaiKhoanActivity;
import com.example.xuans.kfc_store.Adapter.CaNhanAdapter;
import com.example.xuans.kfc_store.Entity.CaNhan;
import com.example.xuans.kfc_store.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CaNhanFragment extends Fragment {
    private TextView txt_canhan;
    private ListView listCaNhan;
    private ArrayList<CaNhan> caNhans = new ArrayList<>();
    private CaNhanAdapter caNhanAdapter;
    public CaNhanFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.canhan_fragment, container, false);
        if(caNhans.size()<1){
        loatDuLieuAdapterCaNhan();}
        listCaNhan = view.findViewById(R.id.list_canhan_fragment);
        txt_canhan =view.findViewById(R.id.txt_canhan_fragment);
        caNhanAdapter = new CaNhanAdapter(getActivity(),R.layout.canhan_entity,caNhans);
       /* String[] items = new String[] { "Item 1", "Item 2", "Item 3" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.canhan_entity,R.id.txt_canhan_entity, items);*/
        listCaNhan.setAdapter(caNhanAdapter);
        listCaNhan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(getActivity(), ThongTinTaiKhoanActivity.class);
                    startActivity(intent);
                }
                if(position==3){
                    Intent inten = new Intent(getActivity(), DangNhapActivity.class);
                    startActivity(inten);
                }
            }
        });
        Intent intent = getActivity().getIntent();
        String tenNguoiDung = intent.getStringExtra("Ten_Nguoi_Dung");
        txt_canhan.setText(tenNguoiDung);
        Intent intent1 = new Intent(getActivity(),HeaderActivity.class);
        intent1.putExtra("Ten_Nguoi_Dung",tenNguoiDung);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

       public void loatDuLieuAdapterCaNhan(){
        caNhans.add(new CaNhan(R.drawable.user1, "Họ Và Tên"));
        caNhans.add(new CaNhan(R.drawable.diac, "Địa Chỉ"));
        caNhans.add(new CaNhan(R.drawable.call, "Số Điện Thoại"));
        caNhans.add(new CaNhan(R.drawable.ema, "Email"));
        caNhans.add(new CaNhan(R.drawable.dangxu, "Đăng Xuất"));
    }

}
