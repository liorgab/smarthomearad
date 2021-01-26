package com.example.smarthomebeta;

import android.widget.Button;

import java.util.ArrayList;

public class orderDataModel {
    String itemName;
    String itemImage;
    Integer totalItem;
    Long ItemPrice;

    public orderDataModel(String itemName, String itemImage, Integer totalItem , Long ItemPrice) {
        this.itemName=itemName;
        this.itemImage=itemImage;
        this.totalItem=totalItem;
        this.ItemPrice = ItemPrice;

    }

    public Long getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(Long itemPrice) {
        ItemPrice = itemPrice;
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
