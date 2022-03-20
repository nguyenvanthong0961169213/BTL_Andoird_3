package com.example.btl_ordering_food_app_2.Fragment.tab_home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new Fragment_remarkable();
            case 1:
                return new Fragment_home();
            case 2:
                return new Fragment_cart();
            default:
                return new Fragment_home();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
