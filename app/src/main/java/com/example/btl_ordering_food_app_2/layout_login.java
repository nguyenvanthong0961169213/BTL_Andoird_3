package com.example.btl_ordering_food_app_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btl_ordering_food_app_2.Fragment.tab_home.Fragment_home;
import com.example.btl_ordering_food_app_2.Model.user_obj;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mahfa.dnswitch.DayNightSwitch;
import com.mahfa.dnswitch.DayNightSwitchListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class layout_login extends AppCompatActivity {

    final Context context = this;

    ImageView sun;
    View daySky, nightSky;
    DayNightSwitch dayNightSwitch;
    Button btn_Createaccount,btn_Signin;
    EditText edt_phonenumber, edt_password;
    List<user_obj> data_user=new ArrayList<>();

    void Connect_ID() {
        sun = findViewById(R.id.sun);
        daySky = findViewById(R.id.day_BackGround);
        nightSky = findViewById(R.id.night_BackGround);
        dayNightSwitch = findViewById(R.id.day_night_switch);
        btn_Createaccount = findViewById(R.id.btn_createaccount);
        edt_phonenumber = findViewById(R.id.edt_PhoneNumber);
        edt_password = findViewById(R.id.edt_Password);
        btn_Signin = findViewById(R.id.btn_SignIn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_login);
        Connect_ID();
        Load_data_login();
        dayNightSwitch.setListener(new DayNightSwitchListener() {
            @Override
            public void onSwitch(boolean is_night) {
                if (is_night) {
                    sun.animate().translationY(30).setDuration(1000);
                    daySky.animate().alpha(0).setDuration(1300);
                } else {
                    sun.animate().translationY(-30).setDuration(1000);
                    daySky.animate().alpha(1).setDuration(1300);
                }
            }
        });
        btn_Createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //      startActivity(intent_signup);
            }
        });
        btn_Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_login();
            }
        });

    }
    void check_login()
    {
        for (user_obj user:data_user)
        {
            if (edt_phonenumber.getText().toString().trim().equals(user.getPhonenumber())
                    && edt_password.getText().toString().trim().equals(user.getUserpassword())) {
                Intent intent=new Intent(context,Layout_main.class);
                //Tạo intent trở về MainActivity
                //Tạo Bundle để chứa dữ liệu
                Bundle bundle=new Bundle();
//                                 // Device device = new Device(Integer.parseInt(edt_ID.getText().toString()),uri, edt_DeviceName.getText().toString(),edt_DeviceWattage.getText().toString(),swtStatusAdd.isChecked());
                bundle.putSerializable("user_obj", (Serializable) user);;
//                                //Đăt bunler lên intent
//                                intent.putExtras(bundle);
                startActivity(intent);
                break;
            }
            else
            {
                Toast.makeText(context,user.getUserpassword(),Toast.LENGTH_LONG).show();
            }
        }
    }
    void Load_data_login()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    data_user.add(snap.getValue(user_obj.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}