package com.example.btl_ordering_food_app_2.Fragment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_ordering_food_app_2.Fragment.tab_home.Fragment_cart;
import com.example.btl_ordering_food_app_2.Model.buyItemUser;
import com.example.btl_ordering_food_app_2.R;

import org.w3c.dom.Text;

import java.util.List;

public class buyItemAdapter extends RecyclerView.Adapter<buyItemAdapter.buyItemViewHolder>{
    private Fragment_cart mContext;
    private List<buyItemUser> mListbuyItemUser;

    public buyItemAdapter(Fragment_cart mContext) {
        this.mContext = mContext;
    }

    public void setData(List<buyItemUser> list){
        this.mListbuyItemUser = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public buyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_item, parent, false);
        return new buyItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull buyItemViewHolder holder, int position) {
        buyItemUser buyitemUser = mListbuyItemUser.get(position);
        if (buyitemUser == null){
            return;
        }

        holder.MaHD.setText(buyitemUser.getMaHD());
        holder.TenSP.setText(buyitemUser.getTenSP());
        holder.GiaTien.setText(buyitemUser.getGiaTien());

    }

    @Override
    public int getItemCount() {
        if(mListbuyItemUser != null){
            return mListbuyItemUser.size();
        }
        return 0;
    }

    public class buyItemViewHolder extends RecyclerView.ViewHolder{

        private TextView MaHD;
        private TextView TenSP;
        private TextView GiaTien;

        public buyItemViewHolder(@NonNull View itemView) {
            super(itemView);
            MaHD = itemView.findViewById(R.id.MaHD);
            TenSP = itemView.findViewById(R.id.TenSP);
            GiaTien = itemView.findViewById(R.id.GiaTien);

        }
    }
}
