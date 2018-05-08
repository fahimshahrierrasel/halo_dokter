package com.treebricks.dokuter.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.treebricks.dokuter.R;
import com.treebricks.dokuter.models.RecyclerItem;

import java.util.ArrayList;

public class LeftSideDrawableListAdapter extends RecyclerView.Adapter<LeftSideDrawableListAdapter.ViewHolder> {

    private ArrayList<RecyclerItem> recyclerItems = new ArrayList<>();
    private Context context;
    private OnItemClickListener listener;

    public LeftSideDrawableListAdapter(ArrayList<RecyclerItem> recyclerItems, Context context) {
        this.recyclerItems.clear();
        this.recyclerItems.addAll(recyclerItems);
        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_left_side_drawable, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(recyclerItems.get(position));
    }

    @Override
    public int getItemCount() {
        return recyclerItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemTitle;
        TextView tvItemDescription;
        ImageView ivItemIcon;
        CardView cvCardItem;

        ViewHolder(final View itemView) {
            super(itemView);
            cvCardItem = itemView.findViewById(R.id.cvItemCardView);
            tvItemTitle = itemView.findViewById(R.id.item_title);
            tvItemDescription = itemView.findViewById(R.id.item_description);
            ivItemIcon = itemView.findViewById(R.id.item_icon);
            cvCardItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                            listener.onItemClick(itemView, position);
                    }
                }
            });
        }

        void bind(final RecyclerItem item) {
            tvItemTitle.setText(item.getItemTitle());
            tvItemDescription.setText(item.getItemDescription());
            ivItemIcon.setImageDrawable(context.getResources().getDrawable(item.getItemIcon()));
        }
    }
}
