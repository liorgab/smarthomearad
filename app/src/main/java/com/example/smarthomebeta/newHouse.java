package com.example.smarthomebeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;

import static com.example.smarthomebeta.FBref.refContacts;

public class newHouse extends AppCompatActivity {

    EditText houseCityEdt,houseStreetEdt,houseStreetNumEdt,houseApartmentNumberEdt,houseFloorEdt,housePhoneNumEdt,
            houseDnsEdt,housePasswordEdt;
    String uidContact = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_house);
        houseCityEdt = (EditText)findViewById(R.id.houseCityEdt);
        houseStreetEdt = (EditText)findViewById(R.id.houseStreetEdt);
        houseStreetNumEdt = (EditText)findViewById(R.id.houseStreetNumEdt);
        houseApartmentNumberEdt = (EditText)findViewById(R.id.houseApartmentNumberEdt);
        houseFloorEdt = (EditText)findViewById(R.id.houseFloorEdt);
        housePhoneNumEdt = (EditText)findViewById(R.id.housePhoneNumEdt);
        houseDnsEdt = (EditText)findViewById(R.id.houseDnsEdt);
        housePasswordEdt = (EditText)findViewById(R.id.housePasswordEdt);
    }

    public void addHouse(View view) {
        Log.d("TAG", "addHouse: start");
        String uidContact = "" + getIntent().getSerializableExtra("contact").toString();
        Log.d("TAG", "Uidcontact: "+uidContact.toString());
        Constants.REF_PRIVATE_CURRENT_YEAR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                String count = ""+snapshot.getChildrenCount();
                Log.d("TAG", "childeNum:"+count.toString());
                pushHouse(count);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        
    }
    public void pushHouse(String count){
        int childCount = Constants.PRIVATE_HOUSE_REC_ID+Integer.parseInt(count);
        String UidHouse = ""+childCount;
        String houseCity= houseCityEdt.getText().toString();
        String houseStreet = houseStreetEdt.getText().toString();
        String houseStreetNum = houseStreetNumEdt.getText().toString();
        String houseApartmentNum = houseApartmentNumberEdt.getText().toString();
        String houseFloor = houseFloorEdt.getText().toString();
        String housePhoneNum = housePhoneNumEdt.getText().toString();
        String houseDnd = houseDnsEdt.getText().toString();
        String housePassword = housePasswordEdt.getText().toString();
        uidContact = "" + getIntent().getSerializableExtra("contact").toString();
        Log.d("TAG", "Uidcontact: "+uidContact.toString());

        Address houseAddress = new Address( houseCity, houseStreet, houseStreetNum, houseApartmentNum, houseFloor, housePhoneNum);
        House house = new House(houseDnd, housePassword, houseAddress, uidContact,null,null,null,null);
        Constants.REF_PRIVATE_CURRENT_YEAR.child(UidHouse).setValue(house);
        if (uidContact != null) {
            Constants.REF_PRIVATE_CURRENT_YEAR.child(UidHouse).child("Contacts").child("1").setValue(uidContact);
            // Connection connection = new Connection("owner",uidContact);
            // uidContact = connection.toString();
        }
    }

}