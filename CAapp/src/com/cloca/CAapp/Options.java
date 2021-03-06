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

public class Options extends Activity implements OnClickListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);
		// TODO Auto-generated constructor stub
		/**Cancel Button*/
		
		View optionCancel = findViewById(R.id.settings_cancel);
		optionCancel.setOnClickListener(this);
	    }
		
		public void onClick(View thisView) {
		switch (thisView.getId()) {
		
		case R.id.settings_cancel:
			Intent optionCancel = new Intent(this, Home.class);
			startActivity(optionCancel);}
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