package com.cloca.CAapp;

import com.cloca.CAapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Lynde_Shores extends Activity implements OnClickListener{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lynde_shores);

		/**View Map Button*/	
		View viewmapView = findViewById(R.id.view_lynde_shores_map_button);
		viewmapView.setOnClickListener(this);
		
		/**Activities Button*/	
		View activitiesView = findViewById(R.id.lynde_shores_activities_button);
		activitiesView.setOnClickListener(this);
		
	}
		
		public void onClick(View thisView) {
			switch (thisView.getId()) {
			case R.id.view_lynde_shores_map_button:
				Intent showMap = new Intent(this, Lynde_Shores_MapTabs.class);
				startActivity(showMap);
				break;
				
			case R.id.lynde_shores_activities_button:
				Intent showActivities = new Intent(this, Lynde_Shores_Activities.class);
				startActivity(showActivities);
				break;
	}

		}
}
