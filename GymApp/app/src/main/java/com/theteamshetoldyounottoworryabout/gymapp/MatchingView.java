package com.theteamshetoldyounottoworryabout.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

//TODO: implement a better layout design
/*
    I'm not really happy with the current design, it seems a bit too 2007-ish
    Good note, though- we can implement this and switch to a better layout
 */
public class MatchingView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_view);


        //initialize textView fields,



        RequestLocation locationRequest = new RequestLocation();
        double m_location = locationRequest.getLattitude();
        final TextView etLocation = (TextView) findViewById(R.id.etLocation);
        String locationMessage = "here is the location, " + Double.toString(m_location);
        etLocation.setText(locationMessage);
        



    }
}
