package com.example.btl_ordering_food_app_2.Fragment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_ordering_food_app_2.HDactivity;
import com.example.btl_ordering_food_app_2.Model.Invoice;
import com.example.btl_ordering_food_app_2.Model.Invoice_detail;
import com.example.btl_ordering_food_app_2.R;
import com.example.btl_ordering_food_app_2.activity_cthd_invoice;

import java.util.List;

public class invoice_detail_adapter extends RecyclerView.Adapter<invoice_detail_adapter.UserViewHolder>{
    private List<Invoice_detail> data;
    private activity_cthd_invoice mContext;

    public invoice_detail_adapter(List<Invoice_detail> data, activity_cthd_invoice mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_invoice,parent,false);
        return new invoice_detail_adapter.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Invoice_detail user = data.get(position);
        if(user == null)
        {
            return;
        }
        holder.txtGiaTienHD.setText(user.getMaSP());
        holder.txtMAHD.setText(String.valueOf(user.getSoLuong()));
    }

    @Override
    public int getItemCount() {
        if(data != null){
            return data.size();
        }
        return 0;
    }


    public class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView txtMAHD,txtGiaTienHD;

        private CardView CVHoaDon;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMAHD =  itemView.findViewById(R.id.txtMaHD);
            txtGiaTienHD = itemView.findViewById(R.id.txtGiaTienHD);
            CVHoaDon = itemView.findViewById(R.id.CVHoaDon);
        }
    }
}
