package com.example.btl_ordering_food_app_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Layout_header_info_user extends Fragment {
    TextView txt_name,txt_username;
    void Connect_ID(View view)
    {
        txt_name=view.findViewById(R.id.txt_Name_drawer);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_header_info_user,container,false);
        Connect_ID(view);
        txt_name.setText("haha");
        return view;
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_cart);
//        Connect_ID();
//        txt_username.setText("hí");
//    }

}
