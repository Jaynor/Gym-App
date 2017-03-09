package com.theteamshetoldyounottoworryabout.gymapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.content.IntentSender;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by guillermo on 3/2
 *
 * */
//TODO add getters for this class

public class RequestLocation extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, LocationListener{

    public static final String TAG = RequestLocation.class.getSimpleName();
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    private GoogleMap mMap; //perhaps may be null ->would imply Google Play Services APK is not ava
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);






        //create an instance of google [play location API
        //we dont need to set a content view
        //we do not need to see the map of the API

        setUpMapIfNeeded();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        //woah that was verbose !
        //location object request
        //
        mLocationRequest  = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10*1000)     //10 seconds
                .setFastestInterval(1* 1000); // 1 second, in milliseconds



    }

    @Override
    protected void onResume() {
        super.onResume();
        //setup map if needed, but not really needed
        setUpMapIfNeeded();
        //connect to the google API
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //stop requestimg location
        if(mGoogleApiClient.isConnected()){
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this);
            mGoogleApiClient.disconnect();

        }
    }
    private void setUpMapIfNeeded(){
        //we really don't need to display a map
        //we only need the location,
        //so no need to implement this

    }
    private void setUpMap(){
        //heck, might as well set up the map
        mMap.addMarker(new MarkerOptions().position(new LatLng(0,0)).title("marker"));


    }
    private void handleNewLocation(Location location){
        //gather LAT/LONG of new location
        Log.d(TAG,location.toString());

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();

        System.out.print(currentLatitude);
        System.out.print(currentLongitude);

        LatLng latLng = new LatLng(currentLatitude,currentLongitude);
        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title("you are here in this place!!");
        mMap.addMarker(options);
        //this updates the location of the
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        //add a methed to put this into its activity bucket --->



    }
    public double getLattitude(){
        return 5.0;
    }
    public double getLongitude(){
        return 5.0;

    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //need to get permission to access location
        //NEED to fic permission  issues
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(location==null){
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);

        }
        else{
            handleNewLocation(location);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //retry a failed connetion
        if(connectionResult.hasResolution()){
            try {
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);


            }
            catch (IntentSender.SendIntentException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
