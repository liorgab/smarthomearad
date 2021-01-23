package com.example.smarthomebeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.example.smarthomebeta.FBref.refContacts;

public class Contactmenu extends AppCompatActivity {
    ListView contactslist;
    LinkedList<String> contacts;
    ArrayAdapter<String>adp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactmenu);
        contactslist = (ListView) findViewById(R.id.contactslist);
        contacts = new LinkedList<String>();
        adp = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,contacts);

        refContacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    contacts.add(ds.child("contactFamily").getValue().toString()+" "+ds.child("contactName").getValue().toString());

                }
                adp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        contactslist.setAdapter(adp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contactmenuitems, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.newcontact){
            Intent t1 = new Intent(this, newContact.class);
            startActivityForResult(t1,1);
        }

        return true;
    }
}