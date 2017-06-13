package com.cloca.CAapp;

import com.cloca.CAapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Heberdown extends Activity implements OnClickListener{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.heberdown);

		/**View Map Button*/	
		View viewmapView = findViewById(R.id.view_heberdown_map_button);
		viewmapView.setOnClickListener(this);
		
		/**Activities Button*/	
		View activitiesView = findViewById(R.id.heberdown_activities_button);
		activitiesView.setOnClickListener(this);
		
	}
		
		public void onClick(View thisView) {
			switch (thisView.getId()) {
			case R.id.view_heberdown_map_button:
				Intent showMap = new Intent(this, Heberdown_MapTabs.class);
				startActivity(showMap);
				break;
				
			case R.id.heberdown_activities_button:
				Intent showActivities = new Intent(this, Heberdown_Activities.class);
				startActivity(showActivities);
				break;
	}

		}
}
