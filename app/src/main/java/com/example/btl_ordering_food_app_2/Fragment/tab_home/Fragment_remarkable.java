package com.example.btl_ordering_food_app_2.Fragment.tab_home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_ordering_food_app_2.FoodDescriptionActivity;
import com.example.btl_ordering_food_app_2.Fragment.Adapter.food_adapter;
import com.example.btl_ordering_food_app_2.Fragment.Adapter.food_adapter_remarkable;
import com.example.btl_ordering_food_app_2.Model.Food;
import com.example.btl_ordering_food_app_2.Model.category_obj;
import com.example.btl_ordering_food_app_2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fragment_remarkable extends Fragment {
    private RecyclerView rcvTypeFood;
    public static food_adapter_remarkable food_adapter;
    List<Food> lstContent1;
    ViewFlipper vfQuangCao;
    public EditText txtFilter;
    public ISendDataListener Isenddata;

    public interface ISendDataListener{
        void SendData(Food food);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Isenddata = (Fragment_remarkable.ISendDataListener) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_remarkable,container,false);
        vfQuangCao = view.findViewById(R.id.vfQuangCao);
        txtFilter = view.findViewById(R.id.filterEditText);
        rcvTypeFood = view.findViewById(R.id.rcvTypeFoodRemarkable);


        int Image[] = {R.drawable.qc1 , R.drawable.qc2 , R.drawable.qc3};
        for (int item : Image) {
            SetImageFlip(item);
        }
        update_data_doan();
        food_adapter = new food_adapter_remarkable(lstContent1,this);
        rcvTypeFood.setAdapter(food_adapter);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(),1);
        rcvTypeFood.setLayoutManager(gridLayoutManager1);
        txtFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                food_adapter.getFilter().filter(charSequence);
                food_adapter.notifyDataSetChanged();
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }
    private void SetImageFlip(int image)
    {
        ImageView imgView = new ImageView(getActivity());
        imgView.setImageResource(image);
        imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        vfQuangCao.addView(imgView);
    }

    public void GetData(Food food){
        Intent intent = new Intent(getActivity(), FoodDescriptionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("food", (Serializable) food);

        intent.putExtras(bundle);
        startActivityForResult(intent,100);
    }
    void update_data(String category)
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Food").child(category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    lstContent1.add(snap.getValue(Food.class));
                }
                food_adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    void update_data_doan()
    {
        lstContent1 = new ArrayList<>();
        String[] arr = {"DoAn","DoAnNhanh","DoAnVat","DoUong"};
        for(String i:arr)
        {
            update_data(i);
        }

    }

}
