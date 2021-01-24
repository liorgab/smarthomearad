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
        importListItems();
        lv.setAdapter(new myListAdapter(this,R.layout.order_list_item,data));


    }
    private void importListItems() {

        Constants.REF_ITEMS.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot)
                {
                    String count = ""+snapshot.getChildrenCount();
                    // Log.d("TAG", "itemName: " +snapshot);
                    int i = 0;
                    for(DataSnapshot item_snapshot:snapshot.getChildren()) {
                        i++;
                        if (i<10 ) {
                            data.add(item_snapshot.child("itemName").getValue().toString());
                            Log.d("item name ",item_snapshot.child("itemName").getValue().toString());
                            Log.d("item Key",item_snapshot.getKey().toString());

                        }

                    }
                    Log.d("data after snap",data.toString());


                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        data.clear();
        for(int i=1;i<10;i++) {
            data.add("this is item " + i);
        }
        Log.d("data after string",data.toString());

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
                viewHolder.addOne = (Button) convertView.findViewById(R.id.addOneBtn);
                viewHolder.addOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "add clicked for list item" + position ,Toast.LENGTH_LONG ).show();
                        Log.d("TAG", "onClick: "+position);
                    }
                });
                viewHolder.subOne = (Button) convertView.findViewById(R.id.subOneBtn);
                viewHolder.totalItems = (TextView) convertView.findViewById(R.id.itemTotalTv);
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