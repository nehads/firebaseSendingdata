package com.example.dell.app1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText inputFare, inputName;
    private Button btnSave;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        inputFare = (EditText) findViewById(R.id.fare);
        inputName = (EditText) findViewById(R.id.name);
        btnSave = (Button) findViewById(R.id.btn_save);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to child node named as 'details'
        mFirebaseDatabase = mFirebaseInstance.getReference("details");

       // mFirebaseInstance.getReference("app_title").setValue("pilot");

        // app_title change listener
        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "App title updated");

                String appTitle = dataSnapshot.getValue(String.class);

                // update toolbar title
                getSupportActionBar().setTitle(appTitle);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read app title value.", error.toException());
            }
        });

        // Save / update the user
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fare = inputFare.getText().toString();
                String name = inputName.getText().toString();

                // Check for already existed userId
                if (TextUtils.isEmpty(userId)) {
                    createUser(fare, name);
//                } else {
//                    updateUser(fare, name);
//                }
                }
                Toast.makeText(getApplicationContext(), "Data sent", Toast.LENGTH_SHORT).show();
            }
        });

     //   toggleButton();
    }

    // Changing button text
    private void toggleButton() {
        if (TextUtils.isEmpty(userId)) {
            btnSave.setText("Save");
//        } else {
//            btnSave.setText("Update");
//        }
        }
    }

    /**
     * Creating new user node under 'details'
     */
    private void createUser(String fare, String name) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        User user = new User(fare, name);

        mFirebaseDatabase.child(userId).setValue(user);

      //  addUserChangeListener();
    }

    /**
     * User data change listener
     */
//    private void addUserChangeListener() {
//        // User data change listener
//        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
//
//                // Check for null
//                if (user == null) {
//                    Log.e(TAG, "User data is null!");
//                    return;
//                }
//
//                Log.e(TAG, "User data is changed!" + user.fare + ", " + user.name);
//
//                // Display newly updated fare and name
//              //  txtDetails.setText(user.fare + ", " + user.name);
//
//                // clear edit text
//                inputName.setText("");
//                inputFare.setText("");
//
//               // toggleButton();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.e(TAG, "Failed to read user", error.toException());
//            }
//        });
//    }

//    private void updateUser(String fare, String name) {
//        // updating the user via child nodes
//        if (!TextUtils.isEmpty(fare))
//            mFirebaseDatabase.child(userId).child("fare").setValue(fare);
//
//        if (!TextUtils.isEmpty(name))
//            mFirebaseDatabase.child(userId).child("name").setValue(name);
//    }
}

