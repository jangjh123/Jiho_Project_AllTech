package com.example.jiho_project_alltech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;

public class MainActivity3 extends AppCompatActivity {

    private fragment1 fragment1;
    private fragment2 fragment2;
    private fragment3 fragment3;
    private fragment4 fragment4;
    private ViewPager pager;
    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();


        pager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    public void initView () {
        pager = findViewById(R.id.pager);
        fragment1 = new fragment1();
        fragment2 = new fragment2();
        fragment3 = new fragment3();
        fragment4 = new fragment4();
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setOffscreenPageLimit(4);
        adapter.addItem(fragment1);
        adapter.addItem(fragment2);
        adapter.addItem(fragment3);
        adapter.addItem(fragment4);
    }
}