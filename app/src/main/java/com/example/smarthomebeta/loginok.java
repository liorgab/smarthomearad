package com.example.smarthomebeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class loginok extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginok);
    }

    public void contactmenu(View view) {
        Intent t1 = new Intent(this,Contactmenu.class);
        startActivity(t1);

    }

    public void housemenu(View view) {
        Intent t1 = new Intent(this,newHouse.class);
        startActivity(t1);

    }
    public void ordermenu(View view) {
        Intent t1 = new Intent(this,order.class);
        startActivity(t1);

    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {

        menu.add(0,0,250,"Log Out");

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==0){
            SharedPreferences settings=getSharedPreferences("PREFS_NAME",MODE_PRIVATE);
            SharedPreferences.Editor editor=settings.edit();
            editor.putBoolean("stayConnect",false);
            editor.commit();
            Intent t1 = new Intent(this, MainActivity.class);
            startActivity(t1);
        }

        return true;
    }

}