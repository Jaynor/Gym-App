package com.theteamshetoldyounottoworryabout.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText m_login = (EditText) findViewById(R.id.m_login);
        final EditText Password = (EditText) findViewById(R.id.password);
        final Button bLogin = (Button) findViewById(R.id.button);
        final TextView registerLink = (TextView) findViewById(R.id.m_registerHere);

        registerLink.setOnClickListener(new View.OnClickListener() {
            @override
            public void onClickView(View v){
                Intent registerIntent = new Intent(LoginActivity.this,LoginRegister.class);
                LoginRegister.this.startActivity(registerIntent);
            }

        });
    }
}
