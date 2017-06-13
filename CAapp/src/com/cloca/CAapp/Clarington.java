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


public class Clarington extends Activity implements OnClickListener {
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clarington);
    
	
	/**Bowmanville and Westside Marshes Button*/	
	View BowmanView = findViewById(R.id.bowmanville_westside_button);
	BowmanView.setOnClickListener(this);
    
	/**Enniskillen Button*/
	View EnnisView = findViewById(R.id.enniskillen_button);
	EnnisView.setOnClickListener(this);
	
	/**Stephens Gulch Button*/
	View stephensView = findViewById(R.id.stephens_gulch_button);
	stephensView.setOnClickListener(this);
	
	/**Long Sault Button*/
	View SaultView = findViewById(R.id.long_sault_button);
	SaultView.setOnClickListener(this);
	
	/**View Map Button*/
	View MapView = findViewById(R.id.clarington_map_button);
	MapView.setOnClickListener(this);
    }
	
	public void onClick(View thisView) {
	switch (thisView.getId()) {
	case R.id.bowmanville_westside_button:
		Intent showBowmanville = new Intent(this, Bowmanville_Westside.class);
		startActivity(showBowmanville);
		break;
	
	case R.id.long_sault_button:
		Intent showSault = new Intent (this, Long_Sault.class);
		startActivity(showSault);
		break;
		
	case R.id.enniskillen_button:
		Intent showEnniskillen = new Intent (this, Enniskillen.class);
		//String ca_name = null;
		showEnniskillen.putExtra("ca_name","Enniskillen");
		showEnniskillen.putExtra("image_url", R.drawable.enniskillen_photo);
		startActivity(showEnniskillen);
		break;
		
	case R.id.stephens_gulch_button:
		Intent showStephens = new Intent (this, Stephens_Gulch.class);
		startActivity(showStephens);
		break;
		
	case R.id.clarington_map_button:
		Intent showClarington = new Intent (this, Clarington_Map.class);
		startActivity(showClarington);
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