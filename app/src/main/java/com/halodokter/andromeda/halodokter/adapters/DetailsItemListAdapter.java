package com.halodokter.andromeda.halodokter.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.halodokter.andromeda.halodokter.R;
import com.halodokter.andromeda.halodokter.models.DetailsListItem;

import java.util.ArrayList;

public class DetailsItemListAdapter extends RecyclerView.Adapter<DetailsItemListAdapter.ViewHolder> {

    private ArrayList<DetailsListItem> detailsListItems = new ArrayList<>();
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemTitle;
        TextView tvItemDescription;
        Button btnItem;
        ViewHolder(View itemView) {
            super(itemView);
            tvItemTitle = itemView.findViewById(R.id.item_title);
            tvItemDescription = itemView.findViewById(R.id.item_description);
            btnItem = itemView.findViewById(R.id.item_button);
        }
    }

    public DetailsItemListAdapter(ArrayList<DetailsListItem> detailsListItems, Context context) {
        this.detailsListItems.clear();
        this.detailsListItems.addAll(detailsListItems);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_with_button, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DetailsListItem item = detailsListItems.get(position);
        holder.tvItemTitle.setText(item.getItemTitle());
        holder.tvItemDescription.setText(item.getItemDescription());
        holder.btnItem.setText(item.getButtonText());
        holder.btnItem.setOnClickListener(new View.OnClickListener() {
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
        return detailsListItems.size();
    }
}
