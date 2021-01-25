package com.example.smarthomebeta;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class order extends AppCompatActivity {
    private ArrayList<String> data = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);
        ListView lv = (ListView) findViewById(R.id.orderListview);
        data = importListItems();

        Log.d("data after 3",data.toString());

        lv.setAdapter(new myListAdapter(this,R.layout.order_list_item,data));
        Log.d("data after 4",data.toString());

    }
    private ArrayList<String> importListItems() {
        ArrayList<String> data = new ArrayList<String>();

        Constants.REF_ITEMS.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
                public void onDataChange(@NonNull DataSnapshot snapshot)
                {
                    Log.d("TAG", "data after 0: " +snapshot);
                    String strData = ""+snapshot;
                    String count = ""+snapshot.getChildrenCount();

                    for(DataSnapshot item_snapshot:snapshot.getChildren()) {
                            data.add(item_snapshot.child("itemName").getValue().toString());
                            //Log.d("item name ",item_snapshot.child("itemName").getValue().toString());
                            //Log.d("item Key",item_snapshot.getKey().toString());
                    }
                    Log.d("data after 1",data.toString());
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

        data.clear();
        for(int i=1;i<10;i++) {
          data.add("this is item " + i);
        }
       Log.d("data after 2",data.toString());
       return data;
    }

    private class myListAdapter extends ArrayAdapter<String> {
        private int layout;
        public myListAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
            super(context, resource, objects);
            layout = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder mainViewHolder = null;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout,parent,false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.itemImage = (ImageView) convertView.findViewById(R.id.itemImageIv);
                viewHolder.itemName = (TextView) convertView.findViewById(R.id.itemNameTv);
                viewHolder.subOne = (Button) convertView.findViewById(R.id.subOneBtn);
                viewHolder.totalItems = (TextView) convertView.findViewById(R.id.itemTotalTv);
                viewHolder.addOne = (Button) convertView.findViewById(R.id.addOneBtn);
                viewHolder.addOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "add clicked for list item" + position ,Toast.LENGTH_LONG ).show();
                        Log.d("TAG", "onClick: "+position);
                        //Integer total = Integer.valueOf(viewHolder.totalItems.getText().toString());
                        //total = (Integer.valueOf(total)+1);
                        //viewHolder.totalItems = (TextView) total.toString();
                    }
                });
                convertView.setTag(viewHolder);
            }
            else {
                mainViewHolder = (ViewHolder) convertView.getTag();
                mainViewHolder.itemName.setText(getItem(position));
            }

            return convertView;
        }
    }
    public class ViewHolder {
        ImageView itemImage;
        TextView itemName;
        Button addOne;
        Button subOne;
        TextView totalItems;

    }
}