package com.example.jiho_project_alltech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private Button insert, intent;
    private RecyclerView recyclerView;
    private ArrayList<Data> myList = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog(MainActivity2.this);
                customDialog.show();
                customDialog.setDialogListener(new CustomDialog.CustomDialogListener() {
                    @Override
                    public void onInsertClicked(String title, String content) {
                        String DataTitle = title;
                        String DataContent = content;
                        Data data = new Data(DataTitle, DataContent);
                        myList.add(data);
                        adapter.notifyDataSetChanged();
                        Snackbar.make(v,data.getTitle()+"", BaseTransientBottomBar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelClicked() {
                        Toast.makeText(MainActivity2.this, "Canceled", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });

    }
    public void initView() {
        insert = findViewById(R.id.insert);
        intent = findViewById(R.id.intent);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyAdapter(myList, this);
        recyclerView.setAdapter(adapter);

    }
}