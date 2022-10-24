package com.example.onlineshoppingstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton login, forgotPassword, signup;
    private EditText User, Password;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); /*** code allows for full screen use **/


        /***
         * Code still to be written
         * checks to verify that the user exists and that the details correlate
         * ***/
        login = (ImageButton) findViewById(R.id.LoginButton);
        myDB = new DBHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /***
                 * Functions to check user exists (database needs to be connected)
                 * Moves to next Activity (to be created by next Team)
                 * ***/
                checkUserDetails();

            }
        });

        /***
         * Opens new ForgotPassword Activity
         * Activity allows users to reset their password
         * ***/
        forgotPassword = (ImageButton) findViewById(R.id.forgotPasswordButton);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openForgotPassword();

            }
        });

        /***
         * Opens new Signup Activity
         * Activity allows users to sign up for the app
         * ***/
        signup = (ImageButton) findViewById(R.id.signUpButton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignupActivity();

            }
        });
    }

    public void openForgotPassword(){
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    public void openSignupActivity(){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void checkUserDetails() {

        User = (EditText) findViewById(R.id.USERNAME);
        String user = User.getText().toString();

        Password = (EditText) findViewById(R.id.PASSWORD);
        String password = Password.getText().toString();
        myDB = new DBHelper(this);

        if(user.equals("")|| password.equals("")){
            Toast.makeText(this, "Fill all the fields.", Toast.LENGTH_SHORT).show();
        }
        else {

            Boolean result = myDB.checkusernameandPassword(user, password);

            if (result == true){
                Toast.makeText(this, "Welcome ." + user, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Invalid Credentials.", Toast.LENGTH_SHORT).show();

            }
        }






//        /***
//         * Code to be written for retrieving values from database
//         * To be done by Database team
//         * example provided at bottom of page
//         * ***/
//
//        if (user.equals("admin") && password.equals("admin")) {
//            /***
//             * further code needs to be written to check whether text fields match
//             * database entries before login button can properly execute
//             * code provided is just a placeholder to show functionality
//             * ***/
//            executeLogin();
//            /***
//             * see executeLogin() function to move to new Activity (needs to be created)
//             * ***/
//
//        } else {
//            Toast.makeText(this, "Invalid Username or Password! \nTry Again", Toast.LENGTH_SHORT).show();
//
//        }
    }

    public void executeLogin() {
        /***
         * NewActivity is a placeholder Activity to be created by next interface team
         * commented out to prevent breaking the code

        Intent intent = new Intent(MainActivity.this, NewActivity.class);
            startActivity(intent);
        ***/
        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
    }


    /***
     * Example of code to access entries in database
     *
     * DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users/");
     *     ref.addListenerForSingleValueEvent(new ValueEventListener() {
     *         @Override
     *         public void onDataChange(@NonNull DataSnapshot snapshot) {
     *             for (DataSnapshot ds : snapshot.getChildren()) {
     *
     *                 users_from_database = (String) ds.child("username").getValue();
     *
     *                 username_list.add(users_from_database);
     *                 StringBuilder stringBuilder = new StringBuilder();
     *                 for (String s : username_list) {
     *                     stringBuilder.append(s + "\n");
     *                 }
     *                 Log.d("ZI", stringBuilder.toString());
     *
     *             }
     *
     *         }
     *
     *        }
     * ***/
}