package com.cloca.CAapp;

import com.cloca.CAapp.R;

import android.app.Activity;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.View;

public class Enniskillen_Directions extends Activity implements OnClickListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enniskillen_directions);

		/**Map It Button*/	
		View mapitView = findViewById(R.id.mapit_button);
		mapitView.setOnClickListener(this);
	}

	public void onClick(View thisView) {
		switch (thisView.getId()) {
		case R.id.mapit_button:
			Intent showFindMe = new Intent(this, findme.class);
			startActivity(showFindMe);
			break;
		}
	}
}