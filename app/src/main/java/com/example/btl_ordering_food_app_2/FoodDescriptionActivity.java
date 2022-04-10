package com.example.btl_ordering_food_app_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_ordering_food_app_2.Model.Food;
import com.example.btl_ordering_food_app_2.Fragment.tab_home.Fragment_cart;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class FoodDescriptionActivity extends AppCompatActivity {
    ImageView imgFoodDesception;
    TextView txtTenFoodDesception,txtGiaBanFoodDesception,txtThanhPhanFoodDescription;
    Button btnThemSpFoodDescription,btnThoatFoodDescription;
    static int id = 0;
    static Food food ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_description);
        imgFoodDesception = findViewById(R.id.ImgFoodDescription);
        txtTenFoodDesception = findViewById(R.id.txtTenFoodDescription);
        btnThemSpFoodDescription = findViewById(R.id.btn_themsp_Description);
        btnThoatFoodDescription = findViewById(R.id.btnThoatFoodDescription);
        txtGiaBanFoodDesception = findViewById(R.id.txtGiaBanFoodDescription);
        txtThanhPhanFoodDescription=findViewById(R.id.txtThanhPhanFoodDescription);
        food = new Food();
        SetData();

        btnThemSpFoodDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddOrder(food);
            }
        });
        btnThoatFoodDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    public void LayDulieu(Food food){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        food = (Food) bundle.getSerializable("food");
    }
    public void SetData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        FoodDescriptionActivity.food = (Food) bundle.getSerializable("food");
       // imgFoodDesception.setImageResource(food.getAnh());
        Picasso.get().load(food.getAnh()).into(imgFoodDesception);
        txtTenFoodDesception.setText(food.getTenSP());
        txtGiaBanFoodDesception.setText(String.valueOf(food.getGiaTien()));
        txtThanhPhanFoodDescription.setText("Thành Phần: "+food.getThanhPhan());
    }
    public void AddOrder(Food food){
        Fragment_cart fragment_cart = (Fragment_cart) getSupportFragmentManager().findFragmentById(R.id.navigation_cart);
        Fragment_cart.receiveDataFromFramentHome(food);
    }
}