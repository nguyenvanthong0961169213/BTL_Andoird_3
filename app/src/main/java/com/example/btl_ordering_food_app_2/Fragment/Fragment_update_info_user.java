package com.example.btl_ordering_food_app_2.Fragment;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
public class Fragment_update_info_user extends Fragment {

    Button btn_Cancel,btn_update_confirm;
    CircleImageView circleImageView;
    ImageView imagephoto;
    ImageView imagecamera;
    public Uri imageuri;
    TextView txt_name,txt_phone_number_update;
    EditText edt_Name_update,edt_PhoneNumber_update,edt_address_update;
    RadioButton rdo_male,rdo_female;
    public String avatar_user;

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
        btn_update_confirm=view.findViewById(R.id.btn_update_confirm);

    }
    void update_data_user()
    {
        user_obj user = (user_obj) this.getArguments().getSerializable("user_obj_data_update");
          txt_name.setText(user.getName());
          txt_phone_number_update.setText(user.getPhonenumber());
          edt_Name_update.setText(user.getName());
          edt_PhoneNumber_update.setText(user.getPhonenumber());
          edt_address_update.setText(user.getAddress());
          Picasso.get().load(user.getAvatar()).into(circleImageView);
          avatar_user=user.getAvatar();
          if (user.isSex()) {
                rdo_male.setChecked(true);
          }else {
                rdo_female.setChecked(true);
          }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_update_info_user,container,false);
        Connect_ID(view);

        update_data_user();
        Intent intent_cancel = new Intent(getContext(),Layout_main.class);
        user_obj info_user = (user_obj) this.getArguments().getSerializable("user_obj_data_update");
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                bundle.putSerializable("user_obj_data",(Serializable) info_user);;
                //Đăt bunler lên intent
                intent_cancel.putExtras(bundle);
                startActivity(intent_cancel);
            }
        });

        btn_update_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //update dữ liệu
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                databaseReference.child("User").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            user_obj user=(snap.getValue(user_obj.class));
                            if(user.getId().equals(info_user.getId()))
                            {
                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                DatabaseReference databaseReference_update = firebaseDatabase.getReference();
                                user_obj user_update=new user_obj(info_user.getId(),edt_Name_update.getText().toString().trim(),
                                        edt_PhoneNumber_update.getText().toString().trim(),edt_address_update.getText().toString().trim(),
                                        (rdo_male.isChecked())?true:false,info_user.getUserpassword(),avatar_user);
                                databaseReference_update.child("User").child(snap.getKey()).setValue(user_update, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        Toast.makeText(getContext(),"Cập nhật thành công",Toast.LENGTH_LONG).show();
                                        Bundle bundle=new Bundle();
                                        bundle.putSerializable("user_obj_data",(Serializable) user_update);
                                        //Đăt bunler lên intent
                                        intent_cancel.putExtras(bundle);
                                        startActivity(intent_cancel);
                                    }

                                });
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

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
                //upload_image(view);

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
        if(requestCode==124 && resultCode== RESULT_OK && data!=null)
        {
            assert data!=null;
            imageuri=data.getData();
            circleImageView.setImageURI(imageuri);
            //Picasso.get().load(imageuri).into(circleImageView);
        }
    }
    private void upload_image(View view)
    {
        final ProgressDialog pd =new ProgressDialog(getActivity());
        pd.setTitle("Uploading Image...");
        pd.show();
        FirebaseStorage storage= FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        final String randomkey= UUID.randomUUID().toString();
       // String timestamp = "" + System.currentTimeMillis();
        StorageReference riversRef = storageReference.child("images/" + randomkey);

// Register observers to listen for when the download is done or if it fails
        riversRef.putFile(imageuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> avatar = taskSnapshot.getStorage().getDownloadUrl();
                        while (!avatar.isCanceled()){
                            // Uri dowload = avatar.getResult();
                            //avatar_user=dowload.toString();
                            //avatar_user=avatar.getResult().toString();
                        }
                        pd.dismiss();
                        Snackbar.make(view.findViewById(android.R.id.content),"Image Uploaded",Snackbar.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getActivity().getApplicationContext(),"failed to uploaded",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                          double ProgressPercent=(100.00 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                          pd.setMessage("Percentage: "+(int) ProgressPercent +"%");
                    }
                });
    }
}