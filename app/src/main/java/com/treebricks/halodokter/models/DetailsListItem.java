package com.treebricks.halodokter.models;

public class DetailsListItem extends RecyclerItem {
    private String buttonText;
    public DetailsListItem(String itemTitle, String itemDescription, int itemIcon, String buttonText, Class activityClass) {
        super(itemTitle, itemDescription, itemIcon, activityClass);
        this.buttonText = buttonText;
    }

    public String getButtonText() {
        return buttonText;
    }
}
