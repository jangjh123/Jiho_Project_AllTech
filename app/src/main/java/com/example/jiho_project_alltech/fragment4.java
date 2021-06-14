package com.example.jiho_project_alltech;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class fragment4 extends Fragment {

    private RecyclerView recyclerView;
    private EditText editText;
    private Button button;
    private MyLastAdapter adapter;
    private ArrayList<Memo> myMemo = new ArrayList<>();
    private MyDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment4, container, false);

        db = new MyDatabase(getActivity());
        myMemo = db.RestoreAllMemo();
        recyclerView = view.findViewById(R.id.recyclerView);
        button = view.findViewById(R.id.button);
        editText = view.findViewById(R.id.editText);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyLastAdapter(myMemo, getActivity(), new RecyclerOnClickListener() {
            @Override
            public void itemOnClick(int itemId) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                AlertDialog dialog = builder.setMessage(db.getMemoById(itemId).getContent()+" delete?")
                        .setNegativeButton("Yeah", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.deleteMemo(itemId);
                                myMemo = db.RestoreAllMemo();
                                updateRecyclerView(myMemo);
                            }
                        })
                        .setPositiveButton("Nope", null)
                        .create();
                dialog.show();
            }
        });
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                Memo memo = new Memo (content);
                myMemo.add(memo);
                db.InsertMemo(memo);
                myMemo = db.RestoreAllMemo();
                updateRecyclerView(myMemo);
                editText.setText("");
            }
        });

        return view;
    }
    public void updateRecyclerView(ArrayList<Memo> myMemo) {
        adapter.setList(myMemo);
        recyclerView.setAdapter(adapter);
    }
}