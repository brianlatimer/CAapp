package com.cloca.CAapp;

import com.cloca.CAapp.R;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;

import android.app.Activity;
import android.os.Bundle;

public class Map extends Activity {
	MapView map = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		
		map = (MapView)findViewById(R.id.mapview);
		
		map.addLayer(new ArcGISDynamicMapServiceLayer("" + "http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer"));
		Object init = getLastNonConfigurationInstance();
			if(init !=null) {
				map.restoreState((String) init);
			}

	}

	protected void onPause() {
		super.onPause();
		map.pause();
	}

	protected void onResume() {
		super.onResume();
		map.unpause();
	}
}
