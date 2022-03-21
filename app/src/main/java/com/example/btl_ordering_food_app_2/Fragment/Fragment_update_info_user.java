package com.example.btl_ordering_food_app_2.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.ConversationAction;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.btl_ordering_food_app_2.Fragment.tab_home.Fragment_home;
import com.example.btl_ordering_food_app_2.Layout_main;
import com.example.btl_ordering_food_app_2.MainActivity;
import com.example.btl_ordering_food_app_2.R;

public class Fragment_update_info_user extends Fragment {

    Button btn_Cancel;

    void Connect_ID(View view)
    {
        btn_Cancel=view.findViewById(R.id.btn_update_cancle);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_update_info_user,container,false);
        Connect_ID(view);
        Intent intent_cancel = new Intent(getActivity(),Layout_main.class);
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Context viewContext=(Context) view.getContext();
                //Intent intent_cancel = new Intent(viewContext,Fragment_home_app.class);
                startActivity(intent_cancel);
            }
        });
        return view;
    }
}
