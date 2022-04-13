package com.example.btl_ordering_food_app_2.Fragment.tab_home;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_ordering_food_app_2.FoodDescriptionActivity;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fragment_home extends Fragment {
    private RecyclerView rcvCategory,rcvTypeFood;
    private category_adapter category_adapter_data;
    private com.example.btl_ordering_food_app_2.Fragment.Adapter.food_adapter food_adapter;
    public static category_obj Selected_category;
    SearchView searchView;
    List<category_obj> lstContent ;
    List<Food> lst_food;

    //Khởi tạo đối tượng ịnterface
   // int Selected_ID=-1;
    public ISendDataListener Isenddata;

    public interface ISendDataListener{
        void SendData(Food food);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Isenddata = (ISendDataListener) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);

        rcvCategory = view.findViewById(R.id.rcvCategory);
        rcvTypeFood = view.findViewById(R.id.rcvTypeFood);


        food_adapter = new food_adapter(this);
        category_adapter_data = new category_adapter(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),4);
        rcvCategory.setLayoutManager(gridLayoutManager);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(),1);
        rcvTypeFood.setLayoutManager(gridLayoutManager1);

        lstContent= new ArrayList<>();
        lstContent.add(new category_obj(1,"Đồ Ăn",R.drawable.icon_com));
        lstContent.add(new category_obj(2,"Đồ Uống",R.drawable.icon_douong));
        lstContent.add(new category_obj(3,"Ăn Vặt",R.drawable.icon_doanvat));
        lstContent.add(new category_obj(4,"Ăn Nhanh",R.drawable.icon_doannhanh));

        category_adapter_data.setData(lstContent);
        rcvCategory.setAdapter(category_adapter_data);
       rcvCategory.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
          }
      });
        //lstContent.

        lst_food=new ArrayList<>();
        food_adapter.setData(lst_food);
        rcvTypeFood.setAdapter(food_adapter);
        update_data_doan("DoAn");

        return view;


    }
    public static final String TAG= MainActivity.class.getSimpleName();
    public void back_backgound_category()
    {
        lstContent= new ArrayList<>();
        lstContent.add(new category_obj(1,"Đồ Ăn",R.drawable.icon_com));
        lstContent.add(new category_obj(2,"Đồ Uống",R.drawable.icon_douong));
        lstContent.add(new category_obj(3,"Ăn Vặt",R.drawable.icon_doanvat));
        lstContent.add(new category_obj(4,"Ăn Nhanh",R.drawable.icon_doannhanh));

       // category_adapter
    }
   void update_data_doan(String category)
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Food").child(category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                   lst_food.add(snap.getValue(Food.class));
                }
                food_adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    //Filter của khánh
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_searchview, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // food_adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // food_adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
    public void ID_Click(category_obj category_obj)
    {
        if(category_obj.getID()==1)
        {
            lst_food=new ArrayList<>();
            food_adapter.setData(lst_food);
            update_data_doan("DoAn");
        }
        else if(category_obj.getID()==2)
        {
            lst_food=new ArrayList<>();
            food_adapter.setData(lst_food);
            update_data_doan("DoUong");
        }
        else if(category_obj.getID()==3)
        {
            lst_food=new ArrayList<>();
            food_adapter.setData(lst_food);
            update_data_doan("DoAnVat");
        }
        else if(category_obj.getID()==4)
        {
            lst_food=new ArrayList<>();
            food_adapter.setData(lst_food);
            update_data_doan("DoAnNhanh");
        }

    }
    public void GetData(Food food){
        Intent intent = new Intent(getActivity(), FoodDescriptionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("food", (Serializable) food);

        intent.putExtras(bundle);
        startActivityForResult(intent,100);
    }

}
