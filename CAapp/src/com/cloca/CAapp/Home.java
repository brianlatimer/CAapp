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

public class Home extends Activity implements OnClickListener {
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        ImageView img = (ImageView)findViewById(R.id.title_layout);
        img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.cloca.com"));
                startActivity(intent);
            }
        });
        
        ImageView img1 = (ImageView)findViewById(R.id.cloca_logo);
        img1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.cloca.com"));
                startActivity(intent);
            }
        });

	
	/**Search Button*/	
	View searchView = findViewById(R.id.search_button);
	searchView.setOnClickListener(this);
	
	/**About Button*/
	View aboutView = findViewById(R.id.about_button);
	aboutView.setOnClickListener(this);
    
	/**Contact Us Button*/
	View contactView = findViewById(R.id.contact_us_button);
	contactView.setOnClickListener(this);
	
	/**Report Button*/
	View reportView = findViewById(R.id.report_button);
	reportView.setOnClickListener(this);
	}
	
	public void onClick(View thisView) {
	switch (thisView.getId()) {
	case R.id.search_button:
		Intent showSearch = new Intent(this, SearchTabs.class);
		startActivity(showSearch);
		break;
		
	case R.id.about_button:
		Intent showAbout = new Intent (this, About.class);
		startActivity(showAbout);
		break;
		
	case R.id.contact_us_button:
		Intent showContact = new Intent (this, Contact.class);
		startActivity(showContact);
		break;
		
	case R.id.report_button:
		Intent showReport = new Intent (this, Report.class);
		startActivity(showReport);
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