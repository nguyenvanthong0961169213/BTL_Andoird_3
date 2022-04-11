package com.example.btl_ordering_food_app_2.Fragment.tab_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_ordering_food_app_2.Fragment.Adapter.buyItemAdapter;
import com.example.btl_ordering_food_app_2.Fragment.Adapter.order_adapter;
import com.example.btl_ordering_food_app_2.Model.Food;
import com.example.btl_ordering_food_app_2.Model.Order;
import com.example.btl_ordering_food_app_2.Model.buyItemUser;
import com.example.btl_ordering_food_app_2.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_cart extends Fragment {
    private static RecyclerView rcvOrder,rcvbuyItem;
    private static order_adapter OrderAdapter;
    static List<Order> lstContent;
    public static int TongTien;
    static TextView txtTongTienOrder;
    Button btnMua;

    void Connect_ID(View view)
    {
       // txt_username=view.findViewById(R.id.txt_test);
        txtTongTienOrder=view.findViewById(R.id.txtTongTienOrder);
        btnMua = view.findViewById(R.id.btnMua);
        rcvbuyItem = view.findViewById(R.id.rcvbuyItem);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_cart,container,false);
        Connect_ID(view);

        //LapCode

      //  DangLap code:
//        buyItemAdapter buyitemAdapter = new buyItemAdapter(this);
//        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(),1);
//        rcvbuyItem.setLayoutManager(gridLayoutManager1);
//        buyitemAdapter.setData(getListbuyItem());
//        rcvbuyItem.setAdapter(buyitemAdapter);


        rcvOrder = view.findViewById(R.id.rcvCart);
        OrderAdapter = new order_adapter(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        rcvOrder.setLayoutManager(gridLayoutManager);

//        rcvOrder = view.findViewById(R.id.rcvCart);
//        OrderAdapter = new order_adapter(this);
//
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
//        rcvOrder.setLayoutManager(gridLayoutManager);

        lstContent= new ArrayList<>();
       // lstContent.add(new Order(1002,2,4000,R.drawable.sun,"Ca"));
       // lstContent.add(new Order(1003,2,4000,R.drawable.sun,"Ca"));

        OrderAdapter.setData(lstContent);
        rcvOrder.setAdapter(OrderAdapter);

        TongTien = 0;
        for(Order item : lstContent){
            TongTien+=item.getGiaTien()*item.getSoLuong();
        }
        txtTongTienOrder.setText(String.valueOf(TongTien));

        return view;
    }
    //hàm getListbuyItem
//    private List<buyItemUser> getListbuyItem(){
//        List<buyItemUser> list = new ArrayList<>();
//        //fix cứng nên add data, nếu lấy dữ liệu từ firebase thì xóa đoạn này đi...
//        list.add(new buyItemUser("HD001", "Cơm gà Sốt Đậu ",45000));
//        list.add(new buyItemUser("HD002", "Gà chiên giòn ",25000));
//
//        return list;
//    }
    public static void receiveDataFromFramentHome(Food food) {

        TongTien += food.getGiaTien();
        txtTongTienOrder.setText(String.valueOf(TongTien));

        boolean check = false;
        for (Order item: lstContent) {
            if (item.getMaSP() == food.getMaSP()){
                int dem = item.getSoLuong();
                dem++;
                item.setSoLuong(dem);OrderAdapter.notifyDataSetChanged();
                rcvOrder.setAdapter(OrderAdapter);
                check = true;
                break;
            }
        }
        if(check == false){
            lstContent.add(new Order(food.getMaSP(),1,food.getGiaTien(),food.getAnh(),food.getTenSP()));
            OrderAdapter.notifyDataSetChanged();
            rcvOrder.setAdapter(OrderAdapter);
        }
        check = false;
    }
    public static void CongTien(Order order){
        TongTien += order.getGiaTien();
        txtTongTienOrder.setText(String.valueOf(TongTien));
    }
    public static  void TruTien(Order order){
        TongTien -= order.getGiaTien();
        txtTongTienOrder.setText(String.valueOf(TongTien));
    }

}
