package com.cloca.CAapp;

import com.cloca.CAapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class Other_Areas extends Activity implements OnClickListener {
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_ca);
	
	/**Audley Woods Button*/	
	View audleyView = findViewById(R.id.audley_woods_button);
	audleyView.setOnClickListener(this);
	
	/**Hampton Button*/	
	View hamptonView = findViewById(R.id.hampton_button);
	hamptonView.setOnClickListener(this);
	
	/**Oshawa Valleylands Button*/	
	View oshawaView = findViewById(R.id.oshawa_valleylands_button);
	oshawaView.setOnClickListener(this);
	
	/**Rahmani Tract Button*/	
	View rahmaniView = findViewById(R.id.rahmani_tract_button);
	rahmaniView.setOnClickListener(this);
	
	/**Toad Hollow Button*/	
	View toadView = findViewById(R.id.toad_hollow_button);
	toadView.setOnClickListener(this);
	
	
	}
	
	public void onClick(View thisView) {
	switch (thisView.getId()) {
	
	case R.id.audley_woods_button:
		Intent showAudley = new Intent(this, Audley_Woods_MapTabs.class);
		startActivity(showAudley);
		break;
		
	case R.id.hampton_button:
		Intent showHampton = new Intent(this, Hampton_MapTabs.class);
		startActivity(showHampton);
		break;
		
	case R.id.oshawa_valleylands_button:
		Intent showOshawa = new Intent(this, Oshawa_Valleylands_MapTabs.class);
		startActivity(showOshawa);
		break;
		
	case R.id.rahmani_tract_button:
		Intent showRahmani = new Intent(this, Rahmani_Tract_MapTabs.class);
		startActivity(showRahmani);
		break;
		
	case R.id.toad_hollow_button:
		Intent showToad = new Intent(this, Toad_Hollow_MapTabs.class);
		startActivity(showToad);
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