package com.example.first;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;

    //存放fragment对应的容器
    private FrameLayout fragmentContain;

    private FragOperManager manager;
    private homePageFragment homePageFragment;
    private searchFragment searchFragment;
    private releaseFragment releaseFragment;
    private personalFragment personalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentContain = (FrameLayout) findViewById(R.id.fragmentContain);

//        homePageBtn.performClick();//设置默认第一个选项卡对应的fragment显示

        homePageFragment = new homePageFragment();
        releaseFragment = new releaseFragment();
        searchFragment = new searchFragment();
        personalFragment = new personalFragment();

        manager = new FragOperManager(this, R.id.fragmentContain);
        manager.chReplaceFrag(homePageFragment,"homePageFragment",false);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 1:
                Toast.makeText(getApplicationContext(),"修改成功，请重新登录",Toast.LENGTH_SHORT).show();
                this.finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.homePageBtn:
                manager.chReplaceFrag(homePageFragment, "a", false);
                break;
            case R.id.searchBtn:
                manager.chReplaceFrag(searchFragment, "b", false);
                break;
            case R.id.releaseBtn:
                manager.chReplaceFrag(releaseFragment, "c", false);
                break;
            case R.id.personalBtn:
                manager.chReplaceFrag(personalFragment, "e", false);
                break;
        }
    }






}
