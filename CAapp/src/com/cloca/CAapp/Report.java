package com.cloca.CAapp;

import com.cloca.CAapp.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;



@SuppressWarnings("unused")
public class Report extends Activity implements OnClickListener {
	
	//public AlbumStorageDirFactory mAlbumStorageDirFactory = null;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        
	    /**Wildlife Sightings Button*/
		View speciesreportView = findViewById(R.id.species_report_button);
		speciesreportView.setOnClickListener(this);
		//speciesreportView.
		
		/**Flood Button*/
		View floodreportView = findViewById(R.id.flood_report_button);
		floodreportView.setOnClickListener(this);
		
		/**CA Concerns Button*/
		View hazardreportView = findViewById(R.id.hazard_report_button);
		hazardreportView.setOnClickListener(this);
		
		/**Fish Sightings Button*/
		View fishreportView = findViewById(R.id.fish_report_button);
		fishreportView.setOnClickListener(this);
		
		/**CA General Photo's Button*/
		View CAgeneralreportView = findViewById(R.id.CA_general_report_button);
		CAgeneralreportView.setOnClickListener(this);
	
	
    }
    
	public void onClick(View thisView) {
	Intent showReportPrep = new Intent (this, ReportPrep.class);
	switch (thisView.getId()) {

	case R.id.species_report_button:
		showReportPrep.putExtra("report_category", "Wildlife Sighting");
		showReportPrep.putExtra("category_info", getText(R.string.species_sighting));
		startActivity(showReportPrep);
		break;
		
	case R.id.flood_report_button:
		showReportPrep.putExtra("report_category", "Flooding");
		showReportPrep.putExtra("category_info", getText(R.string.flood_concerns));
		startActivity(showReportPrep);
		break;
		
	case R.id.hazard_report_button:
		showReportPrep.putExtra("report_category", "Conservation Areas Concerns");
		showReportPrep.putExtra("category_info", getText(R.string.cons_area_concerns));
		startActivity(showReportPrep);
		break;
		
	case R.id.fish_report_button:
		showReportPrep.putExtra("report_category", "Fish Sightings and Concerns");
		showReportPrep.putExtra("category_info", getText(R.string.fish_concerns));
		startActivity(showReportPrep);
		break;
		
	case R.id.CA_general_report_button:
		showReportPrep.putExtra("report_category", "Conservation Area General");
		showReportPrep.putExtra("category_info", getText(R.string.cons_general));
		startActivity(showReportPrep);
		break;
		
	}
	}
		
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inf = getMenuInflater();
    	inf.inflate(R.menu.submenu, menu);
    	
    	menu.findItem(R.id.submenu_help).setIntent(
    			new Intent(this, Help.class));
    	menu.findItem(R.id.submenu_options).setIntent(
    			new Intent(this, Options.class));
    	return true;
		
	}
    @Override
    public boolean onOptionsItemSelected(MenuItem itm) {
    	super.onOptionsItemSelected(itm);
    	Intent menuIntent = itm.getIntent();
    	if (menuIntent !=null)
    	startActivity(itm.getIntent());
    	return true;
    }

}