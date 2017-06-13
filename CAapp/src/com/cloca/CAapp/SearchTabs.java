package com.cloca.CAapp;


import com.cloca.CAapp.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class SearchTabs extends TabActivity implements OnTabChangeListener{
	
TabHost _tabHost;
Resources _res;

@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_tabs);
		
		_tabHost = getTabHost();
		_res = getResources();
		_tabHost.setOnTabChangedListener(this);
			
		
		TabHost.TabSpec _tabSpec;
		
		_tabSpec = _tabHost.newTabSpec("search").setIndicator("SEARCH", _res.getDrawable(R.drawable.search_icon)).setContent(new Intent(this, Search.class));
		_tabHost.addTab(_tabSpec);
		
		_tabSpec= _tabHost.newTabSpec("news").setIndicator("NEWS", _res.getDrawable(R.drawable.news)).setContent(new Intent(this, TwitterFeed.class));
		_tabHost.addTab(_tabSpec);
		
		for(int i=0; i<_tabHost.getTabWidget().getChildCount(); i++)
		{
			_tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.LTGRAY);
		}
		
		_tabHost.getTabWidget().setCurrentTab(0);
		
		_tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.argb(255, 10, 154, 181));
		}

public void onTabChanged(String tabId){
	for(int i=0; i<_tabHost.getTabWidget().getChildCount(); i++)
	{
		_tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.LTGRAY);
		}
		_tabHost.getTabWidget().getChildAt(_tabHost.getCurrentTab()).setBackgroundColor(Color.argb(255, 10, 154, 181));
	}
	
}

	
