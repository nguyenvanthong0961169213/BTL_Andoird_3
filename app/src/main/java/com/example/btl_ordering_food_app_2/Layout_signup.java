package com.example.btl_ordering_food_app_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.btl_ordering_food_app_2.Model.user_obj;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import okhttp3.internal.cache.DiskLruCache;

public class Layout_signup extends AppCompatActivity {
   // Activity
    EditText edt_PhoneNumber_Signup,edt_Address_Signup,edt_Password_SignUp,edt_RepeatPassword_SignUp,edt_Name_Signup;
    Button btn_backsignin,btn_signup;
    RadioButton rdo_Male_Signup,rdo_Female_Signup;
    List<user_obj> arrUser_1;
    void Connect_ID()
    {
        edt_PhoneNumber_Signup=findViewById(R.id.edt_PhoneNumber_SignUp);
        edt_Name_Signup=findViewById(R.id.edt_Name_Signup);
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
       btn_signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               user_obj user_Singup=new user_obj(edt_PhoneNumber_Signup.getText().toString(),edt_Name_Signup.getText().toString().trim(),
                       edt_PhoneNumber_Signup.getText().toString().trim(),edt_Address_Signup.getText().toString().trim(),
                       (rdo_Male_Signup.isChecked())?true:false,edt_Password_SignUp.getText().toString(),"https://upload.wikimedia.org/wikipedia/commons/a/ac/Default_pfp.jpg");
               if(Load_data_login(edt_PhoneNumber_Signup.getText().toString()))
               {
                   FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                   DatabaseReference databaseReference_Signup = firebaseDatabase.getReference();
                   databaseReference_Signup.child("User").child("1").setValue(user_Singup, new DatabaseReference.CompletionListener() {
                       @Override
                       public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                           Toast.makeText(getBaseContext(),"Cập nhật thành công",Toast.LENGTH_LONG).show();
                       }
                   });
               }
           }
       });
    }
    static boolean check_phone=true;
    boolean Load_data_login(String phonenumber)
    {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    user_obj user = (snap.getValue(user_obj.class));
                    if (user.getId() != phonenumber) {
                        arrUser_1.add(snap.getValue(user_obj.class));

                    } else {
                        check_phone=false;
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return check_phone;
    }
}