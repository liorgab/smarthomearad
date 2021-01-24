package com.example.smarthomebeta;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Constants {
    public static final Integer CURRENT_YEAR = 21;
    public static final Integer CONTACT_FIRST_REC_ID = 10000;
    public static final Integer PRIVATE_HOUSE_REC_ID = 100;
    public static final String PRIVATE_PROJECT_NAME = "prv";

    //firebase constants
    public static FirebaseAuth M_Auth = FirebaseAuth.getInstance();
    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance();
    public static DatabaseReference REF_USERS=FBDB.getReference("Users");
    public static DatabaseReference REF_CONTACTS = FBDB.getReference("contacts");
    public static DatabaseReference REF_ITEMS = FBDB.getReference("items");

    public static DatabaseReference REF_PRIVATE = FBDB.getReference("projects/"+PRIVATE_PROJECT_NAME);
    public static DatabaseReference REF_PRIVATE_CURRENT_YEAR = REF_PRIVATE.child("Buildings").child(PRIVATE_PROJECT_NAME+CURRENT_YEAR.toString()).
                                    child("Apartments");


}
