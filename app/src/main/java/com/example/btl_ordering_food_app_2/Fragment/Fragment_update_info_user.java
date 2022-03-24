package com.example.btl_ordering_food_app_2.Fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.btl_ordering_food_app_2.Layout_main;
import com.example.btl_ordering_food_app_2.Layout_signup;
import com.example.btl_ordering_food_app_2.Model.user_obj;
import com.example.btl_ordering_food_app_2.R;

import java.io.Serializable;

import de.hdodenhof.circleimageview.CircleImageView;
public class Fragment_update_info_user extends Fragment {

    Button btn_Cancel;
    CircleImageView circleImageView;
    ImageView imagephoto;
    ImageView imagecamera;
    String uri =null;
    TextView txt_name,txt_phone_number_update;
    EditText edt_Name_update,edt_PhoneNumber_update,edt_address_update;
    RadioButton rdo_male,rdo_female;

    void Connect_ID(View view)
    {
        btn_Cancel=view.findViewById(R.id.btn_update_cancle);
        circleImageView=view.findViewById(R.id.image_user_update);
        imagephoto=view.findViewById(R.id.image_photo_update);
        imagecamera=view.findViewById(R.id.image_camera_update);
        txt_name=view.findViewById(R.id.txt_name_update);
        txt_phone_number_update=view.findViewById(R.id.txt_phone_number_update);
        edt_Name_update=view.findViewById(R.id.edt_Name_update);
        edt_PhoneNumber_update=view.findViewById(R.id.edt_PhoneNumber_update);
        edt_address_update=view.findViewById(R.id.edt_address_update);
        rdo_male=view.findViewById(R.id.rdo_male);
        rdo_female=view.findViewById(R.id.rdo_female);
    }
    void update_data_user()
    {
        user_obj user = (user_obj) this.getArguments().getSerializable("user_obj_data_update");
          txt_name.setText(user.getName());
          txt_phone_number_update.setText(user.getPhonenumber());
          edt_Name_update.setText(user.getName());
          edt_PhoneNumber_update.setText(user.getPhonenumber());
          edt_address_update.setText(user.getAddress());
       //   if(user.isSex()?);
        if (user.isSex()) {
            rdo_male.setChecked(true);
        } else {
            rdo_female.setChecked(true);
        }

//
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_update_info_user,container,false);
        Connect_ID(view);
        //Load_data();
        update_data_user();
        Intent intent_cancel = new Intent(getContext(),Layout_main.class);
        user_obj user_2 = (user_obj) this.getArguments().getSerializable("user_obj_data_update");
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();

                bundle.putSerializable("user_obj_data",(Serializable) user_2);;
//                                //Đăt bunler lên intent
                intent_cancel.putExtras(bundle);
                startActivity(intent_cancel);
            }
        });
        imagecamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_camera=new Intent();
                intent_camera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent_camera,123);

            }
        });
        imagephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"chọn ảnh"),124);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123 && resultCode== RESULT_OK && data!=null)
        {
            Bitmap bitmap=(Bitmap) data.getExtras().get("data");
            circleImageView.setImageBitmap(bitmap);
        }
//
        if(requestCode==124 && resultCode== RESULT_OK && data!=null)
        {
            assert data!=null;
            uri=data.getData().toString();
            circleImageView.setImageURI(data.getData());

        }

    }
//    void Load_data()
//    {
//        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference=firebaseDatabase.getReference();
//
//        databaseReference.child("User/0").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//               user_obj data_user_obj=new user_obj();
//               data_user_obj= snapshot.getValue(user_obj.class);
//               txt_name.setText(data_user_obj.getName());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}


