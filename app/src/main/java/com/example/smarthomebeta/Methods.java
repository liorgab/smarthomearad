package com.example.smarthomebeta;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Methods {

    public static String dateAndTimeTostring() {
        Date currentTime = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String strDate = dateFormat.format(currentTime);
        return strDate;
    }

    public static DataSnapshot getDataSnapshot(DatabaseReference dbref) {
        final DataSnapshot[] dataSnapshot = new DataSnapshot[1];
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataSnapshot[0] = snapshot;
                Log.d("TAG", "onCreate: "+dataSnapshot[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return dataSnapshot[0];
    }
}
