package com.example.btl_ordering_food_app_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Layout_signup extends AppCompatActivity {

    Button btn_backsignin;
    void Connect_ID()
    {
        btn_backsignin=findViewById(R.id.btn_backsignin);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_signup);

        Connect_ID();
        Intent intent_backsignin=new Intent(this,layout_login.class);
        btn_backsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_backsignin);
            }
        });
    }
}