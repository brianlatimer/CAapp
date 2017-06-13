package com.cloca.CAapp;


import com.cloca.CAapp.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class Audley_Woods_MapTabs extends TabActivity implements OnTabChangeListener{
	
TabHost _tabHost;
Resources _res;

@SuppressWarnings("deprecation")
@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_tabs);
		
		_tabHost = getTabHost();
		_res = getResources();
		_tabHost.setOnTabChangedListener(this);
		
		TabHost.TabSpec _tabSpec;
		
		_tabSpec = _tabHost.newTabSpec("map").setIndicator("MAP", _res.getDrawable(R.drawable.map)).setContent(new Intent(this,Audley_WoodsMap.class));
		_tabHost.addTab(_tabSpec);
		
		_tabSpec= _tabHost.newTabSpec("gps").setIndicator("FIND ME", _res.getDrawable(R.drawable.gps)).setContent(new Intent(this, LocationFinder.class));
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

	
