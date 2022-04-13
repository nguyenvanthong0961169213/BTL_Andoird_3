package com.example.btl_ordering_food_app_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.btl_ordering_food_app_2.Fragment.Adapter.invoice_adapter;
import com.example.btl_ordering_food_app_2.Fragment.Adapter.invoice_detail_adapter;
import com.example.btl_ordering_food_app_2.Model.Invoice;
import com.example.btl_ordering_food_app_2.Model.Invoice_detail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class activity_cthd_invoice extends AppCompatActivity {

    static Invoice invoice ;
    invoice_detail_adapter invoice_adapter;
    private static RecyclerView rcvconten;

    List<Invoice_detail> lstconten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cthd_invoice);
        rcvconten=findViewById(R.id.rcvChiTietHD);
        LayDulieu();
        Toast.makeText(this, invoice.getMaHD(), Toast.LENGTH_SHORT).show();
        lstconten=new ArrayList<>();
        load_data_invoice_detail();
        invoice_adapter=new invoice_detail_adapter(lstconten,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        rcvconten.setLayoutManager(gridLayoutManager);
        rcvconten.setAdapter(invoice_adapter);
    }
    public void LayDulieu(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        activity_cthd_invoice.invoice = (Invoice) bundle.getSerializable("invoice");
    }
    void load_data_invoice_detail()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Invoice_detail").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Invoice_detail invoice_detail=snap.getValue((Invoice_detail.class));
                  if(invoice_detail.getMaHD().equals(invoice.getMaHD()))
                  {
                      lstconten.add(invoice_detail);
                  }
                }
                invoice_adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}