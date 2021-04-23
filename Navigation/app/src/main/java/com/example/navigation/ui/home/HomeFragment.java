package com.example.navigation.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.navigation.R;
import com.example.navigation.offering;
import com.example.navigation.prayer;
import com.example.navigation.prostration;
import com.example.navigation.ritual;
import com.google.android.material.imageview.ShapeableImageView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class HomeFragment extends Fragment implements View.OnClickListener {
    ShapeableImageView img1,img2,img3,img4;
    private int[] mImage = new int[] {
            R.drawable.m1,R.drawable.m2,R.drawable.slider
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        CarouselView cView = root.findViewById(R.id.carouselView);
        cView.setPageCount(mImage.length);
        cView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImage[position]);
            }
        });

        img1 = (ShapeableImageView)root.findViewById(R.id.ritual);
        img2 = (ShapeableImageView)root.findViewById(R.id.offering);
        img3 = (ShapeableImageView)root.findViewById(R.id.prostration);
        img4 = (ShapeableImageView)root.findViewById(R.id.prayer);

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ritual:
                startActivity(new Intent(getActivity(), ritual.class));
                break;
            case R.id.offering:
                startActivity(new Intent(getActivity(), offering.class));
                break;
            case R.id.prostration:
                startActivity(new Intent(getActivity(), prostration.class));
                break;
            case R.id.prayer:
                startActivity(new Intent(getActivity(), prayer.class));
                break;
        }
    }
}