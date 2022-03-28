package com.example.btl_ordering_food_app_2.Fragment.tab_home;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl_ordering_food_app_2.Fragment.Adapter.category_adapter;
import com.example.btl_ordering_food_app_2.Fragment.Adapter.food_adapter;
import com.example.btl_ordering_food_app_2.Model.Food;
import com.example.btl_ordering_food_app_2.Model.category_obj;
import com.example.btl_ordering_food_app_2.R;
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

      //   rcvCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        lstContent= new ArrayList<>();
        lstContent1 = new ArrayList<>();
        lstContent.add(new category_obj(1,"Đồ ăn",R.drawable.icon_home));
        lstContent.add(new category_obj(2,"Đồ uống",R.drawable.icon_home));
        lstContent.add(new category_obj(3,"Kem",R.drawable.icon_home));
        lstContent.add(new category_obj(4,"Đồ chiên",R.drawable.icon_home));

        category_adapter.setData(lstContent);
        rcvCategory.setAdapter(category_adapter);


        lstContent1.add(new Food(1002,2000,R.drawable.sun,"Ca"));
        lstContent1.add(new Food(1003,20000,R.drawable.sun,"Ca"));
        lstContent1.add(new Food(1002,2000,R.drawable.sun,"Ca"));
        lstContent1.add(new Food(1003,20000,R.drawable.sun,"Ca"));
        lstContent1.add(new Food(1003,20000,R.drawable.sun,"Ca"));
        lstContent1.add(new Food(1003,20000,R.drawable.sun,"Ca"));
        food_adapter.setData(lstContent1);
        rcvTypeFood.setAdapter(food_adapter);

        return  view;

    }

}
