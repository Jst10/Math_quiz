/*

    Copyright 2014 Jože Kulovic

    This file is part of Math-quiz.

    Math-quiz is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Math-quiz is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Math-quiz.  If not, see http://www.gnu.org/licenses

*/
package com.my.math_quiz;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class LaunchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
		
		((ImageButton)findViewById(R.id.ALsettingsButton)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LaunchActivity.this, PreferenceActivity.class);
				startActivity(intent);
				
			}
		});
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		Log.d("conCh","configng changfe");
//		super.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.launch, menu);
		return true;
	}
	
	public void launcherActivityButtonClick(View v){
		if(v.getId()==R.id.ALmultiPLayer){
			Intent intent = new Intent(this, MultiPlayerActivity.class);
			startActivity(intent);
		} else if(v.getId()==R.id.ALsinglePLayer){
			Intent intent = new Intent(this, LevelsDisplayedActivity.class);
			intent.putExtra(LevelsDisplayedActivity.KEY_FOR_MODE_PARAMETER, LevelsDisplayedActivity.MODE_BEFORE_SINGLE_PLAYER_GAME);
			startActivity(intent);
//		} else if(v.getId()==R.id.ALclassMode){
//			Intent intent = new Intent(this, ClassActivity.class);
//			startActivity(intent);
		}else if(v.getId()==R.id.ALtutorial){
			Intent intent = new Intent(this, LevelsDisplayedActivity.class);
			intent.putExtra(LevelsDisplayedActivity.KEY_FOR_MODE_PARAMETER, LevelsDisplayedActivity.MODE_TUTORIAL_SELECTION);
			startActivity(intent);
		}

	}

}
