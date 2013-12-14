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
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.my.math_quiz.views.TitleBar;
import com.my.math_quiz.views.TitleBar.TitleBarListener;

public class MultiPlayerActivity extends Activity implements TitleBarListener {

	TitleBar titleBar=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_player);
		
		titleBar=(TitleBar)findViewById(R.id.TBtitleBar);
		titleBar.setTitleBarListener(this);
		titleBar.setTitle(getString(R.string.multi_player));
		titleBar.setRightImage(BitmapFactory.decodeResource(getResources(), R.drawable.action_settings));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.launch, menu);
		return true;
	}
	
	public void MultiPlayerActivityButtonClick(View v){
		if(v.getId()==R.id.MPhost){
			Intent intent = new Intent(this, MultiPlayerActivityHost.class);
			startActivity(intent);
		} else if(v.getId()==R.id.MPJoin){
			Intent intent = new Intent(this, MultiPlayerActivityJoin.class);
			startActivity(intent);
		} else if(v.getId()==R.id.MPOneDevice){
			Intent intent = new Intent(this, LevelsDisplayedActivity.class);
			intent.putExtra(LevelsDisplayedActivity.KEY_FOR_MODE_PARAMETER, LevelsDisplayedActivity.MODE_MULTIPLAYER_SELECTION_ONE_DEVICE);
			startActivity(intent);
		}
		
	}

	@Override
	public void onLeftButtonClick() {
		this.finish();
	}

	@Override
	public void onRightButtonClick() {
		Intent intent = new Intent(MultiPlayerActivity.this, PreferenceActivity.class);
		startActivity(intent);
	}

}
