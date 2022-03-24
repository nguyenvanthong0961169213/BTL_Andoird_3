package com.example.btl_ordering_food_app_2;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Layout_header_info_user extends AppCompatActivity {
    TextView txt_name;
    void Connect_ID()
    {
       txt_name=findViewById(R.id.txt_Name_drawer);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_header_info_user);
        Connect_ID();
        //txt_name.setText("kaka");
        Intent intent_Cancel=new Intent(this, Layout_signup.class);
        startActivity(intent_Cancel);
    }

}
