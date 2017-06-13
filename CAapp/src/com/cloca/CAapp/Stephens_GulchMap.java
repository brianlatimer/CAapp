package com.cloca.CAapp;

import com.cloca.CAapp.R;
import com.esri.android.map.Layer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.esri.android.map.event.OnSingleTapListener;

import android.app.Activity;
import android.os.Bundle;

public class Stephens_GulchMap extends Activity {
	MapView map = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stephens_gulch_map);
		
		map = (MapView)findViewById(R.id.stephens_gulch_mapview);
		
		map.addLayer(new ArcGISDynamicMapServiceLayer("" + "http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer"));
		//map.addLayer(new ArcGISDynamicMapServiceLayer("" + "https://extgis.cloca.com/extArcGIS/rest/services/Android/Basemap_2008_mercator/MapServer"));
		map.addLayer(new ArcGISDynamicMapServiceLayer("" + "https://extgis.cloca.com/extArcGIS/rest/services/Android/Conservation_Areas_Map/MapServer"));
		map.addLayer(new ArcGISDynamicMapServiceLayer("" + "https://extgis.cloca.com/extArcGIS/rest/services/Android/Trails/MapServer"));
		Object init = getLastNonConfigurationInstance();
			if(init !=null) {
				map.restoreState((String) init);
			}
			map.setOnSingleTapListener(new OnSingleTapListener() {
				private static final long serialVersionUID = 1L;
				
				public void onSingleTap (float x, float y) {
					if (map.isLoaded()) {
						Layer layer = map.getLayer(2);
						
						if (layer.isVisible()) {
							layer.setVisible(false);
							
						} else {
							layer.setVisible(true);
						}
					}
				}
				
			});
	
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

	

		