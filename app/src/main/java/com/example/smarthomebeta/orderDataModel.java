package com.example.smarthomebeta;

import android.widget.Button;

import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class orderDataModel {
    String itemName;
    String itemImage;
    Integer totalItem;

    public orderDataModel(String itemName, String itemImage,  Integer totalItem ) {
        this.itemName=itemName;
        this.itemImage=itemImage;
        this.totalItem=totalItem;

    }

    public String getItemName() {
        return itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }
}
