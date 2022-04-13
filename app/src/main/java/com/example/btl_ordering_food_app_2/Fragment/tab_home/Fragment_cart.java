package com.example.btl_ordering_food_app_2.Fragment.tab_home;

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
import com.example.btl_ordering_food_app_2.Layout_main;
import com.example.btl_ordering_food_app_2.Model.Food;
import com.example.btl_ordering_food_app_2.Model.Invoice;
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
    private List<Invoice> lstContent1;
    List<Invoice> lstContent_check_size;
    private invoice_adapter invoiceAdapter;
    Button btnMua;

    void Connect_ID(View view)
    {
       // txt_username=view.findViewById(R.id.txt_test);
        txtTongTienOrder=view.findViewById(R.id.txtTongTienOrder);
        btnMua = view.findViewById(R.id.btnMua);
        rcvHoaDon = view.findViewById(R.id.rcvHoaDon);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_cart,container,false);
        Connect_ID(view);

        Toast.makeText(getContext(),"hello",Toast.LENGTH_LONG).show();
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


        GridLayoutManager gridLayoutManagerHD = new GridLayoutManager(getActivity(),1);
        rcvHoaDon.setLayoutManager(gridLayoutManagerHD);
        invoiceAdapter= new invoice_adapter(lstContent1,this);
        rcvHoaDon.setAdapter(invoiceAdapter);
       // load_data_invoice(Layout_main.MaKh);
        update_data_invoiced();
        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_data_invoiced();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                //k=Integer.parseInt(lstContent_check_size.get(0).getMaKH());
                String res="HDB_"+(k);
                Invoice invoice=new Invoice(res,Integer.parseInt(txtTongTienOrder.getText().toString().trim()),Layout_main.MaKh);
               databaseReference.child("Invoice").child(""+(k)).setValue(invoice, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                   }
                });
                //update_data_invoice(Layout_main.MaKh);
               // load_data_invoice(Layout_main.MaKh);
                invoiceAdapter.notifyDataSetChanged();
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
    void load_data_invoice(String Id_user)
    {
        lstContent1 = new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Invoice").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Invoice invoice=(snap.getValue(Invoice.class));
                    if(invoice.getMaKH().equals(Id_user))
                    {
                        lstContent1.add(snap.getValue(Invoice.class));

                    }
                    //Toast.makeText(getContext(),invoice.getMaHD(),Toast.LENGTH_LONG).show();
                }
               invoiceAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public static int k=0;
    public  static int old=k;
    void update_data_invoiced() {
        //update dữ liệu
        lstContent_check_size=new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Invoice").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    lstContent_check_size.add(snap.getValue(Invoice.class));
                }
               k=lstContent_check_size.size();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
