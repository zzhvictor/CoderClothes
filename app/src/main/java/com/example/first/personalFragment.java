package com.example.first;

import android.content.Intent;
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

public class personalFragment extends Fragment {
    private ViewPager viewpager;
    private List<Fragment> listfragment;
    private TextView mycollocationTextView;
    private Button setBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.personalfragment,null);
        viewpager = view.findViewById(R.id.viewPager);
        setBtn =view.findViewById(R.id.setBtn);

        listfragment = new ArrayList<>();
        Fragment mycollocationFragment = new myCollocationFragment();
        listfragment.add(mycollocationFragment);

        FragmentManager fm = getChildFragmentManager();
        myFragmentPagerAdapter mfpa = new myFragmentPagerAdapter(fm,listfragment);

        viewpager.setAdapter(mfpa);
        viewpager.setCurrentItem(0);//设置当前页是第一页

        mycollocationTextView = view.findViewById(R.id.mycollocationTextView);

        mycollocationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(0);
            }
        });


//        followBtn = view.findViewById(R.id.followBtn);
//        fansBtn = view.findViewById(R.id.fansBtn);
//
//        followBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(),Follow.class);
//                startActivity(intent);
//            }
//        });
//
//        fansBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(),Fans.class);
//                startActivity(intent);
//            }
//        });

        setBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(),SetActivity.class),1);
            }
        });



        return view;
    }


}

