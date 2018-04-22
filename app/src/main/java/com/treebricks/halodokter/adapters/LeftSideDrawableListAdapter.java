package com.treebricks.halodokter.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.treebricks.halodokter.R;
import com.treebricks.halodokter.models.RecyclerItem;

import java.util.ArrayList;

public class LeftSideDrawableListAdapter extends RecyclerView.Adapter<LeftSideDrawableListAdapter.ViewHolder> {

    private ArrayList<RecyclerItem> recyclerItems = new ArrayList<>();
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemTitle;
        TextView tvItemDescription;
        ImageView ivItemIcon;
        CardView cvCardItem;
        ViewHolder(View itemView) {
            super(itemView);
            cvCardItem = itemView.findViewById(R.id.cvItemCardView);
            tvItemTitle = itemView.findViewById(R.id.item_title);
            tvItemDescription = itemView.findViewById(R.id.item_description);
            ivItemIcon = itemView.findViewById(R.id.item_icon);
        }
    }

    public LeftSideDrawableListAdapter(ArrayList<RecyclerItem> recyclerItems, Context context) {
        this.recyclerItems.clear();
        this.recyclerItems.addAll(recyclerItems);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_left_side_drawable, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final RecyclerItem item = recyclerItems.get(position);
        holder.tvItemTitle.setText(item.getItemTitle());
        holder.tvItemDescription.setText(item.getItemDescription());
        holder.ivItemIcon.setImageDrawable(context.getResources().getDrawable(item.getItemIcon()));
        holder.cvCardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getActivityClass() != null) {
                    context.startActivity(new Intent(context, item.getActivityClass()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return recyclerItems.size();
    }
}
