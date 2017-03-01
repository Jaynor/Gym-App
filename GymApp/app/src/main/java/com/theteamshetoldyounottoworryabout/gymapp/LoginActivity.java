package com.theteamshetoldyounottoworryabout.gymapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText m_login = (EditText) findViewById(R.id.m_login);
        final EditText Password = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.button);
        final TextView registerLink = (TextView) findViewById(R.id.m_registerHere);

        registerLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(LoginActivity.this,LoginRegister.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String username  = m_login.getText().toString();
                final String password = Password.getText().toString();

                //get response from server
                Response.Listener<String> responseListener = new Response.Listener<String> (){
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                //create a new Intent - go to User Activity Section
                                String name  = jsonResponse.getString("name");
                                int age = jsonResponse.getInt("age");
                                //create a new intent

                                Intent intent  = new Intent(LoginActivity.this, UserAreaActivity.class);
                                intent.putExtra("name", name);
                                intent.putExtra("age", age);
                                intent.putExtra("username",username);
                                LoginActivity.this.startActivity(intent);

                            }else{
                                //Print a Login error (retry) - perhaps add a timeout if we are feeling ambitious
                                AlertDialog.Builder builder  = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Incorrect Login")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }
                        }
                        catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(username,password,responseListener);
                //need to add a RequestQueee
                //need to set permission for internet request
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

    }
}
