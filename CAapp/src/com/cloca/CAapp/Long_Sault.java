package com.cloca.CAapp;

import com.cloca.CAapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Long_Sault extends Activity implements OnClickListener{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.long_sault);

		/**View Map Button*/	
		View viewmapView = findViewById(R.id.view_long_sault_map_button);
		viewmapView.setOnClickListener(this);
		
		/**Activities Button*/	
		View activitiesView = findViewById(R.id.long_sault_activities_button);
		activitiesView.setOnClickListener(this);
		
	}
		
		public void onClick(View thisView) {
			switch (thisView.getId()) {
			case R.id.view_long_sault_map_button:
				Intent showMap = new Intent(this, Long_Sault_MapTabs.class);
				startActivity(showMap);
				break;
				
			case R.id.long_sault_activities_button:
				Intent showActivities = new Intent(this, Long_Sault_Activities.class);
				startActivity(showActivities);
				break;
	}

		}
}
