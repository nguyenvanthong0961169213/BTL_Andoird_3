package com.example.btl_ordering_food_app_2.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.btl_ordering_food_app_2.Layout_main;
import com.example.btl_ordering_food_app_2.R;

public class Fragment_update_password extends Fragment {

    Button btn_Cancel_Update_Password;
    void Connect_ID(View view)
    {
        btn_Cancel_Update_Password=view.findViewById(R.id.btn_update_cancle_password);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_update_password,container,false);
        Connect_ID(view);
        Intent intent_Cancel=new Intent(getActivity(), Layout_main.class);
        btn_Cancel_Update_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_Cancel);
            }
        });
        return view;
    }
}
