package com.example.wk01hw02_solo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {



    private Button mButton;
    private EditText mUsernameField;
    private EditText mPasswordField;
    private String mUsername;
    private String mPassword;
    private int userId = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireupDisplay();

    }

    private void wireupDisplay() {
        mUsernameField = findViewById(R.id.editTextLoginUserName);
        mPasswordField = findViewById(R.id.editTextLoginPassword);

        mButton = findViewById(R.id.buttonLogin);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesFromDisplay();
                if(checkForUserInDatabase()){
                    if(!validatePassword()) {
                        Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                        mPasswordField.selectAll();
                    }else {
                        Intent intent = LandingActivity.intentFactory(getApplicationContext(), userId, mUsername);
                        startActivity(intent);
                    }
                };
            }
        });
    }


    private void getValuesFromDisplay() {
        mUsername = mUsernameField.getText().toString();
        mPassword = mPasswordField.getText().toString();
    }

    private boolean checkForUserInDatabase(){

        if(!mUsername.equals("din_djarin")){
            Toast.makeText(MainActivity.this, "Invalid Username", Toast.LENGTH_SHORT).show();
            mUsernameField.selectAll();
            return false;
        }
        userId = 1;
        return true;
    }

    private boolean validatePassword(){
        return mPassword.equals("baby_yoda_ftw");
    }
}