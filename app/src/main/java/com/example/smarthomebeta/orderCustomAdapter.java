package com.example.smarthomebeta;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;

public class orderCustomAdapter extends ArrayAdapter<orderDataModel> implements View.OnClickListener{

    private ArrayList<orderDataModel> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView itemName;
        TextView itemPrice;
        TextView totalPrice;
        EditText totalItems;
        ImageView itemImage;
        Button addBtn;
        Button subBtn;
    }

    public orderCustomAdapter(ArrayList<orderDataModel> data, Context context) {
        super(context, R.layout.order_list_item, data);
        this.dataSet = data;
        this.mContext=context;
    }
    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Log.d("TAG", "getTag: "+v.getTag());

        Object object= getItem(position);
        orderDataModel dataModel=(orderDataModel)object;

        switch (v.getId())
        {
            case R.id.itemNameTv:
                //Snackbar.make(v, "Release date " +dataModel.getItemName(), Snackbar.LENGTH_LONG)
                //        .setAction("No action", null).show();
                break;
            case R.id.addOneBtn:
                //Snackbar.make(v, "button add " , Snackbar.LENGTH_LONG)
                //        .setAction("No action", null).show();
                Integer addTotal = dataModel.getTotalItem()+1;

                Log.d("TAG", "onClick: "+dataSet.get(position).toString());
                dataModel.setTotalItem(addTotal);
                dataModel.setTotalPrice(addTotal*dataModel.getItemPrice());
                Log.d("TAG", "getTotalPrice: "+dataModel.getTotalPrice().toString());
                notifyDataSetChanged();
                break;
            case R.id.subOneBtn:
                //Snackbar.make(v, "button sub " , Snackbar.LENGTH_LONG)
                //        .setAction("No action", null).show();
                Integer subTotal = dataModel.getTotalItem();
                if (subTotal>0) {
                    dataModel.setTotalItem(subTotal-1);
                    dataModel.setTotalPrice((subTotal-1)*dataModel.getItemPrice());
                }
                notifyDataSetChanged();
                break;
        }
    }
    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        orderDataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.order_list_item, parent, false);
            viewHolder.itemName = (TextView) convertView.findViewById(R.id.itemNameTv);
            viewHolder.itemPrice = (TextView) convertView.findViewById(R.id.itemPriceTv);
            viewHolder.totalPrice = (TextView) convertView.findViewById(R.id.totalPriceTv);
            viewHolder.totalItems = (EditText) convertView.findViewById(R.id.itemTotalEt);
            viewHolder.itemImage = (ImageView) convertView.findViewById(R.id.itemImageIv);
            viewHolder.addBtn = (Button) convertView.findViewById(R.id.addOneBtn) ;
            viewHolder.subBtn = (Button) convertView.findViewById(R.id.subOneBtn) ;
            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.fragment_fade_enter : R.anim.fragment_open_enter);
        result.startAnimation(animation);

        lastPosition = position;

        viewHolder.itemName.setText(dataModel.getItemName());
        //viewHolder.totalItems.setText(dataModel.getTotalItem());
        viewHolder.totalItems.setText(dataModel.getTotalItem().toString());
        // Log.d("TAG", "setText: " +dataModel.getTotalItem().toString());
        viewHolder.itemName.setOnClickListener(this);
        viewHolder.addBtn.setOnClickListener(this);
        viewHolder.addBtn.setTag(position);
        viewHolder.subBtn.setOnClickListener(this);
        viewHolder.subBtn.setTag(position);
        viewHolder.itemName.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }

}
