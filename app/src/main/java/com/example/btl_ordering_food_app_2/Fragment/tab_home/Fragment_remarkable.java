package com.example.btl_ordering_food_app_2.Fragment.tab_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.btl_ordering_food_app_2.R;

public class Fragment_remarkable extends Fragment {


    ViewFlipper vfQuangCao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_remarkable,container,false);
        vfQuangCao = view.findViewById(R.id.vfQuangCao);
        int Image[] = {R.drawable.qc1 , R.drawable.qc2 , R.drawable.qc3};
        for (int item : Image) {
            SetImageFlip(item);
        }
        return view;
    }
    private void SetImageFlip(int image)
    {
        ImageView imgView = new ImageView(getActivity());
        imgView.setImageResource(image);
        imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        vfQuangCao.addView(imgView);
    }

}
