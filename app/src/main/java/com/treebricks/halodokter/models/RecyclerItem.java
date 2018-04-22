package com.treebricks.halodokter.models;

public class RecyclerItem {
    private String itemTitle;
    private String itemDescription;
    private int itemIcon;
    private Class activityClass;

    public RecyclerItem(String itemTitle, String itemDescription, int itemIcon, Class activityClass) {
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemIcon = itemIcon;
        this.activityClass = activityClass;
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

    public Class getActivityClass() {
        return activityClass;
    }
}
