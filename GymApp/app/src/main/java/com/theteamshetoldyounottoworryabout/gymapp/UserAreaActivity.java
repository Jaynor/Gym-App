package com.theteamshetoldyounottoworryabout.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//TODO: Where user will search for other users
public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        int age = intent.getIntExtra("age",-1);





        final EditText etName = (EditText) findViewById(R.id.etName);
        final TextView etMessage = (TextView) findViewById(R.id.etWelcomMessage);

        String message = name + ", Welcome.";
        etMessage.setText(message);
        etName.setText(name);

        final Button bFindPartner  = (Button)findViewById(R.id.etRequestPartner);

        //create a button listener
        bFindPartner.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //perform action when clicking on the object
            }
        });



    }
}
