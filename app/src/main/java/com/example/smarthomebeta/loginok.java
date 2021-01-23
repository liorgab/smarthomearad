package com.example.smarthomebeta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
}