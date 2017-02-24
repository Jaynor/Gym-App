package com.theteamshetoldyounottoworryabout.gymapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


public class LoginRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        //gather text from fields
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText Weight = (EditText) findViewById(R.id.Weight);
        final EditText PhoneNum = (EditText) findViewById(R.id.PhoneNum);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        //register button
        final Button bRegister = (Button) findViewById(R.id.bRegister);




    }
}
