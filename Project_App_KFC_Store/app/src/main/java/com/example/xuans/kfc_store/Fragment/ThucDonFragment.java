package com.example.xuans.kfc_store.Fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.xuans.kfc_store.Activity.GaRanVaQuayActivity;
import com.example.xuans.kfc_store.Activity.PhanAnComBoActivity;
import com.example.xuans.kfc_store.Activity.ThucAnNheActivity;
import com.example.xuans.kfc_store.Activity.TrangMiengVaThucUongActivity;
import com.example.xuans.kfc_store.Adapter.DanhSachThucDonAdapter;
import com.example.xuans.kfc_store.Entity.DanhSachThucDon;
import com.example.xuans.kfc_store.Entity.GioHang;
import com.example.xuans.kfc_store.Internet.CheckConnection;
import com.example.xuans.kfc_store.Internet.Server;
import com.example.xuans.kfc_store.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThucDonFragment extends Fragment {

    private ViewFlipper viewFlipper;
    private GridView gridviewThucDon;
    private RecyclerView menuThucDonRecyclerview;
    private ArrayList<DanhSachThucDon>mangDanhSachThucDon;
    DanhSachThucDonAdapter danhSachThucDonAdapter;
    ListView listView;
    int ID_ThucDon =0;
    String Ten_Thuc_Don ="";
    String HinhAnh_ThucDon ="";
    /*OnFragmentManager listener;*/
    public ThucDonFragment() {
        // Required empty public constructor
    }
    public static ArrayList<GioHang>arraygiohangs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thucdon_fragment, container, false);
        listView = view.findViewById(R.id.list_thucdon_fragment);
        viewFlipper =view.findViewById(R.id.viewflipper);
        mangDanhSachThucDon = new ArrayList<>();
        danhSachThucDonAdapter = new DanhSachThucDonAdapter(mangDanhSachThucDon,getActivity());
        listView.setAdapter(danhSachThucDonAdapter);
        if(arraygiohangs!=null){

        }else {
            arraygiohangs = new ArrayList<>();
        }
        if(CheckConnection.haveNetworkConnection(getActivity())){
            ActionViewFlipper();
            getDuLieuDanhSachThucDon();
                BatSuKienListView();

        }else {
            CheckConnection.ShowToas_Short(getActivity(),"Bạn Hãy Kiểm Tra Kết Nối");
            
        }
        return view;
    }

    private void BatSuKienListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if(CheckConnection.haveNetworkConnection(getActivity())){
                            Intent intent1= new Intent(getActivity(),PhanAnComBoActivity.class);
                            intent1.putExtra("ID_ThucDon",mangDanhSachThucDon.get(position).getID_ThucDon());
                            startActivity(intent1);
                        }
                        else {
                            CheckConnection.ShowToas_Short(getActivity(),"Ban Hãy Kiểm Tra Lại Kết Nối");
                        }
                        break;
                    case 1:
                        if(CheckConnection.haveNetworkConnection(getActivity())){
                            Intent intent1= new Intent(getActivity(),GaRanVaQuayActivity.class);
                            intent1.putExtra("ID_ThucDon",mangDanhSachThucDon.get(position).getID_ThucDon());
                            startActivity(intent1);
                        }
                        else {
                            CheckConnection.ShowToas_Short(getActivity(),"Ban Hãy Kiểm Tra Lại Kết Nối");
                        }
                        break;
                        case 2:
                        if(CheckConnection.haveNetworkConnection(getActivity())){
                            Intent intent= new Intent(getActivity(),PhanAnComBoActivity.class);
                            intent.putExtra("ID_ThucDon",mangDanhSachThucDon.get(position).getID_ThucDon());
                            startActivity(intent);
                        }
                        else {
                            CheckConnection.ShowToas_Short(getActivity(),"Ban Hãy Kiểm Tra Lại Kết Nối");
                        }
                        break;
                    case 3:
                        if(CheckConnection.haveNetworkConnection(getActivity())){
                            Intent intent3= new Intent(getActivity(),ThucAnNheActivity.class);
                            intent3.putExtra("ID_ThucDon",mangDanhSachThucDon.get(position).getID_ThucDon());
                            startActivity(intent3);
                        }
                        else {
                            CheckConnection.ShowToas_Short(getActivity(),"Ban Hãy Kiểm Tra Lại Kết Nối");
                        }
                        break;
                    case 4:
                        if(CheckConnection.haveNetworkConnection(getActivity())){
                            Intent intent4= new Intent(getActivity(),TrangMiengVaThucUongActivity.class);
                            intent4.putExtra("ID_ThucDon",mangDanhSachThucDon.get(position).getID_ThucDon());
                            startActivity(intent4);
                        }
                        else {
                            CheckConnection.ShowToas_Short(getActivity(),"Ban Hãy Kiểm Tra Lại Kết Nối");
                        }
                        break;

                }
            }
        });
    }
    private void getDuLieuDanhSachThucDon() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdandanhsachthucdon, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null){
                    for (int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID_ThucDon =jsonObject.getInt("ID_ThucDon");
                            Ten_Thuc_Don =jsonObject.getString("Ten_Thuc_Don");
                            HinhAnh_ThucDon =jsonObject.getString("HinhAnh_ThucDon");
                            mangDanhSachThucDon.add(new DanhSachThucDon(ID_ThucDon,Ten_Thuc_Don,HinhAnh_ThucDon));
                            danhSachThucDonAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToas_Short(getActivity(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://jollibee.com.vn/images/home/banner200x1000px.jpg");
        mangquangcao.add("https://jollibee.com.vn/images/home/banner200x1000px_new.jpg");
        mangquangcao.add("https://popeyes.vn/media/wysiwyg/menu_image_690x518_2.jpg");
        mangquangcao.add("https://popeyes.vn/media/wysiwyg/popeyes/category_spicy_1015x270.jpg");
        mangquangcao.add("https://popeyes.vn/media/wysiwyg/popeyes/category_beverages_1015x270.jpg");
        mangquangcao.add("https://popeyes.vn/media/wysiwyg/banner_ricemeal_v.jpg");
        mangquangcao.add("https://popeyes.vn/media/wysiwyg/popeyes/category_burger_1015x270.jpg");
        for(int i=0;i<mangquangcao.size();i++){
            ImageView imageView = new ImageView(getActivity());
            Picasso.with(getActivity()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }
}
