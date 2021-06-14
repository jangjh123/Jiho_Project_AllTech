package com.example.jiho_project_alltech;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class fragment2 extends Fragment {

    private TextView textView_number, textView_up, textView_down;

    private int a = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);

        SharedPreferences MyMiniDB = this.getActivity().getSharedPreferences("MiniDB", Context.MODE_PRIVATE);
        a = MyMiniDB.getInt("MyNumber", 0);

            textView_number = view.findViewById(R.id.textView_number);
            textView_up = view.findViewById(R.id.textView_up);
            textView_down = view.findViewById(R.id.textView_down);

            textView_number.setText(a+"");
            textView_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    a --;
                    textView_number.setText(a+"");
                }
            });
            textView_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    a ++;
                    textView_number.setText(a+"");
                }
            });


        return view;
    }
    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("MiniDB", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = sharedPreferences.edit();
        myEditor.putInt("MyNumber", a);
        myEditor.apply();
    }
}