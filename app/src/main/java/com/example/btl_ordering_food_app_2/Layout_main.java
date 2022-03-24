package com.example.btl_ordering_food_app_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_ordering_food_app_2.Fragment.Fragment_update_info_user;
import com.example.btl_ordering_food_app_2.Fragment.Fragment_update_password;
import com.example.btl_ordering_food_app_2.Fragment.Fragment_home_app;
import com.example.btl_ordering_food_app_2.Model.user_obj;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;

public class Layout_main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_HOME=0;
    private static final int FRAGMENT_UPDATE_INFO_USER=1;
    private static final int FRAGMENT_UPDATE_INFO_PASSWORD=2;
    private static final int FRAGMENT_LOGOUT=3;

    private int CurrentFragment=FRAGMENT_HOME;

    private DrawerLayout drawerLayout;
    final Context context = this;
    TextView txt_Name_drawer,txt_UserName,txt_address,txt_phone_number,txt_sex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_main);



        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView=findViewById(R.id.navigation_view);
        update_data_navigation_drawer(navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new Fragment_home_app());
        XinQuyen();
    }
    void update_data_navigation_drawer(NavigationView navigationView)
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user_obj user = (user_obj) bundle.get("user_obj_data");
        txt_Name_drawer=navigationView.getHeaderView(0).findViewById(R.id.txt_Name_drawer);
        txt_UserName=navigationView.getHeaderView(0).findViewById(R.id.txt_UserName_drawer);
        txt_phone_number=navigationView.getHeaderView(0).findViewById(R.id.txt_phone_number_drawer);
        txt_address=navigationView.getHeaderView(0).findViewById(R.id.txt_address_drawer);
        txt_sex=navigationView.getHeaderView(0).findViewById(R.id.txt_sex);
        txt_Name_drawer.setText(user.getName());
        txt_UserName.setText(user.getPhonenumber());
        txt_phone_number.setText(user.getPhonenumber());
        txt_address.setText(user.getAddress());
        txt_sex.setText(user.isSex()?"Nam":"Nữ");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.navigation_home_app)
        {
            replaceFragment(new Fragment_home_app());
//            CurrentFragment=FRAGMENT_REMARKABLE;
        }else if(id==R.id.navigation_update_info_user)
        {

            Intent intent_data = getIntent();
            Bundle bundle_data = intent_data.getExtras();
            user_obj user = (user_obj) bundle_data.get("user_obj_data");

            Fragment_update_info_user fragment_update_info_user = new Fragment_update_info_user();
            Bundle bundle = new Bundle();
            bundle.putSerializable("user_obj_data_update",(Serializable) user);;
            fragment_update_info_user.setArguments(bundle);//Here pass your data


            replaceFragment(fragment_update_info_user);
        }else if(id==R.id.navigation_update_password)
        {
            replaceFragment(new Fragment_update_password());
 //           CurrentFragment=FRAGMENT_REMARKABLE;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }

    public boolean Granted_camera=false;
    final static int CODECamera=1;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case CODECamera:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    Granted_camera=true;
                    Toast.makeText(this,"được",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this,"Bạn chưa cấp quyền cho cammera",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    private void XinQuyen()
    {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.CAMERA
            },CODECamera);
        }
    }
}