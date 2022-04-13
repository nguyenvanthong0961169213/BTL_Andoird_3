package com.example.btl_ordering_food_app_2.Fragment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl_ordering_food_app_2.Model.Food;
import com.example.btl_ordering_food_app_2.Model.Invoice;
import com.example.btl_ordering_food_app_2.Fragment.tab_home.Fragment_cart;
import com.example.btl_ordering_food_app_2.Fragment.tab_home.Fragment_remarkable;
import com.example.btl_ordering_food_app_2.R;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;
public class invoice_adapter  extends RecyclerView.Adapter<invoice_adapter.UserViewHolder> {
    private List<Invoice> data;
    private Fragment_cart mContext;

    public invoice_adapter(List<Invoice> data, Fragment_cart mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @NonNull
    @Override

    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_invoice,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Invoice user = data.get(position);
        if(user == null)
        {
            return;
        }

        holder.txtGiaTienHD.setText(String.valueOf(user.getGiaTien()));
        holder.txtMAHD.setText(user.getMaHD());

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
