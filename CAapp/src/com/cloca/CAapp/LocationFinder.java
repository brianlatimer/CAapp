package com.cloca.CAapp;

import com.cloca.CAapp.R;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.esri.android.map.LocationService;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;


public class LocationFinder extends Activity implements LocationListener {
	
	private static final String TAG = "LocationActivity";

	  LocationManager locationManager; 
	  TextView locationText;	 
	  	  
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.location);
	    
	    locationText = (TextView)this.findViewById(R.id.location_text);	    
	    
	    locationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE); 
	    
	    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER); 
	    if (location != null) {
	      Log.d(TAG, location.toString());
	      this.onLocationChanged(location);
	      
	    }
	  }	  

	  @Override
	  protected void onResume() {
	    super.onResume();
	    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this); 
	  }

	  @Override
	  protected void onPause() {
	    super.onPause();
	    locationManager.removeUpdates(this); 
	  }

	  public void onLocationChanged(Location location) {
		//MapView map = null;
	    Log.d(TAG, "onLocationChanged with location " + location.toString());
	    String text = String.format("Lat:\t %f\nLong:\t %f\nBearing:\t %f", location.getLatitude(), 
	                  location.getLongitude(),  location.getBearing());
	    this.locationText.setText(text);
	    this.locationText.setTextSize(20);
	  }

	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}