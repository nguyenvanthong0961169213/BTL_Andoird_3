package com.example.btl_ordering_food_app_2.Fragment.tab_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_ordering_food_app_2.Fragment.Adapter.invoice_adapter;
import com.example.btl_ordering_food_app_2.Fragment.Adapter.order_adapter;
import com.example.btl_ordering_food_app_2.HDactivity;
import com.example.btl_ordering_food_app_2.Layout_main;
import com.example.btl_ordering_food_app_2.Model.Food;
import com.example.btl_ordering_food_app_2.Model.Invoice;
import com.example.btl_ordering_food_app_2.Model.Invoice_detail;
import com.example.btl_ordering_food_app_2.Model.Order;
import com.example.btl_ordering_food_app_2.Model.user_obj;
import com.example.btl_ordering_food_app_2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fragment_cart extends Fragment {
    private static RecyclerView rcvOrder,rcvHoaDon;
    private static order_adapter OrderAdapter;
    static List<Order> lstContent;
    public static int TongTien;
    static TextView txtTongTienOrder;
    static List<Invoice> lstContentHD;
    private invoice_adapter invoiceAdapter;
    Button btnMua,btnHD;

    void Connect_ID(View view)
    {
       // txt_username=view.findViewById(R.id.txt_test);
        txtTongTienOrder=view.findViewById(R.id.txtTongTienOrder);
        btnMua = view.findViewById(R.id.btnMua);
        btnHD = view.findViewById(R.id.btnHdb);
//        rcvHoaDon = view.findViewById(R.id.rcvHoaDon);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_cart,container,false);
        Connect_ID(view);
        Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
        rcvOrder = view.findViewById(R.id.rcvCart);
        OrderAdapter = new order_adapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        rcvOrder.setLayoutManager(gridLayoutManager);
        lstContent= new ArrayList<>();
        OrderAdapter.setData(lstContent);
        rcvOrder.setAdapter(OrderAdapter);
        TongTien = 0;
        for(Order item : lstContent){
            TongTien+=item.getGiaTien()*item.getSoLuong();
        }
        txtTongTienOrder.setText(String.valueOf(TongTien));
        load_data_invoice(Layout_main.MaKh);
        load_data_invoice_detail();
        btnHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HDactivity.class);
                startActivity(intent);

            }
        });


//        lstContentHD=new ArrayList<>();

//        load_data_invoice(Layout_main.MaKh);
//        GridLayoutManager gridLayoutManagerHD = new GridLayoutManager(getActivity(),1);
//        rcvHoaDon.setLayoutManager(gridLayoutManagerHD);
//
//        invoiceAdapter= new invoice_adapter(lstContentHD,this);
//        rcvHoaDon.setAdapter(invoiceAdapter);
//
        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_data_invoiced();
                update_data_detail_invoiced((Layout_main.MaKh+"_HDB_"+h));
                lstContent=new ArrayList<>();
                OrderAdapter.setData(lstContent);
                TongTien = 0;
                txtTongTienOrder.setText(String.valueOf(TongTien));
            }
        });
        return view;
    }
    public static void receiveDataFromFramentHome(Food food) {

        TongTien += food.getGiaTien();
        txtTongTienOrder.setText(String.valueOf(TongTien));

        boolean check = false;
        for (Order item: lstContent) {
            if (item.getMaSP() == food.getMaSP()){
                int dem = item.getSoLuong();
                dem++;
                item.setSoLuong(dem);OrderAdapter.notifyDataSetChanged();
                rcvOrder.setAdapter(OrderAdapter);
                check = true;
                break;
            }
        }
        if(check == false){
            lstContent.add(new Order(food.getMaSP(),1,food.getGiaTien(),food.getAnh(),food.getTenSP()));
            OrderAdapter.notifyDataSetChanged();
            rcvOrder.setAdapter(OrderAdapter);
        }
        check = false;
    }
    public static void CongTien(Order order){
        TongTien += order.getGiaTien();
        txtTongTienOrder.setText(String.valueOf(TongTien));
    }
    public static  void TruTien(Order order){
        TongTien -= order.getGiaTien();
        txtTongTienOrder.setText(String.valueOf(TongTien));
    }
    Invoice invoice,invoice_all;
    int dem;
    void load_data_invoice(String Id_user)
    {
        dem =0;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Invoice").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    dem++;
                    invoice_all=(snap.getValue(Invoice.class));
                    if(invoice_all.getMaKH().equals(Id_user))
                    {
                        invoice=invoice_all;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    int h ;
    void update_data_invoiced() {
        String MaHD=invoice.getMaHD();
        String k = CatChuoi(MaHD);
        h =Integer.parseInt(k)+1;
        Toast.makeText(getContext(),"Cập Nhật Thành Công !!!"+MaHD,Toast.LENGTH_LONG).show();
        int h=Integer.parseInt(k)+1;
        Invoice invoice=new Invoice((Layout_main.MaKh+"_HDB_"+h),Integer.parseInt(String.valueOf
                (txtTongTienOrder.getText())),Layout_main.MaKh);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Invoice").child(""+dem).setValue(invoice, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(getContext(),"Cập Nhật Thành Công !!!",Toast.LENGTH_LONG).show();

            }

        });
    }
    public String CatChuoi(String MaHD) {
        String[] splits = MaHD.split("_");
       return splits[2];
    }
    int dem_cthd;
    void load_data_invoice_detail()
    {
        dem_cthd=0;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Invoice_detail").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    dem_cthd++;
                }
                Toast.makeText(getContext(),"Đem_cthd"+dem_cthd,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    void update_data_detail_invoiced(String maHD)
    {
        for(Order item:lstContent)
        {
            Invoice_detail invoice_detail=new Invoice_detail(maHD,item.getSoLuong(),item.getMaSP());
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference();
            databaseReference.child("Invoice_detail").child(""+(dem_cthd+1)).setValue(invoice_detail, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    Toast.makeText(getContext(),"Cập Nhật Thành Công !!!",Toast.LENGTH_LONG).show();
                }

            });
        }
    }
}
