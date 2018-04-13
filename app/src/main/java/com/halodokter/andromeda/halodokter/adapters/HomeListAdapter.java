package com.halodokter.andromeda.halodokter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.halodokter.andromeda.halodokter.R;
import com.halodokter.andromeda.halodokter.models.RecyclerItem;

import java.util.ArrayList;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    private ArrayList<RecyclerItem> recyclerItems = new ArrayList<>();
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemTitle;
        TextView tvItemDescription;
        ImageView ivItemIcon;
        ViewHolder(View itemView) {
            super(itemView);
            tvItemTitle = itemView.findViewById(R.id.item_title);
            tvItemDescription = itemView.findViewById(R.id.item_description);
            ivItemIcon = itemView.findViewById(R.id.item_icon);
        }
    }

    public HomeListAdapter(ArrayList<RecyclerItem> recyclerItems, Context context) {
        this.recyclerItems.clear();
        this.recyclerItems.addAll(recyclerItems);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerItem item = recyclerItems.get(position);
        holder.tvItemTitle.setText(item.getItemTitle());
        holder.tvItemDescription.setText(item.getItemDescription());
        holder.ivItemIcon.setImageDrawable(context.getResources().getDrawable(item.getItemIcon()));
    }

    @Override
    public int getItemCount() {
        return recyclerItems.size();
    }
}
