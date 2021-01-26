package com.example.smarthomebeta;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class order extends AppCompatActivity {
    ArrayList<Integer> itemCatalogNum;
    ArrayList<orderDataModel> dataModels;
    ListView lv;
    private static orderCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);
        lv = (ListView) findViewById(R.id.orderListview);
        dataModels = new ArrayList<>();

        itemCatalogNum = new ArrayList<Integer>();
        Constants.REF_ITEMS.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot data: snapshot.getChildren()){


                    itemCatalogNum.add(Integer.parseInt(data.getKey()));
                    String itemName = data.child("itemName").getValue().toString();
                    dataModels.add(new orderDataModel(itemName,itemName+".png",0));
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
