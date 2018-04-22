package com.treebricks.halodokter.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.treebricks.halodokter.R;
import com.treebricks.halodokter.models.RecyclerItem;

import java.util.ArrayList;

public class RightSideDrawableNoDescListAdapter extends RecyclerView.Adapter<RightSideDrawableNoDescListAdapter.ViewHolder> {

    private ArrayList<RecyclerItem> recyclerItems = new ArrayList<>();
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemTitle;
        CardView cvCardItem;
        ViewHolder(View itemView) {
            super(itemView);
            cvCardItem = itemView.findViewById(R.id.cvItemCardView);
            tvItemTitle = itemView.findViewById(R.id.item_title);
        }
    }

    public RightSideDrawableNoDescListAdapter(ArrayList<RecyclerItem> recyclerItems, Context context) {
        this.recyclerItems.clear();
        this.recyclerItems.addAll(recyclerItems);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_right_side_drawable_no_des, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final RecyclerItem item = recyclerItems.get(position);
        holder.tvItemTitle.setText(item.getItemTitle());
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
