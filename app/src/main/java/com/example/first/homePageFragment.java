package com.example.first;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
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

public class homePageFragment extends Fragment {

    private ViewPager viewpager;
    private List<Fragment> listfragment;
    private TextView recommendTV;
    private TextView fashionTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.homepagefragment, null);
        viewpager = view.findViewById(R.id.viewPager);

        listfragment = new ArrayList<>();
        Fragment recommendFragment = new recommendFragment();
        Fragment followFragment = new followFragment();
        listfragment.add(recommendFragment);
        listfragment.add(followFragment);

        FragmentManager fm = getChildFragmentManager();
        myFragmentPagerAdapter mfpa = new myFragmentPagerAdapter(fm,listfragment);

        viewpager.setAdapter(mfpa);
        viewpager.setCurrentItem(0);//设置当前页是第一页

        recommendTV = view.findViewById(R.id.recommendTV);
        fashionTV = view.findViewById(R.id.fashionTV);

        recommendTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(0);

            }
        });

        fashionTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(1);
            }
        });



        return view;
    }


}
