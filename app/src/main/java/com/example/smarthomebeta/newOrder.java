package com.example.smarthomebeta;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.smarthomebeta.Constants.M_Auth;
import static com.example.smarthomebeta.Constants.REF_ORDERS;
import static com.example.smarthomebeta.Methods.dateAndTimeTostring;

public class newOrder extends AppCompatActivity {
    private static orderCustomAdapter adapter;
    ArrayList<Integer> itemCatalogNum;
    ArrayList<orderDataModel> dataModels;
    HashMap<String, String> items=new HashMap<>();


    ListView lv;
    TextView orderTotalPrice;
    TextView orderTotalItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);
        lv = (ListView) findViewById(R.id.orderListview);
        orderTotalPrice = (TextView) findViewById(R.id.totalmoneytv);
        orderTotalItems = (TextView) findViewById(R.id.totalitemstv);
        dataModels = new ArrayList<>();
        itemCatalogNum = new ArrayList<Integer>();

        Constants.REF_ITEMS.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    itemCatalogNum.add(Integer.parseInt(data.getKey()));
                    String itemName = data.child("itemName").getValue().toString();
                    Integer itemPrice =  Long.valueOf((Long) data.child("itemPrice").getValue()).intValue();
                    Long itemDisplay =  Long.valueOf((Long) data.child("ItemDisplay").getValue());
                    Log.d("TAG", "itemDisplay: " + itemPrice);
                    if (itemDisplay == 1) {
                        dataModels.add(new orderDataModel(itemName,itemName+".png",0, itemPrice,0));
                    }
                }
                adapter= new orderCustomAdapter(dataModels,getApplicationContext());
                lv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void calcTotal(View view) {
            Integer totalPrice = 0;
            Integer totalItems = 0;
            String itemCatalog;
            Integer itemTotal;
            items.clear();
            for (int counter = 0; counter < dataModels.size(); counter++) {
                itemTotal = dataModels.get(counter).getTotalItem();
                totalPrice = totalPrice + dataModels.get(counter).getItemPrice()*itemTotal;
                totalItems = totalItems+itemTotal;
                if (itemTotal>0){
                    itemCatalog=itemCatalogNum.get(counter).toString();
                    items.put(itemCatalog.toString(),itemTotal.toString());
                    Log.d("TAG", "catnum: " + itemCatalog.toString()+"total: " + itemTotal.toString());

                }
            }
        Log.d("TAG", "itemsArray: " + items.toString());

        orderTotalPrice.setText(totalPrice.toString());
        orderTotalItems.setText(totalItems.toString());

    };

    public void saveorder(View view) {

        FirebaseUser user = M_Auth.getCurrentUser();
        String userid = user.getUid();
        String UidHouse;
        calcTotal(view);
        String curDate = dateAndTimeTostring();
        Order orderdate = new Order(curDate,"1", userid, orderTotalItems.getText().toString(),orderTotalPrice.getText().toString(),
                "1","Null","Null",curDate,items);
        REF_ORDERS.push().setValue(orderdate);
    }
}

