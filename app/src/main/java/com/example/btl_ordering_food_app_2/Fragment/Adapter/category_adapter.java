package com.example.btl_ordering_food_app_2.Fragment.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.btl_ordering_food_app_2.Model.category_obj;

import java.util.List;

public class category_adapter extends BaseAdapter {


    //Attributes
    //1.Khai báo các phần tử nằm trong lớp category_adapter
    //1.Lớp ngữ cảnh contaxt là lớp cha của lớp activity
    private Activity activity;
    //2.Nguồn dữ liệu cho Adapter
    private List<category_obj> data;
    //3.
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
