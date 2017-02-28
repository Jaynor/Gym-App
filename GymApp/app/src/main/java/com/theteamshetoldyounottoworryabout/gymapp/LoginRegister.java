package com.theteamshetoldyounottoworryabout.gymapp;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;


public class LoginRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        //gather text from fields
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etAge  = (EditText) findViewById(R.id.etAge);
       // final EditText Weight = (EditText) findViewById(R.id.Weight);
        final EditText PhoneNum = (EditText) findViewById(R.id.PhoneNum);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        //register button
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        //then so stuff with this
        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final String name = etName.getText().toString();
                final String username = etEmail.getText().toString();
                final int age = Integer.parseInt(etAge.getText().toString());
                final String password = etPassword.getText().toString();
                //final int weight = Integer.parseInt(Weight.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                Intent intent = new Intent(LoginRegister.this, LoginActivity.class);
                                LoginRegister.this.startActivity(intent);

                            }
                            else{
                                AlertDialog.Builder builder  = new AlertDialog.Builder(LoginRegister.this);
                                builder.setMessage("Registration failed")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name,username,age,password,responseListener);
                //need to add a RequestQueee
                //need to set permission for internet request
                RequestQueue queue = Volley.newRequestQueue(LoginRegister.this);
                queue.add(registerRequest);



            }
        });


    }
}
