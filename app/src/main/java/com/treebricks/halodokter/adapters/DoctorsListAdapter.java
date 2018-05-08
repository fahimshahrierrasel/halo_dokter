package com.treebricks.halodokter.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.treebricks.halodokter.R;

import java.util.ArrayList;

public class DoctorsListAdapter extends RecyclerView.Adapter<DoctorsListAdapter.ViewHolder> {

    private ArrayList<String> recyclerItems = new ArrayList<>();
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemTitle;
        CardView cvCardItem;
        ViewHolder(View itemView) {
            super(itemView);
            cvCardItem = itemView.findViewById(R.id.card_container);
            tvItemTitle = itemView.findViewById(R.id.tv_doctor_name);
        }
    }


    public DoctorsListAdapter(ArrayList<String> recyclerItems, Context context) {
        this.recyclerItems.clear();
        this.recyclerItems.addAll(recyclerItems);

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.doctor_card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String item = recyclerItems.get(position);
        holder.cvCardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return recyclerItems.size();
    }
}
