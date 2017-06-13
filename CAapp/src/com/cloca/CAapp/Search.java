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



public class Search extends Activity implements OnClickListener {
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
    
        ImageView img3 = (ImageView)findViewById(R.id.parkingpassimage);
        img3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.cloca.com/con_areas/memberships.php"));
                startActivity(intent);
            }
        });
        
    /**Clarington Button*/
	View claringtonView = findViewById(R.id.clarington_button);
	claringtonView.setOnClickListener(this);
	
	/**Oshawa Button*/
	View oshawaView = findViewById(R.id.oshawa_button);
	oshawaView.setOnClickListener(this);
	
	/**Scugog Button*/
	View scugogView = findViewById(R.id.scugog_button);
	scugogView.setOnClickListener(this);
	
	/**Whitby Button*/
	View whitbyView = findViewById(R.id.whitby_button);
	whitbyView.setOnClickListener(this);
	
	/**Other Areas Button*/
	View otherView = findViewById(R.id.other_button);
	otherView.setOnClickListener(this);
	
    }
    
	public void onClick(View thisView) {
	switch (thisView.getId()) {
	
	case R.id.clarington_button:
		Intent showClarington = new Intent (this, Clarington.class);
		startActivity(showClarington);
		break;
		
	case R.id.oshawa_button:
		Intent showOshawa = new Intent (this, Oshawa.class);
		startActivity(showOshawa);
		break;
		
	case R.id.scugog_button:
		Intent showScugog = new Intent (this, Scugog.class);
		startActivity(showScugog);
		break;
		
	case R.id.whitby_button:
		Intent showWhitby = new Intent (this, Whitby.class);
		startActivity(showWhitby);
		break;
		
	case R.id.other_button:
		Intent showOther = new Intent (this, Other_Areas.class);
		startActivity(showOther);
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