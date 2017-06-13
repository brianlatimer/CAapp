package com.cloca.CAapp;

import com.cloca.CAapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


import android.app.Activity;
import com.google.android.gms.maps.MapView;

public class findme extends Activity {
	
    public void onCreate(Bundle savedInstanceState) {
    	
    	// testing use of LatLng to define a rectangle
/*    	 final LatLng Leicester_Square = new LatLng(51.510278, -0.130278);
    	 final LatLng Covent_Garden = new LatLng(51.51197, -0.1228);
    	 final LatLng Piccadilly_Circus = new LatLng(51.51, -0.134444);
    	 final LatLng Embankment = new LatLng(51.507, -0.122);
    	 final LatLng Charing_Cross = new LatLng(51.5073, -0.12755);*/
    	 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findme);
        //LatLngBounds bounds = null;
        //int padding = 1;
        
        // extract MapView from layout
        MapView mapView = (MapView) findViewById(R.id.mapview);        
        
        
/*        LatLngBounds bounds = new LatLngBounds.Builder()        
        .include(Leicester_Square)
        .include(Covent_Garden)
        .include(Piccadilly_Circus)
        .include(Embankment)
        .include(Charing_Cross)
        .build();*/
        
        // These are the bounds for Enniskillen
        //bounds = -8767903 5470908 -8774553 5464238;
        
        //CameraUpdateFactory.newLatLngBounds(bounds, padding);
        //CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding)
        
        //mapView.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
        
        //mapView.setBuiltInZoomControls(true);

        // create an overlay that shows our current location        
        //myLocationOverlay = new MyLocationOverlay(this, mapView);

        // add this overlay to the MapView and refresh it
        //mapView.getOverlays().add(myLocationOverlay);
        //mapView.postInvalidate();
        
    }
	

/*public class MapActivity extends FragmentActivity implements LocationListener  {

GoogleMap googleMap;

LatLng myPosition;

protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findme);
        // if  Google Play Services are available then

            // Getting reference to the SupportMapFragment of activity_main.xml
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.layout.findme);

            // Getting GoogleMap object from the fragment
            googleMap = fm.getMap();

            // Enabling MyLocation Layer of Google Map
            googleMap.setMyLocationEnabled(true);

            // Getting LocationManager object from System Service LOCATION_SERVICE
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();

            // Getting the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);

            // Getting Current Location
            Location location = locationManager.getLastKnownLocation(provider);

            if(location!=null){
            // Getting latitude of the current location
            double latitude = location.getLatitude();

            // Getting longitude of the current location
            double longitude = location.getLongitude();

            // Creating a LatLng object for the current location
            LatLng LatLng = new LatLng(latitude, longitude);

             myPosition = new LatLng(latitude, longitude);

            googleMap.addMarker(new MarkerOptions().position(myPosition).title("Start"));
            }
}*/



public void onLocationChanged(Location arg0) {
	// TODO Auto-generated method stub
	
}

public void onProviderDisabled(String arg0) {
	// TODO Auto-generated method stub
	
}

public void onProviderEnabled(String arg0) {
	// TODO Auto-generated method stub
	
}

public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
	// TODO Auto-generated method stub
	
}
}