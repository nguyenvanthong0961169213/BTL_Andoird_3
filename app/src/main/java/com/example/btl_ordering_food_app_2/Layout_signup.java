package com.example.btl_ordering_food_app_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.btl_ordering_food_app_2.Model.user_obj;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Layout_signup extends AppCompatActivity {

    EditText edt_PhoneNumber_Signup,edt_Address_Signup,edt_Password_SignUp,edt_RepeatPassword_SignUp;
    Button btn_backsignin,btn_signup;
    RadioButton rdo_Male_Signup,rdo_Female_Signup;
    List<user_obj> arrUser_1;
    void Connect_ID()
    {
        edt_PhoneNumber_Signup=findViewById(R.id.edt_PhoneNumber_SignUp);
        edt_Address_Signup=findViewById(R.id.edt_Address_Signup);
        edt_Password_SignUp=findViewById(R.id.edt_Password_SignUp);
        edt_RepeatPassword_SignUp=findViewById(R.id.edt_RepeatPassword_SignUp);
        btn_backsignin=findViewById(R.id.btn_backsignin);
        btn_signup=findViewById(R.id.btn_signup);
        rdo_Male_Signup=findViewById(R.id.rdo_Male_Signup);
        rdo_Female_Signup=findViewById(R.id.rdo_Female_Signup);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_signup);
        Connect_ID();
        rdo_Male_Signup.setChecked(true);
        Intent intent_backsignin=new Intent(this,layout_login.class);
        btn_backsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_backsignin);
            }
        });
     //   btn_signup.setOnClickListener(new View.OnClickListener() {

  //          @Override
//            public void onClick(View view) {
//                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//                DatabaseReference databaseReference = firebaseDatabase.getReference();
//                databaseReference.child("User").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        //snapshot.getChildren()
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//            }
      //  });
    }
    void Load_data_login()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    arrUser_1.add(snap.getValue(user_obj.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}