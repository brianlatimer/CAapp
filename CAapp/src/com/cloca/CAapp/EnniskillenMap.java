package com.cloca.CAapp;


import com.cloca.CAapp.R;
import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.Layer;
import com.esri.android.map.LocationService;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.LinearUnit;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.geometry.Unit;


import android.app.Activity;
import android.location.Location;
import android.os.Bundle;

public class EnniskillenMap extends Activity {
	MapView map = null;
	GraphicsLayer graphicsLayer = null;

	final static double SEARCH_RADIUS = 5;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enniskillen_map);
		
		map = (MapView)findViewById(R.id.enniskillen_mapview);
		
		map.addLayer(new ArcGISDynamicMapServiceLayer("" + "http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer"));
		//map.addLayer(new ArcGISDynamicMapServiceLayer("" + "https://extgis.cloca.com/extArcGIS/rest/services/Android/Basemap_2008_mercator/MapServer"));
		map.addLayer(new ArcGISDynamicMapServiceLayer("" + "https://extgis.cloca.com/extArcGIS/rest/services/Android/Conservation_Areas_Map/MapServer"));
		map.addLayer(new ArcGISDynamicMapServiceLayer("" + "https://extgis.cloca.com/extArcGIS/rest/services/Android/Trails/MapServer"));
		@SuppressWarnings("deprecation")
		Object init = getLastNonConfigurationInstance();
			if(init !=null) {
				map.restoreState((String) init);
				
				graphicsLayer = new GraphicsLayer();
				map.addLayer(graphicsLayer);
		
			}
			//**Function for turning specified layer on/off */
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
			boolean locationChanged = false;
			public void onLocationChanged(Location loc) {
				if (!locationChanged) {
					locationChanged = true;
					double locy = loc.getLatitude();
					double locx = loc.getLongitude();
					
					Point wgspoint = new Point(locx, locy);
					Point mapPoint = (Point) GeometryEngine.project(wgspoint, SpatialReference.create(4326), map.getSpatialReference());
					Unit mapUnit = map.getSpatialReference().getUnit();
					double zoomWidth = Unit.convertUnits(SEARCH_RADIUS, Unit.create(LinearUnit.Code.MILE_US), mapUnit);
					Envelope zoomExtent = new Envelope(mapPoint, zoomWidth, zoomWidth);
					map.setExtent(zoomExtent);
					// call locationfinder here and set location indicator						
/*					LocationService ls = map.getLocationService();
					ls.start();
					ls.setAccuracyCircleOn(true);
					ls.getLocation();*/
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
	


	

		