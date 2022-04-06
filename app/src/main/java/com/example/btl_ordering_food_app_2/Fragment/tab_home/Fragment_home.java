package com.example.btl_ordering_food_app_2.Fragment.tab_home;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl_ordering_food_app_2.Fragment.Adapter.category_adapter;
import com.example.btl_ordering_food_app_2.Fragment.Adapter.food_adapter;
import com.example.btl_ordering_food_app_2.MainActivity;
import com.example.btl_ordering_food_app_2.Model.Food;
import com.example.btl_ordering_food_app_2.Model.category_obj;
import com.example.btl_ordering_food_app_2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Fragment_home extends Fragment {
    private RecyclerView rcvCategory,rcvTypeFood;
    private category_adapter category_adapter;
    private com.example.btl_ordering_food_app_2.Fragment.Adapter.food_adapter food_adapter;

    List<category_obj> lstContent ;
    List<Food> lstContent1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);

        rcvCategory = view.findViewById(R.id.rcvCategory);
        rcvTypeFood = view.findViewById(R.id.rcvTypeFood);

        food_adapter = new food_adapter(this);
        category_adapter = new category_adapter(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),4);
        rcvCategory.setLayoutManager(gridLayoutManager);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(),1);
        rcvTypeFood.setLayoutManager(gridLayoutManager1);

        lstContent= new ArrayList<>();
        lstContent.add(new category_obj(1,"Đồ Ăn",R.drawable.icon_home));
        lstContent.add(new category_obj(2,"Đồ Uống",R.drawable.icon_home));
        lstContent.add(new category_obj(3,"Ăn Vặt",R.drawable.icon_home));
        lstContent.add(new category_obj(4,"Ăn Nhanh",R.drawable.icon_home));

        category_adapter.setData(lstContent);
        rcvCategory.setAdapter(category_adapter);

        food_adapter.setData(lstContent1);
        rcvTypeFood.setAdapter(food_adapter);
        return view;

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        lstContent1=new ArrayList<>();
        update_data_doan();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
        lstContent1=new ArrayList<>();
        update_data_doan();
    }

    public Fragment_home() {
        super();
        lstContent1=new ArrayList<>();
        update_data_doan();
    }

    public static final String TAG= MainActivity.class.getSimpleName();
   void update_data_doan()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Food/DoAn").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                   lstContent1.add(snap.getValue(Food.class));
                }
                Log.e(TAG,"size_2"+lstContent1.get(0).getTenSP());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}
