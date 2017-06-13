package com.cloca.CAapp;

import com.cloca.CAapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Enniskillen extends Activity implements OnClickListener{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enniskillen);

		/**View Map Button*/	
		View viewmapView = findViewById(R.id.view_enniskillen_map_button);
		viewmapView.setOnClickListener(this);
		
		/**Activities Button*/	
		View activitiesView = findViewById(R.id.activities_button);
		activitiesView.setOnClickListener(this);
		
		/**Directions Button*/	
		View directionsView = findViewById(R.id.enniskillen_directions_button);
		directionsView.setOnClickListener(this);
		
	}
		
		public void onClick(View thisView) {
			switch (thisView.getId()) {
			case R.id.view_enniskillen_map_button:
				Intent showMap = new Intent(this, Enniskillen_MapTabs.class);
				startActivity(showMap);
				break;
				
			case R.id.activities_button:
				Intent showActivities = new Intent(this, Enniskillen_Activities.class);
				startActivity(showActivities);
				break;
				
			case R.id.enniskillen_directions_button:
				Intent showDirections = new Intent(this, Enniskillen_Directions.class);
				startActivity(showDirections);
				break;				
			}

		}
}
