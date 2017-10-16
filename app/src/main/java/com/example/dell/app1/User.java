package com.example.dell.app1;

/**
 * Created by DELL on 03-Oct-17.
 */

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    //add the varibales to be added to firebase
    public String fare;
    public String name;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String fare, String name) {
        this.fare = fare;
        this.name = name;
    }
}