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



public class Whitby extends Activity implements OnClickListener {
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whitby);
    
    /**Whitby Map Button*/	
    View mapView = findViewById(R.id.whitby_map_button);
    mapView.setOnClickListener(this);
        
	/**Heber Down Button*/	
	View HeberView = findViewById(R.id.heberdown_button);
	HeberView.setOnClickListener(this);
	
	/**Lynde Shores Button*/	
	View LyndeView = findViewById(R.id.lynde_shores_button);
	LyndeView.setOnClickListener(this);
    
	    }

	
	public void onClick(View thisView) {
	switch (thisView.getId()) {
	case R.id.whitby_map_button:
		Intent showWhitby = new Intent(this, Whitby_Map.class);
		startActivity(showWhitby);
		break;
	
	case R.id.heberdown_button:
		Intent showHeber = new Intent(this, Heberdown.class);
		startActivity(showHeber);
		break;
		
	case R.id.lynde_shores_button:
		Intent showLynde = new Intent(this, Lynde_Shores.class);
		startActivity(showLynde);
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