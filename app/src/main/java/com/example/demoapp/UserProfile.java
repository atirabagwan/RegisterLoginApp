package com.example.demoapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

    TextView username, useremail, userpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        username = (TextView) findViewById(R.id.username);
        useremail = (TextView) findViewById(R.id.useremail);
        userpassword = (TextView) findViewById(R.id.userpassword);

        putUserInfo();
    }

    private void putUserInfo(){

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("UserReg" , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String name = sharedPreferences.getString("fullName" , "");
        String email = sharedPreferences.getString("emailAddress" , "");
        String password = sharedPreferences.getString("password" , "");

        username.setText(name);
        useremail.setText(email);
        userpassword.setText(password);

    }
}
