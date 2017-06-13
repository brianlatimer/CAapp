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



public class Scugog extends Activity implements OnClickListener {
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scugog);
    
	
	/**Purple Woods Button*/	
	View crowsView = findViewById(R.id.crows_pass_button);
	crowsView.setOnClickListener(this);
	
	View scugogMap = findViewById(R.id.scugog_map_button);
	scugogMap.setOnClickListener(this);
    
	}
	
	public void onClick(View thisView) {
	switch (thisView.getId()) {
	case R.id.crows_pass_button:
		Intent showCrows = new Intent(this, Crows_Pass.class);
		startActivity(showCrows);
		break;
		
	case R.id.scugog_map_button:
		Intent showScugogMap = new Intent(this, Scugog_Map.class);
		startActivity(showScugogMap);
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