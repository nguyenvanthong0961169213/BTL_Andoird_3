package com.example.btl_ordering_food_app_2.Fragment.tab_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_ordering_food_app_2.Fragment.Adapter.order_adapter;
import com.example.btl_ordering_food_app_2.Model.Order;
import com.example.btl_ordering_food_app_2.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_cart extends Fragment {
    private RecyclerView rcvOrder;
    private order_adapter OrderAdapter;
    List<Order> lstContent;
    void Connect_ID(View view)
    {
       // txt_username=view.findViewById(R.id.txt_test);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_cart,container,false);
        Connect_ID(view);
        rcvOrder = view.findViewById(R.id.rcvCart);
        OrderAdapter = new order_adapter(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        rcvOrder.setLayoutManager(gridLayoutManager);

        lstContent= new ArrayList<>();
        lstContent.add(new Order(1002,2,4000,R.drawable.sun,"Ca"));
        lstContent.add(new Order(1003,2,4000,R.drawable.sun,"Ca"));

        OrderAdapter.setData(lstContent);
        rcvOrder.setAdapter(OrderAdapter);
        return view;
    }
}
