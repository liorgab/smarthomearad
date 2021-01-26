package com.example.smarthomebeta;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class order extends AppCompatActivity {
    ArrayList<Integer> itemCatalogNum;
    ArrayList<orderDataModel> dataModels;
    ListView lv;
    private static orderCustomAdapter adapter;
    TextView totalPrice;

    public Long calculateTotalPrice(ArrayList<orderDataModel> data){
        Long orderTotalPrice = null;
        for (int counter = 0; counter < data.size(); counter++) {
            orderTotalPrice = orderTotalPrice + data.get(counter).getItemPrice()*data.get(counter).getTotalItem();
        }
        return orderTotalPrice;
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);
        lv = (ListView) findViewById(R.id.orderListview);
        totalPrice = (TextView) findViewById(R.id.totalmoneytv);
        dataModels = new ArrayList<>();
        itemCatalogNum = new ArrayList<Integer>();
        Constants.REF_ITEMS.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot data: snapshot.getChildren()){


                    itemCatalogNum.add(Integer.parseInt(data.getKey()));
                    String itemName = data.child("itemName").getValue().toString();
                    Long itemprice =  Long.valueOf((Long) data.child("itemPrice").getValue());
                    Long itemDisplay =  Long.valueOf((Long) data.child("ItemDisplay").getValue());
                    Log.d("TAG", "itemDisplay: " + itemprice);

                    if (itemDisplay == 1) {
                        dataModels.add(new orderDataModel(itemName,itemName+".png",0, itemprice));
                    }
                }
                adapter= new orderCustomAdapter(dataModels,getApplicationContext());

                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        orderDataModel dataModel= dataModels.get(position);
                        Log.d("TAG", "in order activity: " + position);
                        adapter.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
