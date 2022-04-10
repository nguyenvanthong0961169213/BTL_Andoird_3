package com.example.btl_ordering_food_app_2.Fragment.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_ordering_food_app_2.Fragment.tab_home.Fragment_home;
import com.example.btl_ordering_food_app_2.Layout_main;
import com.example.btl_ordering_food_app_2.Model.Food;
import com.example.btl_ordering_food_app_2.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class food_adapter extends RecyclerView.Adapter<food_adapter.UserViewHolder>
        implements Filterable {
    private List<Food> data;
    private Fragment_home mContext;
    private Activity activity;
    private List<Food> old_data;
    //private Fragment_home.ISendDataListener Isenddata;
    public  food_adapter(Fragment_home mContext){
        this.mContext = mContext;
    }
    public  void setData(List<Food> list){
        this.data = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.avtivity_food,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Food user = data.get(position);
        if(user == null)
        {
            return;
        }

       // holder.civImgFood.setImageResource(user.getAnh());
        Picasso.get().load(user.getAnh()).into(holder.civImgFood);
        holder.txtNameFood.setText(user.getTenSP());
        holder.txtGiaTienFood.setText(String.valueOf(user.getGiaTien()));
        holder.btn_addfood_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),user.getTenSP()+" Đã Được Thêm Vào Giỏ Hàng.",Toast.LENGTH_LONG).show();
                mContext.Isenddata.SendData(user);
            }
        });
        holder.CVFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetDataDescrtptionFood(user);

            }
        });

    }
    private void SetDataDescrtptionFood(Food user) {
        mContext.GetData(user);
    }

    @Override
    public int getItemCount() {
        if(data != null){
            return data.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchString = constraint.toString();
                if (searchString.isEmpty()) {
                    data = old_data;
                } else {
                    List<Food> foodList = new ArrayList<>();
                    for (Food food : old_data) {
                        if (food.getTenSP().toLowerCase().contains(searchString.toLowerCase())) {
                            foodList.add(food);
                        }
                    }
                    data = foodList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = data;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                data = (List<Food>) filterResults.values;
            }
        };
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView civImgFood;
        private TextView txtNameFood,txtGiaTienFood;
        private Button btn_addfood_cart;
        private CardView CVFood;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            civImgFood = itemView.findViewById(R.id.civ_ImgFood);
            txtNameFood =  itemView.findViewById(R.id.txtNameFood);
            txtGiaTienFood = itemView.findViewById(R.id.txtGiaBanFood);
            btn_addfood_cart=itemView.findViewById(R.id.btn_themsp_cart);
            CVFood=itemView.findViewById(R.id.CV_food);
        }
    }
}

