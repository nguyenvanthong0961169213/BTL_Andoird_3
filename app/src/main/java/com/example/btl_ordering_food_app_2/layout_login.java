package com.example.btl_ordering_food_app_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mahfa.dnswitch.DayNightSwitch;
import com.mahfa.dnswitch.DayNightSwitchListener;

public class layout_login extends AppCompatActivity {

    ImageView sun;
    View daySky,nightSky;
    DayNightSwitch dayNightSwitch;
    Button btn_Createaccount;

    void Connect_ID()
    {
        sun=findViewById(R.id.sun);
        daySky=findViewById(R.id.day_BackGround);
        nightSky=findViewById(R.id.night_BackGround);
        dayNightSwitch=findViewById(R.id.day_night_switch);
        btn_Createaccount=findViewById(R.id.btn_createaccount);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_login);

        Connect_ID();
        dayNightSwitch.setListener(new DayNightSwitchListener() {
            @Override
            public void onSwitch(boolean is_night) {
                if(is_night)
                {
                    sun.animate().translationY(30).setDuration(1000);
                    daySky.animate().alpha(0).setDuration(1300);
                }
                else
                {
                    sun.animate().translationY(-30).setDuration(1000);
                    daySky.animate().alpha(1).setDuration(1300);
                }
            }
        });
       // Intent intent_signup = new Intent(this,Layout_SignUp.class);
        btn_Createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          //      startActivity(intent_signup);
            }
        });
    }
}