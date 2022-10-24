package com.example.onlineshoppingstore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    private ImageButton signup;
    private EditText username, password;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide(); /*** code allows for full screen use **/


    signup = (ImageButton) findViewById(R.id.signUpButton);
    username = (EditText) findViewById(R.id.Username);
    password = (EditText) findViewById(R.id.Password);
    myDB = new DBHelper(this);

    signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            /***
             * code to validate and add user to database
             * to be done by database team
             * ***/

            String user = username.getText().toString();
            String pass = password.getText().toString();

            if(TextUtils.isEmpty(user)) {
                Toast.makeText(SignupActivity.this, "Please enter a username.", Toast.LENGTH_SHORT).show();
                username.setError("Username cannot be empty.");
                username.requestFocus();
            }else if(TextUtils.isEmpty(pass)) {
                Toast.makeText(SignupActivity.this, "Please enter a password.", Toast.LENGTH_SHORT).show();
                password.setError("Password cannot be empty.");
                password.requestFocus();
            }else{
                /***
                 * code to add to database to be added by database team
                 * ***/
                Boolean validUser = myDB.checkusername(user);
                if(validUser == false) {
                    Boolean validRegistration = myDB.insertData(user, pass);
                    if (validRegistration == true) {
                        goBackToLogin();
                        Toast.makeText(SignupActivity.this, "User Successfully Registered.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignupActivity.this, "User Registration Failed.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    goBackToLogin();
                    Toast.makeText(SignupActivity.this, "User already Exists. \n Please Sign In.", Toast.LENGTH_SHORT).show();

                }


            }

        }
    });

    }

    public void goBackToLogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}