package com.example.jiho_project_alltech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyLastAdapter extends RecyclerView.Adapter<MyLastAdapter.ViewHolder> {
    private RecyclerOnClickListener listener;
    private ArrayList<Memo> myMemo = new ArrayList<>();
    private Context context;

    public MyLastAdapter(ArrayList<Memo> myMemo, Context context, RecyclerOnClickListener listener) {
        this.myMemo = myMemo;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public MyLastAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.last_fragment_item_list_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyLastAdapter.ViewHolder holder, int position) {
        Memo memo = myMemo.get(position);
        holder.textView_card.setText(myMemo.get(position).getContent());
        holder.textView_card.setTag(memo.getId());
    }

    @Override
    public int getItemCount() {
        return myMemo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_card;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView_card = itemView.findViewById(R.id.textView_card);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = (int)textView_card.getTag();
                    listener.itemOnClick(id);
                }
            });
        }
    }
    public void setList(ArrayList<Memo> myMemo) {
        this.myMemo = myMemo;
        notifyDataSetChanged();
    }
}
