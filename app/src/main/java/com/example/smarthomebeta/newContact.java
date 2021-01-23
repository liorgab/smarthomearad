package com.example.smarthomebeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static com.example.smarthomebeta.FBref.refContacts;


public class newContact extends AppCompatActivity {
    TextView recordcount;
    //Contact contact = new Contact();
    EditText contactFamilyEdt,contactNameEdt,contactCityEdt,contactStreetEdt,contactStreetNumEdt,contactApartmentNumberEdt,contactFloorEdt,contactEmailEdt,contactCellularNumEdt,contactPhoneNumEdt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        recordcount = (TextView)findViewById(R.id.recordCountTxt);
        contactNameEdt = (EditText)findViewById(R.id.contactNameEdt);
        contactFamilyEdt = (EditText)findViewById(R.id.contactFamilyEdt);
        contactCityEdt = (EditText)findViewById(R.id.contactCityEdt);
        contactStreetEdt = (EditText)findViewById(R.id.contactStreetEdt);
        contactStreetNumEdt = (EditText)findViewById(R.id.contactStreetNumEdt);
        contactApartmentNumberEdt = (EditText)findViewById(R.id.contactApartmentNumberEdt);
        contactFloorEdt = (EditText)findViewById(R.id.contactFloorEdt);
        contactEmailEdt = (EditText)findViewById(R.id.contactEmailEdt);
        contactCellularNumEdt = (EditText)findViewById(R.id.contactCellularNumEdt);
        contactPhoneNumEdt = (EditText)findViewById(R.id.contactPhoneNumEdt);
    }

    public void addContact(View view) {
        refContacts.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                String count = ""+snapshot.getChildrenCount();
                pushContact(count);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


    }
    public void pushContact(String count){
        int childCount = 10000+Integer.parseInt(count);
        String UidContact = ""+childCount;
        String contactFamily = contactFamilyEdt.getText().toString();
        String contactName=contactNameEdt.getText().toString();
        String contactCity= contactCityEdt.getText().toString();
        String contactStreet = contactStreetEdt.getText().toString();
        String contactStreetNum = contactStreetNumEdt.getText().toString();
        String contactApartmentNum = contactApartmentNumberEdt.getText().toString();
        String contactFloor = contactFloorEdt.getText().toString();
        String contactEmail = contactEmailEdt.getText().toString();
        String contactCellularNum = contactCellularNumEdt.getText().toString();
        String contactPhoneNum = contactPhoneNumEdt.getText().toString();
        Address address = new Address( contactCity, contactStreet, contactStreetNum, contactApartmentNum, contactFloor, contactPhoneNum);
        Contact contact = new Contact(contactFamily, contactName, contactEmail, contactCellularNum,address);
        refContacts.child(UidContact).setValue(contact);
    }
}