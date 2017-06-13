package com.cloca.CAapp;

import com.cloca.CAapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.webkit.WebView;


public class News extends Activity { 

	WebView webview;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news);
	
		webview = (WebView) findViewById(R.id.webview);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.loadUrl("http://www.cloca.com/news/index.php");
		webview.getSettings().setBuiltInZoomControls(true); 
		webview.setInitialScale(75);
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
