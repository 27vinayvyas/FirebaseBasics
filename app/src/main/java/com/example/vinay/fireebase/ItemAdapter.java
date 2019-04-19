package com.example.vinay.fireebase;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {


    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        public TextView textView1;
        public TextView textView2;
        public TextView textView3;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.username);
            textView2=itemView.findViewById(R.id.comment);
            textView3=itemView.findViewById(R.id.date);

        }
    }

    private ArrayList<Item>itemArrayList;

    public ItemAdapter(ArrayList<Item>mitemArrayList){
        itemArrayList=mitemArrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items,viewGroup,false);
        ItemViewHolder viewHolder=new ItemViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {

        Item item=itemArrayList.get(i);
        itemViewHolder.textView1.setText(item.getName());
        itemViewHolder.textView2.setText(item.getSequence());
        itemViewHolder.textView3.setText(item.getComment());

    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }



}
