package com.example.first;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by 大学生小赵 on 2018/3/15.
 */

public class myFragmentPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager fragmetmanager;  //创建FragmentManager
    private List<Fragment> listfragment; //创建一个List<Fragment>

    public myFragmentPagerAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.fragmetmanager=fm;
        this.listfragment=list;
    }

    @Override
    public Fragment getItem(int arg0) {
        return listfragment.get(arg0);//返回第几个fragment
    }

    @Override
    public int getCount() {
        return listfragment.size();//总共有多少个
    }
}
