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



public class Oshawa extends Activity implements OnClickListener {
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oshawa);
    
	
	/**Purple Woods Button*/	
	View purpleView = findViewById(R.id.purple_woods_button);
	purpleView.setOnClickListener(this);
	
	View oshawaMap = findViewById(R.id.oshawa_map_button);
	oshawaMap.setOnClickListener(this);
    
	}
	
	public void onClick(View thisView) {
	switch (thisView.getId()) {
	case R.id.purple_woods_button:
		Intent showPurple = new Intent(this, Purple_Woods.class);
		startActivity(showPurple);
		break;
		
	case R.id.oshawa_map_button:
		Intent showOshMap = new Intent(this, Oshawa_Map.class);
		startActivity(showOshMap);
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