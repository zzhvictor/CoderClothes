package com.example.first;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 大学生小赵 on 2018/3/8.
 */

public class searchFragment extends Fragment {

    private ViewPager viewpager;
    private List<Fragment> listfragment;
    private TextView userTextView;
    private TextView collocationTextView;
    private Button searchBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.searchfragment, null);
        viewpager = view.findViewById(R.id.viewPager);
        searchBtn = view.findViewById(R.id.InSearchBtn);
        searchBtn.setBackgroundColor(Color.TRANSPARENT);


        listfragment = new ArrayList<>();
        Fragment userFragment = new userFragment();
        Fragment collocationFragment = new collocationFragment();
        listfragment.add(userFragment);
        listfragment.add(collocationFragment);

        FragmentManager fm = getChildFragmentManager();
        myFragmentPagerAdapter mfpa = new myFragmentPagerAdapter(fm,listfragment);

        viewpager.setAdapter(mfpa);
        viewpager.setCurrentItem(0);//设置当前页是第一页

        userTextView = view.findViewById(R.id.userTextView);
        collocationTextView = view.findViewById(R.id.collocationTextView);

        userTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(0);
            }
        });

        collocationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(1);
            }
        });


        return view;
    }
}


