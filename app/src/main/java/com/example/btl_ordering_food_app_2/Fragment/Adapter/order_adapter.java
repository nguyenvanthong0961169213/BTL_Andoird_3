package com.example.btl_ordering_food_app_2.Fragment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_ordering_food_app_2.Model.Order;
import com.example.btl_ordering_food_app_2.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class order_adapter extends RecyclerView.Adapter<order_adapter.UserViewHolder> {
    private List<Order> data;
    private Fragment mContext;

    public order_adapter(Fragment mContext){
        this.mContext = mContext;
    }
    public  void setData(List<Order> list){
        this.data = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_order,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Order user = data.get(position);
        if(user == null)
        {
            return;
        }

        holder.civImgOrder.setImageResource(user.getAnh());
        holder.txtName.setText(user.getTenSP());
        holder.txtSoLuong.setText(String.valueOf(user.getSoLuong()));
        holder.txtGiaTienOrder.setText(String.valueOf(user.getGiaTien()));
        holder.btnCongOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int SoLuongOrderCu = user.getSoLuong();
                user.setSoLuong(SoLuongOrderCu+1);
                holder.txtSoLuong.setText(String.valueOf(user.getSoLuong()));
            }
        });
        holder.btnTruOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int SoLuongOrderCu = user.getSoLuong();
                user.setSoLuong(SoLuongOrderCu-1);
                holder.txtSoLuong.setText(String.valueOf(user.getSoLuong()));
                if(user.getSoLuong()==0){
                    data.remove(user);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(data != null){
            return data.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView civImgOrder;
        private TextView txtName,txtSoLuong,txtGiaTienOrder;
        private Button btnCongOrder,btnTruOrder;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            civImgOrder = itemView.findViewById(R.id.civ_ImgOrder);
            txtName =  itemView.findViewById(R.id.txtNameOrder);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuongOrder);
            txtGiaTienOrder = itemView.findViewById(R.id.txtGiaTienOrder);
            btnCongOrder = itemView.findViewById(R.id.btnCongOrder);
            btnTruOrder = itemView.findViewById(R.id.btnTruOrder);
        }
    }
}
