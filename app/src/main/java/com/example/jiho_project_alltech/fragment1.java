package com.example.jiho_project_alltech;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class fragment1 extends Fragment {

    private Spinner spinner;
    private ArrayAdapter arrayAdapter;
    private TextView textView_Spinner_Result;
    private ArrayList arrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        spinner = view.findViewById(R.id.spinner);
        textView_Spinner_Result = view.findViewById(R.id.textView_Spinner_Result);

        arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.myArr,R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                } else {
                    Toast.makeText(getContext(), position + " chosen , " + spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    String spinner_result = spinner.getSelectedItem().toString();
                    textView_Spinner_Result.setText(spinner_result);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }
}