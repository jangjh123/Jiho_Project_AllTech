package com.example.jiho_project_alltech;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> myArr = new ArrayList<>();

    public MyPagerAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }
    public void addItem (Fragment item) {
        myArr.add(item);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return myArr.get(position);
    }

    @Override
    public int getCount() {
        return myArr.size();
    }
}
