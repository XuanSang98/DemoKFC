package com.example.xuans.kfc_store.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xuans.kfc_store.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CongDongFragment extends Fragment {


    public CongDongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.congdong_fragment, container, false);
    }

}
