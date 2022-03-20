package com.example.btl_ordering_food_app_2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.btl_ordering_food_app_2.Fragment.tab_home.ViewPagerAdapter;
import com.example.btl_ordering_food_app_2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Fragment_home_app extends Fragment {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home_app,container,false);
        initUi();
        return view;
    }

    private void initUi() {
        viewPager=view.findViewById(R.id.view_pager);
        bottomNavigationView=view.findViewById(R.id.bottom_navigation);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(1);
        bottomNavigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.navigation_remarkable:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.navigation_cart:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               switch (position)
               {
                   case 0:
                       bottomNavigationView.getMenu().findItem(R.id.navigation_remarkable).setChecked(true);
                       break;
                   case 1:
                       bottomNavigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);
                       break;
                   case 2:
                       bottomNavigationView.getMenu().findItem(R.id.navigation_cart).setChecked(true);
                       break;
               }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}