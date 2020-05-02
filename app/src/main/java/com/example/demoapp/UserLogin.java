package com.example.demoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserLogin extends AppCompatActivity {

    TextView TV_signUp;
    EditText emailAddress , password;
    Button btnLogin;
    String emailPattern = "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        TV_signUp = (TextView) findViewById(R.id.TV_signUp);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        password = (EditText) findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailAddress.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(UserLogin.this , "Please fill all details" , Toast.LENGTH_SHORT).show();
                }
                else {
                    if(emailAddress.getText().toString().matches(emailPattern)){
                        if(password.getText().toString().length() < 7){
                            Toast.makeText(UserLogin.this , "Password must be at least 7 digits" , Toast.LENGTH_SHORT).show();
                        }else{

                            if(getUserInfo(emailAddress.getText().toString() , password.getText().toString())){

                                Intent intent = new Intent(UserLogin.this , UserProfile.class);
                                startActivity(intent);

                            }else{
                                Toast.makeText(UserLogin.this , "Email or Password incorrect" , Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(UserLogin.this , "Successfully Logging in..." , Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(UserLogin.this , "Input valid Email Address" , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        TV_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLogin.this , UserRegister.class);
                startActivity(intent);
            }
        });
    }

    private boolean getUserInfo(String Uemail , String Upass){

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("UserReg" , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String email = sharedPreferences.getString("emailAddress" , "");
        String password = sharedPreferences.getString("password" , "");

        if(email.equals(Uemail) && password.equals(Upass)){
            return true;
        }else{
            return false;
        }

    }
}
