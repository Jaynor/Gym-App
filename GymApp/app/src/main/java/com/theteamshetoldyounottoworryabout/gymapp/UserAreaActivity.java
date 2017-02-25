package com.theteamshetoldyounottoworryabout.gymapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

//TODO: Where user will search for other users
public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        //get username, , welcome message
        final EditText etName = (EditText) findViewById(R.id.etName);
        final TextView etMessage = (TextView) findViewById(R.id.etWelcomMessage);





    }
}
