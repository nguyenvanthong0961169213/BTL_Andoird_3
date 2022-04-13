package com.example.btl_ordering_food_app_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.btl_ordering_food_app_2.Fragment.Adapter.invoice_adapter;
import com.example.btl_ordering_food_app_2.Model.Invoice;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HDactivity extends AppCompatActivity {

    private static RecyclerView rcvHoaDon;
    private invoice_adapter invoiceAdapter;
    static List<Invoice> lstContentHD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hdactivity);
        rcvHoaDon=findViewById(R.id.rcv_hoadon);
        lstContentHD=new ArrayList<>();
        GridLayoutManager gridLayoutManagerHD = new GridLayoutManager(this,1);
        rcvHoaDon.setLayoutManager(gridLayoutManagerHD);
        invoiceAdapter=new invoice_adapter(lstContentHD,this);
        rcvHoaDon.setAdapter(invoiceAdapter);
        load_data_invoice(Layout_main.MaKh);
        Toast.makeText(this, ""+lstContentHD.size(), Toast.LENGTH_SHORT).show();
    }
    Invoice invoice;
    void load_data_invoice(String Id_user)
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Invoice").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    invoice=(snap.getValue(Invoice.class));
                    if(invoice.getMaKH().equals(Id_user))
                    {
                        lstContentHD.add(snap.getValue(Invoice.class));
                    }
                }
                invoiceAdapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void GetData(Invoice invoice) {
        Intent intent = new Intent(this, activity_cthd_invoice.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("invoice", (Serializable) invoice);
        intent.putExtras(bundle);
        startActivityForResult(intent,101);
    }
}