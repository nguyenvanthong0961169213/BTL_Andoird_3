package com.example.btl_ordering_food_app_2.Fragment.Adapter;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_ordering_food_app_2.Model.Food;
import com.example.btl_ordering_food_app_2.Fragment.tab_home.Fragment_home;
import com.example.btl_ordering_food_app_2.Fragment.tab_home.Fragment_remarkable;
import com.example.btl_ordering_food_app_2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class food_adapter_remarkable extends RecyclerView.Adapter<food_adapter_remarkable.UserViewHolder> implements Filterable {
    private List<Food> data,old_data;
    private Fragment_remarkable mContext;


    //private Fragment_home.ISendDataListener Isenddata;

    public  food_adapter_remarkable(List<Food> data,Fragment_remarkable mContext){
        this.data = data;
        this.old_data = data;
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
        holder.btn_themSPCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.Isenddata.SendData(user);
                Toast.makeText(mContext.getActivity(), "dmdm", Toast.LENGTH_SHORT).show();

            }
        });
        holder.CVFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetDataDescrtptionFood(user);

            }
        });
    }

    public void SetDataDescrtptionFood(Food food){
        mContext.GetData(food);
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
        Filter f = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults fr = new FilterResults();
                //Backup dữ liệu: Lưu tạm dữ liệu vào backup
                if(old_data == null){
                    old_data = new ArrayList<>(data);
                }
//                Nếu chuỗi filter là rỗng thì khôi phục dữ liệu;
                if(charSequence == null || charSequence.length() == 0){
                    fr.count = old_data.size();
                    fr.values = old_data;
                }
//                Còn nếu không thì thực hiện filter
                else {
                    ArrayList<Food> newdata = new ArrayList<>();
                    for(Food s : old_data){
                        if(s.getTenSP().toLowerCase().contains(charSequence.toString().toLowerCase()))newdata.add(s);
                    }
                    fr.count = newdata.size();
                    fr.values = newdata;
                }
                return fr;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                data = new ArrayList<>();
                ArrayList<Food> tmp = (ArrayList<Food>)filterResults.values;

                for(Food s : tmp) {
                    data.add(s);
                }
                notifyDataSetChanged();
            }
        };
        return f;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView civImgFood;
        private TextView txtNameFood,txtGiaTienFood;
        Button btn_themSPCart;
        CardView CVFood;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            civImgFood = itemView.findViewById(R.id.civ_ImgFood);
            txtNameFood =  itemView.findViewById(R.id.txtNameFood);
            txtGiaTienFood = itemView.findViewById(R.id.txtGiaBanFood);
            btn_themSPCart = itemView.findViewById(R.id.btn_themsp_cart);
            CVFood = itemView.findViewById(R.id.CV_food);
        }
    }
}
