package com.example.demoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.service.autofill.RegexValidator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserRegister extends AppCompatActivity {

    TextView TV_signIp;
    EditText fullName,emailAddress,password;
    Button btnRegister;
    String emailPattern = "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        TV_signIp = (TextView) findViewById(R.id.TV_signIn);
        fullName = (EditText) findViewById(R.id.fullName);
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        password = (EditText) findViewById(R.id.password);

        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fullName.getText().toString().isEmpty() || emailAddress.getText().toString().isEmpty() || password.getText().toString().isEmpty()){

                    Toast.makeText(UserRegister.this ,"Please fill all Details" , Toast.LENGTH_SHORT).show();
                }else {

                    if(emailAddress.getText().toString().matches(emailPattern)){

                        if(password.getText().toString().length() >= 6){

                            Toast.makeText(UserRegister.this ,"All Good, User created..." , Toast.LENGTH_SHORT).show();
                            storeUserInfo();
                            Intent intent = new Intent(UserRegister.this , UserLogin.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(UserRegister.this ,"Password length must be at least 7 digits" , Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(UserRegister.this ,"Input Valid Email address" , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        TV_signIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserRegister.this , UserLogin.class);
                startActivity(intent);
            }
        });
    }

    private void storeUserInfo(){

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("UserReg" , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("fullName" , fullName.getText().toString());
        editor.putString("emailAddress" , emailAddress.getText().toString());
        editor.putString("password" , password.getText().toString());
        editor.apply();
        editor.commit();
    }
}
