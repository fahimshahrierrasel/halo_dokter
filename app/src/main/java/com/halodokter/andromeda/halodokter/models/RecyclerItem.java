package com.halodokter.andromeda.halodokter.models;

public class RecyclerItem {
    private String itemTitle;
    private String itemDescription;
    private int itemIcon;

    public RecyclerItem(String itemTitle, String itemDescription, int itemIcon) {
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemIcon = itemIcon;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getItemIcon() {
        return itemIcon;
    }
}
