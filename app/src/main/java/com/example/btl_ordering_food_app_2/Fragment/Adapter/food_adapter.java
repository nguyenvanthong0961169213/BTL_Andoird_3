package com.example.btl_ordering_food_app_2.Fragment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl_ordering_food_app_2.Model.Food;
import com.example.btl_ordering_food_app_2.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class food_adapter extends RecyclerView.Adapter<food_adapter.UserViewHolder> {
    private List<Food> data;
    private Fragment mContext;

    public  food_adapter(Fragment mContext){
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
    }

    @Override
    public int getItemCount() {
        if(data != null){
            return data.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView civImgFood;
        private TextView txtNameFood,txtGiaTienFood;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            civImgFood = itemView.findViewById(R.id.civ_ImgFood);
            txtNameFood =  itemView.findViewById(R.id.txtNameFood);
            txtGiaTienFood = itemView.findViewById(R.id.txtGiaBanFood);
        }
    }
}

